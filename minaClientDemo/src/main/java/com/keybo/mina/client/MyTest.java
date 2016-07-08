package com.keybo.mina.client;

/**
 * @author: xiaojian
 * @date: 16-7-5
 * @time: 下午7:39
 * @version: V1.0
 */
public class MyTest {

    public static void main(String[] args){
        for(int i = 0; i < 1; i++){
            new Thread(new CalculatorClient(i)).start();
        }
    }

}
