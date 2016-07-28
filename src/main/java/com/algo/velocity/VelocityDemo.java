package com.algo.velocity;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yzy on 2016/7/18.
 */
public class VelocityDemo {

    public static void main(String[] args){
        VelocityEngine ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER,"classpath");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        ve.init();

        Template template = ve.getTemplate("hello.vm","UTF-8");
        VelocityContext vct = new VelocityContext();
        vct.put("demo","yzy");
        vct.put("dd","basketball");

        List temp = new ArrayList();
        temp.add("1");
        temp.add("2");

        vct.put("list",temp);

        StringWriter sw = new StringWriter();

        template.merge(vct,sw);

        System.out.println(sw.toString());
    }
}
