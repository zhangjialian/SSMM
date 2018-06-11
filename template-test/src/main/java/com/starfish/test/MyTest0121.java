package com.starfish.test;

public class MyTest0121 {

}

interface Animal{
    void say();
}

class People implements Animal{

    @Override
    public void say(){
        System.out.println("say");
    }
}

class PeopleProxy implements Animal{
    @Override
    public void say() {

    }

    void before(){

    }
}