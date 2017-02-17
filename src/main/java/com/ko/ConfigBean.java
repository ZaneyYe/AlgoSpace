package com.ko;

/**
 * Created by yzy on 2017/2/7.
 */
public class ConfigBean {
    private String name;
    private String conf;

    public ConfigBean(){}
    public ConfigBean(String name, String conf) {
        this.name = name;
        this.conf = conf;
    }

    @Override
    public String toString() {
        return "ConfigBean{" +
                "name='" + name + '\'' +
                ", conf='" + conf + '\'' +
                '}';
    }

}
