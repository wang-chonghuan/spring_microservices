package com.wang.studentmsrv;

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
public class ExamControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final String answersDTOString = "{\"studentId\":1,\"examId\":1,\"answerList\":[{\"questionId\":1,\"order\":1,\"mark\":5,\"answer\":\"B\"},{\"questionId\":2,\"order\":2,\"mark\":3,\"answer\":\"A,B,D\"},{\"questionId\":3,\"order\":3,\"mark\":2,\"answer\":\"Robe Pike\"}]}";

    @Test
    public void testPostAnsweredPaper() throws Exception {
        ResultActions r = this.mockMvc.perform(post("http://localhost:8090/takeexam/answer")
                .content(answersDTOString)
                .header(HttpHeaders.CONTENT_TYPE, "application/json"));
        r = r.andDo(print()).andExpect(status().isOk());
    }

}
