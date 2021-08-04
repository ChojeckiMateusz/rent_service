package pl.chojecki.rent_service.service.implementation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.chojecki.rent_service.model.User;
import pl.chojecki.rent_service.service.UserService;

import javax.transaction.Transactional;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    void addUser() {
        //given
        User user = new User();
        user.setUsername("test");
        user.setPassword("test");
        //when
        User newUser = userService.addUser(user);
        //then
        assertThat(newUser).isNotNull();
        assertThat(newUser.getUsername()).isEqualTo(user.getUsername());
    }

    @Test
    void findUserByUsername() {
        //given
        User user = new User();
        user.setUsername("test");
        user.setPassword("test");
        User newUser = userService.addUser(user);
        //when
        User foundUser = userService.findUserByUsername(newUser.getUsername()).orElseThrow();
        //then
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getId()).isEqualTo(newUser.getId());
    }

    @Test
    void findUserById() {
        //given
        User user = new User();
        user.setUsername("test");
        user.setPassword("test");
        User newUser = userService.addUser(user);
        //when
        User foundUser = userService.findUserById(newUser.getId()).orElseThrow();
        //then
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getId()).isEqualTo(newUser.getId());
    }
}