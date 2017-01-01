package com.erobic.springit.repositories;

import com.erobic.springit.generator.DataGenerator;
import com.erobic.springit.entities.User;
import com.erobic.springit.utils.RequestContextUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void findByUsernameReturnsUser() throws Exception {
        //given
        User user = DataGenerator.generateUser();
        RequestContextUtil.setUsername(user.getUsername());
        entityManager.persistAndFlush(user);
        //when
        User foundUser = userRepository.findByUsername(user.getUsername());
        //then
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getId()).isNotNull();
    }
}