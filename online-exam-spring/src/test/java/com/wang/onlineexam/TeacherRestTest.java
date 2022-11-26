package com.wang.onlineexam;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TeacherRestTest {
    @Autowired
    private MockMvc mockMvc;

    // test post for url: http://localhost:8080/create-teachers, which is in TeacherController
    @Test
    public void testPostTeacher() throws Exception {
        ResultActions r = this.mockMvc.perform(post("/create-teacher")
                .content("{\"name\":\"test.teacher1.name\", \"email\":\"test.teacher1.email\"}")
                .header(HttpHeaders.CONTENT_TYPE, "application/json"));
        r = r.andDo(print()).andExpect(status().isCreated());
    }

    // test post for url: http://localhost:8080/data-api/teachers, which is a Spring Data Rest auto api
    @Test
    public void testDataApiPostTeacherDrapi() throws Exception {
        ResultActions r = this.mockMvc.perform(post("/data-api/teachers")
                .content("{\"name\":\"test.teacher2.name\", \"email\":\"test.teacher2.email\"}")
                .header(HttpHeaders.CONTENT_TYPE, "application/json"));
        r = r.andDo(print()).andExpect(status().isCreated());
    }
}
