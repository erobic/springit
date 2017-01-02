package com.erobic.springit.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * Created by robik on 12/10/16.
 */
@Component
public class InitializingBeanDemo implements InitializingBean {
    private static final Logger logger = LoggerFactory.getLogger(InitializingBeanDemo.class);
    private Boolean initialized = false;

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("afterPropertiesSet has been called!");
        initialized = true;
    }

    public Boolean getInitialized() {
        return initialized;
    }
}
