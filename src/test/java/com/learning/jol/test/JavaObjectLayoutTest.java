package com.learning.jol.test;

import com.learning.BaseTest;
import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;

public class JavaObjectLayoutTest extends BaseTest {


    @Test
    public void testJavaObjectLayoutTest(){

        Object object = new Object();

        System.out.println(ClassLayout.parseInstance(object).toPrintable());

        System.out.println("--------------------------------------------------");

        synchronized (object){
            System.out.println(ClassLayout.parseInstance(object).toPrintable());
        }
    }

}
