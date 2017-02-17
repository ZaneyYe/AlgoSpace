package com.algo.fuck;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 封装HttpComponents-HttpClient 4.x
 * 主要功能：
 * 1、自定义重试次数，默认不重试
 * 2、对非活动的连接进行验证防止half-closed问题
 * 3、http keep-alive timeout时间根据服务器设置，若没有3s超时客户端发起断开
 *
 * @author chentong
 */
@SuppressWarnings("unchecked")
public class HTTPLongClient4 implements Closeable {
    private static final Logger logger                     = LoggerFactory
            .getLogger(HTTPLongClient4.class);

    private static final String USER_AGENT                 = "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/35.0.1916.153 Safari/537.36";

    private static final String DEFAULT_CHARSET            = "utf-8";
    private static final int    DEFAULE_CONNECTION_TIMEOUT = 2000;
    private static final int    DEFAULE_SOTIMEOUT          = 2000;

    /** 带有http长连接池的http客户端 */
    private CloseableHttpClient httpClient                 = null;
    /** 连接超时时间设置,单位毫秒 */
    private int                 connectionTimeout;
    /** 读取socket时间设置,单位毫秒 */
    private int                 soTimeout;
    /** the timeout in milliseconds used when requesting a connection from the connection manager*/
    private int                 connectionRequestTimeout   = 1000;
    /** 每个目的host最多保持多少个连接 */
    private int                 maxPerRoute                = 100;
    /** 最多保持多少个连接 */
    private int                 maxTotalConnections        = 1024;
    /** Defines period of inactivity in milliseconds after which persistent connections must be re-validated prior to being to the consumer */
    private int                 validateAfterInactivity    = 1000;


