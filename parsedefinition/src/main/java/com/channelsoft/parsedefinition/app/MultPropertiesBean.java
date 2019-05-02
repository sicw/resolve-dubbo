package com.channelsoft.parsedefinition.app;

public class MultPropertiesBean {
    private String name;
    private String age;
    private SimpleBean simpleBean;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public SimpleBean getSimpleBean() {
        return simpleBean;
    }

    public void setSimpleBean(SimpleBean simpleBean) {
        this.simpleBean = simpleBean;
    }

    @Override
    public String toString() {
        return "MultPropertiesBean{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", simpleBean=" + simpleBean +
                '}';
    }
}
