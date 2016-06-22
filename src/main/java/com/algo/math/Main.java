package com.algo.math;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by yzy on 2016/2/26.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        double v = Math.toRadians(30);
        BigDecimal b = new BigDecimal(v);
        double value = b.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
        System.out.println(value);

//        Scanner scanner = new Scanner(System.in);
//        System.out.println("请输入：");
//        int i = scanner.nextInt();
//        while(i != 48){
//            System.out.println("再来一次");
//            i = scanner.nextInt();
//            if(i == 48)
//                break;
//        }

        String[] strs = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
        Random random = new Random();
        random.setSeed(2);
        int r1 = random.nextInt();
        System.out.println(strs[r1]);
    }
}