    private CloseableHttpClient createHttpClient() {
        // 多线程连接池
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setDefaultMaxPerRoute(maxPerRoute);
        connectionManager.setMaxTotal(maxTotalConnections);
        connectionManager.setDefaultConnectionConfig(ConnectionConfig.custom()
                .setCharset(Charset.forName(DEFAULT_CHARSET)).build());
        connectionManager.setValidateAfterInactivity(validateAfterInactivity);
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        //                // set request header
        //                List<Header> headers = new ArrayList<Header>();
        //                headers.add(new BasicHeader("Connection", "Keep-Alive"));
        //                httpClientBuilder.setDefaultHeaders(headers);
        // 设置默认request参数
        httpClientBuilder.setDefaultRequestConfig(RequestConfig.custom()
                .setConnectTimeout(connectionTimeout).setSocketTimeout(soTimeout)
                .setConnectionRequestTimeout(connectionRequestTimeout).build());
        //        // 重试机制
        //        httpClientBuilder.setRetryHandler(new HttpRequestRetryHandler() {
        //            @Override
        //            public boolean retryRequest(IOException exception, int executionCount,
        //                                        HttpContext context) {
        //                HttpRequestWrapper httpReuqest = (HttpRequestWrapper) context
        //                    .getAttribute(HttpCoreContext.HTTP_REQUEST);
        //                if (executionCount > DEFAULT_REQUEST_RETRY_NUM) {
        //                    logger.warn(
        //                        "Maximum tries reached for client http pool, maxRetries={}, url={}",
        //                        DEFAULT_REQUEST_RETRY_NUM, httpReuqest.getOriginal());
        //                    return false;
        //                }
        //                if (exception instanceof NoHttpResponseException) {
        //                    logger.warn("No response from server on {} call, url={}", executionCount,
        //                        httpReuqest.getOriginal());
        //                    return true;
        //                }
        //                if (exception instanceof SSLHandshakeException) {
        //                    return false;
        //                }
        //                boolean idempotent = !(httpReuqest instanceof HttpEntityEnclosingRequest);
        //                if (idempotent) {
        //                    logger.warn("HttpClient retryRequest, Exception={}, url={}", exception
        //                        .getClass().getName(), httpReuqest.getOriginal());
        //                    return true;
        //                }
        //                return false;
        //            }
        //        });
        //         自定义长连接策略
        httpClientBuilder.setKeepAliveStrategy(new ConnectionKeepAliveStrategy() {
            @Override
            public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
                // Honor 'keep-alive' header
                HeaderElementIterator it = new BasicHeaderElementIterator(response
                        .headerIterator(HTTP.CONN_KEEP_ALIVE));
                while (it.hasNext()) {
                    HeaderElement he = (HeaderElement) it.nextElement();
                    String param = he.getName();
                    String value = he.getValue();
                    if (value != null && param.equalsIgnoreCase("timeout")) {
                        try {
                            return (Long.parseLong(value) * 1000);
                        } catch (NumberFormatException ignore) {
                        }
                    }
                }
                return (3 * 1000);
            }
        });
        httpClientBuilder.setConnectionManager(connectionManager);
        httpClientBuilder.setUserAgent(USER_AGENT);
        httpClientBuilder.evictIdleConnections(5, TimeUnit.MINUTES);
        return httpClientBuilder.build();
    }

    public HTTPLongClient4() {
        this(DEFAULE_SOTIMEOUT);
    }

    /**
     * @param soTimeout
     */
    public HTTPLongClient4(int soTimeout) {
        this(DEFAULE_CONNECTION_TIMEOUT, soTimeout);
    }

    /**
     * @param connectionTimeout
     * @param soTimeout
     */
    public HTTPLongClient4(int connectionTimeout, int soTimeout) {
        this.soTimeout = soTimeout;
        this.connectionTimeout = connectionTimeout;
        this.httpClient = createHttpClient();
    }

    /**
     * 获得经过长连接池配置的http客户端
     */
    private CloseableHttpClient getHttpClient() {
        return httpClient;
    }

    /**
     * http get请求
     * @param httpAddr
     * @return
     */
    public String get(String httpAddr) {
        return getExRetry(httpAddr, 0);
    }

    /**
     * http get请求, 异常重试
     * @param httpAddr
     * @param retry
     * @return
     */
    public String getExRetry(String httpAddr, int retry) {
        return getExRetry(httpAddr, DEFAULT_CHARSET, retry);
    }

    private String getExRetry(String httpAddr, String charset, int retry) {
        for (int num = 0; num <= retry; num++) {
            if (num > 0) {
                logger.info("HTTPLongClient4.getExRetry: Retrying request ={}, retryCount={}",
                        httpAddr, num);
            }
            try {
                return get(httpAddr, charset);
            } catch (Exception e) {
                logger.error("HTTPLongClient4 httpget error: request={}", httpAddr, e);
            }
        }
        return null;
    }

    /**
     * @param httpAddr
     * @param charset
     * @return
     * @throws IOException
     * @throws ClientProtocolException
     */
    private String get(String httpAddr, String charset) throws IOException {
        if (StringUtils.isBlank(httpAddr)) {
            return null;
        }
        HttpGet httpGet = new HttpGet(httpAddr);
        CloseableHttpResponse response = null;
        String result = null;
        try {
            response = getHttpClient().execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                result = EntityUtils.toString(response.getEntity(), charset);
            } else {
                logger.error("HTTPLongClient4 error status code : {} , httpAddr:{}", statusCode,
                        httpAddr);
            }
            return result;
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    logger.error("HTTPLongClient4 response.close , httpAddr={}", httpAddr, e);
                }
            }
        }
    }

    /**
     * http post请求
     *
     * @param httpAddr
     *            请求地址
     * @param params
     *            传递参数
     * @return
     */
    public String post(String httpAddr, Map<String, Object> params) {
        return postExRetry(httpAddr, params, 0);
    }

    public String postExRetry(String httpAddr, Map<String, Object> params, int retry) {
        return postExRetry(httpAddr, params, DEFAULT_CHARSET, retry);
    }

    private String postExRetry(String httpAddr, Map<String, Object> params, String charset,
                               int retry) {
        for (int num = 0; num <= retry; num++) {
            if (num > 0) {
                logger.info("HTTPLongClient4.postExRetry: Retrying request ={}, retryCount={}",
                        httpAddr, num);
            }
            try {
                return post(httpAddr, params, charset);
            } catch (Exception e) {
                logger.error("HTTPLongClient4 httppost error: request={}", httpAddr, e);
            }
        }
        return null;
    }

    /**
     * @param httpAddr
     * @param params 支持List作为值来传递数组参数
     * @param charset
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     * @throws ParseException
     * @throws Exception
     */
    private String post(String httpAddr, Map<String, Object> params, String charset)
            throws IOException {
        if (StringUtils.isEmpty(httpAddr)) {
            return null;
        }
        List<BasicNameValuePair> pairs = null;
        if (params != null && !params.isEmpty()) {
            pairs = new ArrayList<BasicNameValuePair>(params.size());
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                Object value = entry.getValue();
                if (value instanceof List) {
                    List<Object> list = (List<Object>) value;
                    for (Object object : list) {
                        pairs.add(new BasicNameValuePair(entry.getKey(), String.valueOf(object)));
                    }
                } else {
                    pairs.add(new BasicNameValuePair(entry.getKey(), String.valueOf(value)));
                }
            }
        }
        HttpPost httpPost = new HttpPost(httpAddr);
        if (pairs != null && pairs.size() > 0) {
            try {
                httpPost.setEntity(new UrlEncodedFormEntity(pairs, charset));
            } catch (UnsupportedEncodingException e) {
                logger.error("HTTPLongClient4 UnsupportedEncodingException , httpAddr={}",
                        httpAddr, e);
                return null;
            }
        }
        CloseableHttpResponse response = null;
        String result = null;
        try {
            response = getHttpClient().execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                result = EntityUtils.toString(response.getEntity(), charset);
            } else {
                logger.error("HTTPLongClient4 error statuscode={} , httpAddr={}", statusCode,
                        httpAddr);
            }
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    logger.error("HTTPLongClient4 response.close error, httpAddr={}", httpAddr, e);
                }
            }
        }
        return result;
    }

    /**
     * 文件上传请求
     *
     * @param httpAddr
     * @param fileFieldName
     * @param file
     * @param params
     * @return
     */
    public String postfile(String httpAddr, Map<String, Object> params) {
        return postfile(httpAddr, params, DEFAULT_CHARSET);
    }

    private String postfile(String httpAddr, Map<String, Object> params, String charset) {
        if (StringUtils.isEmpty(httpAddr)) {
            return null;
        }
        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
        if (params != null && !params.isEmpty()) {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                Object value = entry.getValue();
                if (value instanceof File) {
                    multipartEntityBuilder.addPart(entry.getKey(), new FileBody((File) value));
                } else if (value instanceof byte[]) {
                    multipartEntityBuilder.addBinaryBody(entry.getKey(), (byte[]) value);
                } else if (value instanceof List) {
                    List<Object> list = (List<Object>) value;
                    for (Object object : list) {
                        if (object instanceof File) {
                            multipartEntityBuilder.addPart(entry.getKey(), new FileBody(
                                    (File) object));
                        } else {
                            multipartEntityBuilder
                                    .addPart(entry.getKey(), new StringBody(String.valueOf(object),
                                            ContentType.APPLICATION_JSON));
                        }
                    }
                } else {
                    multipartEntityBuilder.addPart(entry.getKey(),
                            new StringBody(String.valueOf(value), ContentType.APPLICATION_JSON));
                }
            }
        }
        HttpPost httpPost = new HttpPost(httpAddr);
        httpPost.setEntity(multipartEntityBuilder.build());
        CloseableHttpResponse response = null;
        String result = null;
        try {
            response = getHttpClient().execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                httpPost.abort();
                logger.error("HTTPLongClient4 error status code :{} , httpAddr={}", statusCode,
                        httpAddr);
            }
            result = EntityUtils.toString(response.getEntity(), charset);
        } catch (IOException e) {
            logger.error("HTTPLongClient4 IOException , httpAddr={}", httpAddr, e);
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    logger.error("HTTPLongClient4 response.close error, httpAddr={}", httpAddr, e);
                }
            }
        }
        return result;
    }

    @Override
    public void close() {
        try {
            httpClient.close();
        } catch (IOException e) {
            logger.error("HTTPLongClient4 httpClient.close error.", e);
        }
    }
}