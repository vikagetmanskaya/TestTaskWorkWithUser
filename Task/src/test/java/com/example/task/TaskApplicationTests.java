package com.example.task;

import com.example.task.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = TaskApplication.class)
@WebAppConfiguration
class TaskApplicationTests {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    ObjectMapper objectMapper = new ObjectMapper();

    @PostConstruct
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testGetById() throws Exception {
        this.mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("User{" +
                        "username='" + "ivan_ivanov" + '\'' + "\n" +
                        "email='" + "ivan@gmail.com" + '\'' + "\n" +
                        "password='" + "12345" + '\'' + "\n" +
                        "phoneNumber='" + "+375296666666" + '\'' + "\n" +
                        "urlPhoto='" + "https://yandex.by/images/search?img_url=http%3A%2F%2Fmobimg.b-cdn.net%2Fv3%2Ffetch%2F36%2F36b936cd1fb8b0523e029ab76bae3373.jpeg&lr=157&pos=0&rpt=simage&source=serp&text=%D0%BA%D0%B0%D1%80%D1%82%D0%B8%D0%BD%D0%BA%D0%B0" + '\'' + "\n" +
                        '}'));


    }

    @Test
    public void testAddUser() throws Exception {
        User user = new User();
        user.setUsername("ivan5_ivanov");
        user.setEmail("ivan5@gmail.com");
        user.setPassword("12345");
        user.setPhoneNumber("+375296666666");
        user.setUrlPhoto("https://yandex.by/images/search?img_url=http%3A%2F%2Fmobimg.b-cdn.net%2Fv3%2Ffetch%2F36%2F36b936cd1fb8b0523e029ab76bae3373.jpeg&lr=157&pos=0&rpt=simage&source=serp&text=%D0%BA%D0%B0%D1%80%D1%82%D0%B8%D0%BD%D0%BA%D0%B0");

        this.mockMvc.perform(post("/users").
                        content(objectMapper.writeValueAsString(user)).
                        contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().string("User id: 7"));
    }

    @Test
    public void testUpdateStatus() throws Exception {
        this.mockMvc.perform(patch("/users/1/Online"))
                .andExpect(status().isOk())
                .andExpect(content().string("User id: 1" +
                        ", user status before: " + "Online" + ", user status now: " +
                        "Online"));
    }


}
