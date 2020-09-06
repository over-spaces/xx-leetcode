package com.learning;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public abstract class BaseTest {

    protected static Logger logger = LoggerFactory.getLogger(BaseTest.class);

    protected static void sleep(long timeout){
        try {
            TimeUnit.SECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            logger.error(e.getMessage(), e);
        }
    }

}
