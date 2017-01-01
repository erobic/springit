package com.erobic.springit;

import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by robik on 1/1/17.
 */
@RunWith(SpringRunner.class)
@ActiveProfiles(value = "test")
@ContextConfiguration(classes = TestApplication.class)
public abstract class AbstractSpringTest {
}
