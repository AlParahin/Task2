package com.example.task2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UtilsTest {
    private static FractionTest fraction;
    private static Fractionable fractionProxy;

    @BeforeEach
    void newTestObjects() {
        fraction = new FractionTest(1, 2);
        fractionProxy = Utils.cache(fraction);
    }

    @Test
    @DisplayName("Без кеширования: количество реальных выполнений > 1")
    void test_0_RealCounter() {
        fraction.doubleValue();
        fraction.doubleValue();
        Assertions.assertEquals(2, fraction.counter);
    }

    @Test
    @DisplayName("Без кеширования: проверка результата")
    void test_1_RealResult() {
        Assertions.assertEquals(0.5, fraction.doubleValue());
    }

    @Test
    @DisplayName("С кешированием: выполнение 1 раз")
    void test_2_CachedMethod_1() {
        fractionProxy.doubleValue();
        fractionProxy.doubleValue();
        fractionProxy.doubleValue();
        Assertions.assertEquals(1, fraction.counter);
    }

    @Test
    @DisplayName("С кешированием 1 операции: проверка результата")
    void test_3_CachedMethodResult() {
        fractionProxy.doubleValue();
        fractionProxy.doubleValue();
        fractionProxy.doubleValue();
        Assertions.assertEquals(0.5, fractionProxy.doubleValue());
    }

    @Test
    @DisplayName("С кешированием 2 операций: 2 выполнения")
    void test_4_CachedMethod_2() {
        fractionProxy.toString();
        fractionProxy.doubleValue();
        fractionProxy.doubleValue();
        fractionProxy.toString();
        fractionProxy.doubleValue();
        fractionProxy.toString();
        Assertions.assertEquals(2, fraction.counter);
    }

    @Test
    @DisplayName("С кешированием + Mutator: 2 выполнения")
    void test_5_MutatedMethod() {
        fractionProxy.doubleValue();
        fractionProxy.doubleValue();
        fractionProxy.doubleValue();
        fractionProxy.setNum(3);
        fractionProxy.doubleValue();
        fractionProxy.doubleValue();
        fractionProxy.doubleValue();
        Assertions.assertEquals(2, fraction.counter);
    }

    @Test
    @DisplayName("С кешированием + Mutator: проверка результата (новый)")
    void test_6_MutatedMethodResult() {
        fractionProxy.doubleValue();
        fractionProxy.doubleValue();
        fractionProxy.doubleValue();
        fractionProxy.setNum(3);
        fractionProxy.doubleValue();
        fractionProxy.doubleValue();
        fractionProxy.doubleValue();
        Assertions.assertEquals(1.5, fractionProxy.doubleValue());
    }

    @Test
    @DisplayName("C кешированием, но без Mutator: 1 выполнение")
    void test_7_SimpleMethod() {
        fractionProxy.doubleValue();
        fractionProxy.doubleValue();
        fractionProxy.setDenum(3);
        fractionProxy.doubleValue();
        fractionProxy.doubleValue();
        Assertions.assertEquals(1, fraction.counter);
    }

    @Test
    @DisplayName("С кешированием, без Mutator: результат старый")
    void test_8_SimpleMethodResult() {
        fractionProxy.doubleValue();
        fractionProxy.doubleValue();
        fractionProxy.setDenum(3);
        fractionProxy.doubleValue();
        fractionProxy.doubleValue();
        Assertions.assertEquals(0.5, fractionProxy.doubleValue());
    }

    @Test
    @DisplayName("Исходное состояние: не вызывает кеширования")
    void test_9_Initial() {
        Assertions.assertEquals(0, fraction.counter);
    }

    @Test
    @DisplayName("Простой метод: не вызывает кеширования")
    void test_A_SimpleMethod() {
        fractionProxy.setDenum(3);
        Assertions.assertEquals(0, fraction.counter);
    }

}