package org.rick.useful;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Slf4jDemo {
    private static final Logger logger = LoggerFactory.getLogger(Slf4jDemo.class);

    public static void main(String[] args) {
        logger.info("Current Time: {} \n a new line", System.currentTimeMillis());
        logger.info("Current Time: " + System.currentTimeMillis());
        logger.info("Current Time: {}", System.currentTimeMillis());
        logger.trace("trace log");
        logger.warn("warn log");
        logger.debug("debug log");
        logger.info("info log");
        logger.error("error log");
        String id="id1";
        String symbol="symbol1";
        logger.debug("slf4j的占位符: {} and symbol : {} ", id, symbol);

    }
}