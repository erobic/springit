package com.erobic.springit.services;

import com.erobic.springit.AbstractSpringTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class InitializingBeanDemoTest extends AbstractSpringTest{
    @Autowired
    InitializingBeanDemo initializingBeanDemo;

    @Test
    public void testGetInitialized() throws Exception {
        assertThat(initializingBeanDemo.getInitialized()).isTrue();
    }
}