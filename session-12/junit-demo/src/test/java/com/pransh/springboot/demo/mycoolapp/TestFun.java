package com.pransh.springboot.demo.mycoolapp;

import com.pransh.springboot.demo.mycoolapp.rest.FunTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestFun {

    FunTest funTest = new FunTest();

    @Test
    void checkAddResult() {

        int expected = 6;

        int actual = funTest.add(2, 4);

        Assertions.assertEquals(expected, actual, "2+4 must be 6");
    }

    @Test
    void checkDivideResult() {
        int expected = 3;

        int actual = funTest.divide(6, 2);

        Assertions.assertEquals(expected, actual, "6/2 must be 3");
    }

}