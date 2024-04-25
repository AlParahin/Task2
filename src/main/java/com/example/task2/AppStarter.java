package com.example.task2;

public class AppStarter {
    public static void main(String[] args) {
        System.out.println("Hello World");

        Fractionable fraction = new Fraction(1, 8);
        System.out.println(fraction.doubleValue());
        System.out.println(fraction.doubleValue());
        fraction = Utils.cache(fraction);
        System.out.println(fraction.doubleValue());
        System.out.println(fraction.doubleValue());
        fraction.setDenum(4);
        System.out.println(fraction.doubleValue());
        System.out.println(fraction.doubleValue());
        System.out.println(fraction);
        System.out.println(fraction);

        System.out.println("That's all folks");

//        Someable someAble = new A();
//        someAble = Utils.cache(someAble);
//        someAble.doSomething();
//        someAble.doSomething();
//        System.out.println("between doing some useful but cached");
//        someAble.doSomething();
    }
}
//class A implements Someable{
//    @Override
//    @Cache
//    public void doSomething() {
//        System.out.println("Me to do something useful");
//    }
//}
