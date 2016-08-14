package com.github.hewking.gsonformat;

/**
 * Created by hewking on 2016/2/21.
 */
public class GsomFormatDemo{

    /**
     * name : 王五
     * gender : man
     * age : 15
     * height : 140cm
     */

    private String name;
    private String gender;
    private int age;
    private String height;

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public String getHeight() {
        return height;
    }
}
