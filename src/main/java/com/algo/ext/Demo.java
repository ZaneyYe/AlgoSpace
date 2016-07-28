package com.algo.ext;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.map.HashedMap;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by yzy on 2016/7/28.
 */
public class Demo {

    public static String collectionToJsonStr(Class clas, Collection ec, Map<String,String> outPutFiledMap){
        if(CollectionUtils.isEmpty(ec)){
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        if(outPutFiledMap == null || outPutFiledMap.isEmpty()){
            Field[] fileds = clas.getDeclaredFields();
            outPutFiledMap = new HashMap<>();
            for(Field field : fileds){
                outPutFiledMap.put(field.getName(),field.getName());
            }
        }
        int filedSize = outPutFiledMap.size();
        int ecSize = ec.size();
        int flag = 0;
        int ecFlag = 0;
        for(Object e : ec){
            Class clazz = e.getClass();
            sb.append("{");
            for(String filedName : outPutFiledMap.keySet()){
                try {
                    Field declaredField = clazz.getDeclaredField(filedName);
                    declaredField.setAccessible(true);
                    Object filedValue = declaredField.get(e);
                    sb.append("\"");
                    sb.append(outPutFiledMap.get(filedName));
                    sb.append("\"");
                    sb.append(":");
                    sb.append("\"");
                    sb.append(String.valueOf(filedValue));
                    sb.append("\"");
                    if(++flag < filedSize){
                        sb.append(", ");
                    }
                } catch(NoSuchFieldException e1) {
                    e1.printStackTrace();
                } catch(IllegalAccessException e1) {
                    e1.printStackTrace();
                }
            }
            flag = 0 ;
            sb.append("}");
            if(++ecFlag < ecSize){
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args){
        List<Person> persons = new ArrayList<>();
        persons.add(new Person(1,"习近平"));
        persons.add(new Person(2,"彭丽媛"));

        Map<String,String> dd = new HashedMap();
        dd.put("id","value");
        dd.put("name","text");

        System.out.println(collectionToJsonStr(Person.class,persons,dd));
    }
}
