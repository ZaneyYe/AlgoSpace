package com.algo.game;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by yzy on 2016/2/26.
 */
public class CountRate {

    public static double countRate(PuKe p1,PuKe p2){
        double rate = 0.06;
        if(p1.getT() == p2.getT()){
            //凑同花
            if(Math.abs(p1.getV() - p2.getV()) <= 2){
                //凑同花顺的胜率
                rate = 0.35;
                return rate;
            }
            rate = 0.23;
            return rate;
        }else if(Math.abs(p1.getV() - p2.getV()) <= 2){
            //顺子
            rate = 0.19;
            return rate;
        }else if(p1.getV() == p2.getV()){
            //凑对子
            rate = 0.13;
            return rate;
        }else {
            return rate;
        }
    }

    //得一张牌胜率
    public static double getRate(PuKe p,Player player){
        Integer v = p.getV(); //点数
        Integer t = p.getT(); //花色
        List<PuKe> pukes = player.getPukes();  //手上两张牌
        for(PuKe k : pukes){
            double rate = countRate(k, p);
            player.setRate(rate + player.getRate());
        }
        BigDecimal rate = new BigDecimal(player.getRate());
        return rate.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
    }


}
