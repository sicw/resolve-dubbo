package com.channelsoft.parsedefinition.app;

public class SimpleBean {
    private String sex;
    private String school;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    @Override
    public String toString() {
        return "SimpleBean{" +
                "sex='" + sex + '\'' +
                ", school='" + school + '\'' +
                '}';
    }
}
