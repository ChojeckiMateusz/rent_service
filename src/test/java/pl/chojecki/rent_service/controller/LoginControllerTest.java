package pl.chojecki.rent_service.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import pl.chojecki.rent_service.model.User;
import pl.chojecki.rent_service.service.UserService;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @Test
    void testLogin() throws Exception {
        //given
        User user = new User();
        user.setUsername("test");
        user.setPassword("test");
        userService.addUser(user);
        //when
        MvcResult login = mockMvc.perform(post("/login")
                .content("{\"username\": \"test\", \"password\": \"test\"}"))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();

        String token = login.getResponse().getHeader("Authorization");
        //then
        mockMvc.perform(get("/secured")
        )
                .andDo(print())
                .andExpect(status().is(401));

        mockMvc.perform(get("/secured")
                .header("Authorization", token)
        )
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(content().string("secured"));
    }
}