package com.learning.juc.test;

import com.learning.BaseTest;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class T_002_CompletableFutureTest extends BaseTest {

    public static void main(String[] args) throws IOException {


        CompletableFuture<Double> future1 = CompletableFuture.supplyAsync(() -> {
            sleep(5);
            return 1.1D;
        });


        System.in.read();

    }

}
