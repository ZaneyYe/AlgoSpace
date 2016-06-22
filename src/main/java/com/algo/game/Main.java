package com.algo.game;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by yzy on 2016/2/26.
 */
public class Main {

    public static void main(String[] args){
        String[] values = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
        String[] color = {"♥","♦","♣","♠"};

//        Player zy = new Player("zy",24);/**/

        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入姓名：");
        String name = scanner.next();
        System.out.println("请输入年龄：");
        int age = scanner.nextInt();
        System.out.println("发牌：");

        Player player = new Player(name,age);
        Random random = new Random();
        for(int k = 1;k <= 2;k++){
            int i = random.nextInt(12);
            int j = random.nextInt(3);
            PuKe p = new PuKe(i,j);
            player.getPukes().add(p);
        }
        player.initial();
        System.out.println("手上牌"+player.getPukes());
        System.out.println();
        System.out.println("初始获胜几率:" + player.getRate()); //初始获胜几率;

        System.out.print("翻开第一张底牌");
        int i = random.nextInt(12);
        int j = random.nextInt(3);
        PuKe p = new PuKe(i,j);
        System.out.println(p);

        double rate = CountRate.getRate(p, player);
        System.out.println("第一张底牌出现后 获胜几率："+ rate);
    }
}
