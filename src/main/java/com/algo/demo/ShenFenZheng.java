package com.algo.demo;

import org.apache.commons.lang.StringUtils;

import java.io.*;

/**
 * Created by yzy on 2016/9/7.
 */
public class ShenFenZheng {

    public static int[] weight = new int[]{7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2};

    public static char[] validate ={'1','0','X','9','8','7','6','5','4','3','2'};

    public static String getCardNo(){
        String cardNo = "";
        String str = "34082519910824195";
        int sum = 0;
        for(int i = 0;i < str.length();i++){
            int c = Integer.parseInt(String.valueOf(str.charAt(i)));
            sum += weight[i] * c;
        }
        System.out.println(sum);
        int index = sum % 11;
        return str + validate[index];
    }

    public static String getAreaCode(String area) throws IOException {
        String areaCode = "";
//        BufferedReader reader = new BufferedReader(new FileReader("demo.txt"));
        InputStream inputStream = ShenFenZheng.class.getClassLoader().getResourceAsStream("demo.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line = null;
        while(StringUtils.isNotBlank(line = reader.readLine())){
            String[] codes = line.split(" ");
            if(codes.length > 1 && codes[1].contains(area)){
                areaCode = codes[0];
                break;
            }
        }
        return areaCode;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(getCardNo());
//        System.out.println(getAreaCode("惠山区"));
    }


}
