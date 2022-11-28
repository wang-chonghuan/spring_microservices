package com.wang.teachermsrv;

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
public class CreateExamControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private final String blankpaperJson = "{\"examId\":1,\"questionSettingList\":[{\"questionId\":1,\"order\":1,\"mark\":2},{\"questionId\":2,\"order\":2,\"mark\":3},{\"questionId\":3,\"order\":3,\"mark\":5}]}";
    private final String studentExamJson = "{\"examId\":1,\"studentIdList\":[1,2]}";

    @Test
    public void testCreateBlankpaper() throws Exception {
        ResultActions r = this.mockMvc.perform(post("http://localhost:8090/exam/blankpaper")
                .content(blankpaperJson)
                .header(HttpHeaders.CONTENT_TYPE, "application/json"));
        r = r.andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void testRegisterStudentsToExam() throws Exception {
        ResultActions r = this.mockMvc.perform(post("http://localhost:8090/exam/student")
                .content(studentExamJson)
                .header(HttpHeaders.CONTENT_TYPE, "application/json"));
        r = r.andDo(print()).andExpect(status().isOk());
    }
}
