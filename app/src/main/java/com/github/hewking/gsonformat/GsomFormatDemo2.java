package com.github.hewking.gsonformat;

/**
 * Created by hewking on 2016/2/21.
 */
public class GsomFormatDemo2 {

    /**
     * name : 王五
     * gender : man
     * age : 15
     * height : 140cm
     * info : {"math":50,"chinese":54}
     */

    private String name;
    private String gender;
    private int age;
    private String height;
    /**
     * math : 50
     * chinese : 54
     */

    private InfoEntity info;

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

    public void setInfo(InfoEntity info) {
        this.info = info;
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

    public InfoEntity getInfo() {
        return info;
    }

    public static class InfoEntity {
        private int math;
        private int chinese;

        public void setMath(int math) {
            this.math = math;
        }

        public void setChinese(int chinese) {
            this.chinese = chinese;
        }

        public int getMath() {
            return math;
        }

        public int getChinese() {
            return chinese;
        }
    }
}
