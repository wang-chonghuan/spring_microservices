package com.wang.exammsv;

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
public class ControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testRegisterExam() throws Exception {
        String url = "http://localhost:8090/course/registerexam";
        String content = "{\"examId\":1,\"studentIdList\":[1,2]}";
        ResultActions r = this.mockMvc.perform(post(url).content(content)
                .header(HttpHeaders.CONTENT_TYPE, "application/json"));
        r = r.andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void testPostAnsweredPaper() throws Exception {
        String url = "http://localhost:8090/exam/postanswers";
        String content = "{\"studentId\":1,\"examId\":1,\"answerList\":[{\"questionId\":1,\"order\":1,\"mark\":5,\"answer\":\"B\"},{\"questionId\":2,\"order\":2,\"mark\":3,\"answer\":\"A,B,D\"},{\"questionId\":3,\"order\":3,\"mark\":2,\"answer\":\"Robe Pike\"}]}";
        ResultActions r = this.mockMvc.perform(post(url).content(content)
                .header(HttpHeaders.CONTENT_TYPE, "application/json"));
        r = r.andDo(print()).andExpect(status().isOk());
    }
}
