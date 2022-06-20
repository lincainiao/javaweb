package com.lin.servlet;

public class Person {
    private int age;
    private String name;
    public Person(){
        this.age = 22;
        this.name = "芝芝";
    }
    public void Girls(int age,String name){
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{"+
                "name='" + name +'\''+
                ", age=" + age + "}";
    }
}
