package com.service.demo;

import com.algo.mapper.CustomerJsonSerializer;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.util.Arrays;

/**
 * Created by yzy on 2017/2/13.
 */
public class JsonReturnHandler implements HandlerMethodReturnValueHandler {
    @Override
    public boolean supportsReturnType(MethodParameter methodParameter) {
        return methodParameter.getMethodAnnotation(JsonResponseParam.class) != null;
    }

    @Override
    public void handleReturnValue(Object returnValue,
                                  MethodParameter methodParameter,
                                  ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest) throws Exception {
        modelAndViewContainer.setRequestHandled(true);

        HttpServletResponse response = (HttpServletResponse) nativeWebRequest.getNativeResponse();

        Annotation[] annotations = methodParameter.getMethodAnnotations();
        CustomerJsonSerializer serializer = new CustomerJsonSerializer();
        Arrays.asList(annotations).forEach(a ->{
                if(a instanceof JsonResponseParam){
                    JsonResponseParam json = (JsonResponseParam) a;
                    serializer.filter(json.type(),json.include(),json.filter());
                }
            }
        );
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        String json = serializer.toJson(returnValue);
        response.getWriter().write(json);
    }

}
