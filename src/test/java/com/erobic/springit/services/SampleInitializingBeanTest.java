package com.erobic.springit.services;

import com.erobic.springit.AbstractSpringTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SampleInitializingBeanTest extends AbstractSpringTest{
    @Autowired
    SampleInitializingBean sampleInitializingBean;

    @Test
    public void testGetInitialized() throws Exception {
        assertThat(sampleInitializingBean.getInitialized()).isTrue();
    }
}