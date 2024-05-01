package com.example.task2;

public class FractionTest implements Fractionable {
    int num, denum, counter;

    public FractionTest(int num, int denum) {
        this.num = num;
        this.denum = denum;
    }

    @Override
    @Cache
    public double doubleValue() {
        counter++;
        return (double) num / denum;
    }

    @Override
    @Mutator
    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public void setDenum(int denum) {
        this.denum = denum;
    }

    @Override
    @Cache
    public String toString() {
        counter++;
        return "FractionTest{" +
                "num=" + num +
                ", denum=" + denum +
                ", counter=" + counter +
                '}';
    }
}
