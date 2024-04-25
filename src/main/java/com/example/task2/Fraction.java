package com.example.task2;

import lombok.*;

@Getter
public class Fraction implements Fractionable {
    private int num;

    private int denum;

    public Fraction(int num, int denum) {
        this.num = num;
        this.denum = denum;
    }

    @Mutator
    public void setNum(int num) {
        this.num = num;
    }

    @Mutator
    public void setDenum(int denum) {
        this.denum = denum;
    }

    @Override
    @Cache
    public double doubleValue() {
//        System.out.println("invoke double value no cached");
        return (double) num / denum;
    }

    @Override
    @Cache
    public String toString() {
//        System.out.println("toString no cached");
        return "Fraction{" +
                "num=" + num +
                ", denum=" + denum +
                '}';
    }
}
