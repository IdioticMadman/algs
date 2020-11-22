package com.robert;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World");
        String[] a = new String[2];
        Child child = new Child();
        System.out.println(child instanceof Parent);
        Child[] children = new Child[1];
        System.out.println(children instanceof Parent[]);
    }

    static class Child extends Parent {

    }

    static class Parent {

    }
}
