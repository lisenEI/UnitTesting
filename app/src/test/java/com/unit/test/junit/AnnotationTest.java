package com.unit.test.junit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author lisen
 * @since 12-05-2018
 */
public class AnnotationTest {

//    @Rule
//    public MyRule rule = new MyRule();

    /**
     * 类初始化执行，静态
     */
    @BeforeClass
    public static void beforeClassTest() {
        println("》》》》》BeforeClass《《《《《");
    }

    /**
     * 类销毁执行，静态
     */
    @AfterClass
    public static void afterClassTest() {
        println("》》》》》AfterClass《《《《《");
    }

    /**
     * 每个测试方法之前执行
     */
    @Before
    public void beforeTest() {
        println("* * * Before * * *");
    }

    /**
     * 每个测试方法之后执行
     */
    @After
    public void afterTest() {
        println("* * * After * * *");
    }


    /**
     * 测试类
     */
    @Test
    public void test() {
        println("Test");
    }

    @Test
    public void test1() {
        println("Test1");
    }
//
//    /**
//     * 忽略
//     */
//    @Test
//    @Ignore
//    public void ignoreTest() {
//        println("Ignore");
//    }
//
//    /**
//     * 验证执行
//     */
//    @Test
//    @Ignore
//    public void failTest() {
//        println("fail");
//        Assert.fail("verify invoke");
//    }
//
//    /**
//     * 期望得到的异常
//     */
//    @Test(expected = NullPointerException.class)
//    @Ignore
//    public void expectedTest() {
//        println("expected");
//        throw new NullPointerException("");
//    }
//
//    /**
//     * 超时处理
//     */
//    @Test(timeout = 500)
//    @Ignore
//    public void timeoutTest() {
//        println("timeout");
//        try {
//            Thread.sleep(200);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
    public static void println(String msg) {
        System.out.println(msg);
    }
}
