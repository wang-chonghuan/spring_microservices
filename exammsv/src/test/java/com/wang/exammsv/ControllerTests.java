package com.wang.exammsv;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testRegisterExam() throws Exception {
        String url = "http://localhost:8090/course/registerexam";
        String content = "{\"examId\":1,\"studentIdList\":[1,2]}";
        mockMvc.perform(post(url).content(content)
                .header(HttpHeaders.CONTENT_TYPE, "application/json"))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void testPostAnsweredPaper() throws Exception {
        String url = "http://localhost:8090/exam/postanswers";
        String content = "{\"studentId\":1,\"examId\":1,\"answerList\":[{\"questionId\":1,\"order\":1,\"mark\":5,\"answer\":\"B\"},{\"questionId\":2,\"order\":2,\"mark\":3,\"answer\":\"A,B,D\"},{\"questionId\":3,\"order\":3,\"mark\":2,\"answer\":\"Robe Pike\"}]}";
        mockMvc.perform(post(url).content(content)
                .header(HttpHeaders.CONTENT_TYPE, "application/json"))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void testCreatePaper() throws Exception {
        String url = "http://localhost:8090/paper/createpaper";
        String content = "{\"examId\":1,\"questionSettingList\":[{\"questionId\":1,\"order\":1,\"mark\":2},{\"questionId\":2,\"order\":2,\"mark\":3},{\"questionId\":3,\"order\":3,\"mark\":5}]}";
        mockMvc.perform(post(url).content(content)
                .header(HttpHeaders.CONTENT_TYPE, "application/json"))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void testFetchBlankpaper() throws Exception {
        testCreatePaper();
        String expectedResult = "{\"blank_question_list\":[{\"question\":{\"id\":1,\"refAnswer\":\"B\",\"tags\":\"golang\",\"questionType\":\"SINGLE\",\"questionContent\":{\"statement\":\"whichyearisgolangfirstreleased?\",\"choices\":[\"A.2010\",\"B.2007\",\"C.2012\",\"D.2017\",\"E.2018\"]}},\"questionSetting\":{\"questionId\":1,\"order\":1,\"mark\":2.0}},{\"question\":{\"id\":2,\"refAnswer\":\"A,B,D\",\"tags\":\"golang\",\"questionType\":\"MULTIPLE\",\"questionContent\":{\"statement\":\"whichyearisNOTgolangfirstreleased?\",\"choices\":[\"A.2010\",\"B.2011\",\"C.2007\",\"D.2017\"]}},\"questionSetting\":{\"questionId\":2,\"order\":2,\"mark\":3.0}},{\"question\":{\"id\":3,\"refAnswer\":\"RobertGriesemer,RobPike,andKenThompson\",\"tags\":\"golang,programminglanguage\",\"questionType\":\"WRITING\",\"questionContent\":{\"statement\":\"Whocreatedgolang?\"}},\"questionSetting\":{\"questionId\":3,\"order\":3,\"mark\":5.0}}]}";
        mockMvc.perform(MockMvcRequestBuilders
                        .get("http://localhost:8090/exam/fetchblankpaper?paperId={paperId}", 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
                //.andExpect(MockMvcResultMatchers.content().bytes(expectedResult.getBytes()));
    }
}
