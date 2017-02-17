/**
 * Created by yzy on 2017/2/15.
 */
public class Main {
    public static void main(String[] args){
        String url = "http://ss51.sinaimg.cn/wap180/6d184dffjw1e3qmdaq1sij&690";
        url = url.replaceAll("(http|ftp|https)(:\\/\\/)(ss)([0-9]+)(\\.[\\w\\-_]+)([\\w\\-\\.,@?^=%&:/~\\+#]+)(&690)",
                "$1://ww$4$5$6.jpg");
        System.out.println(url);

//        String url2 = "http://ss51.sinaimg.cn/wap180/6d184dffjw1e3qmdaq1sij&690";
//        url2 = url2.replaceAll("^http://ss","http://ww").replaceAll("&690$",".jpg");
//        System.out.println(url2);
    }
}
