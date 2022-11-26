package com.wang.onlineexam;

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

@SpringBootTest
@AutoConfigureMockMvc
public class ExamRestTest {
    @Autowired
    private MockMvc mockMvc;

    private final String correctResultForFetch = "{\"writing_question_list\":[{\"questionId\":3,\"type\":\"WRITING\",\"refAnswer\":\"Robert Griesemer, Rob Pike, and Ken Thompson\",\"content\":{\"statement\":\"Who created golang?\"},\"order\":3,\"mark\":2.0,\"score\":0.0,\"answer\":\"Robe Pike\"}]}";

    // test post for url: http://localhost:8080/create-blank-paper
    @Test
    public void testCreateBlankPaper() throws Exception {
        ResultActions r = this.mockMvc.perform(post("/create-blank-paper")
                .content("{\"examId\":1,\"paramList\":[{\"questionId\":1,\"order\":1,\"mark\":5},{\"questionId\":2,\"order\":2,\"mark\":3},{\"questionId\":3,\"order\":3,\"mark\":2}]}")
                .header(HttpHeaders.CONTENT_TYPE, "application/json"));
        r = r.andDo(print()).andExpect(status().isOk());
    }

    // todo! how to make sure this answeredPaper is the same with the blank paper? there should be a verification, at least the blank paper is set
    // todo! if the score field is not set in the requestBody, will the system crash by null pointer exception?
    @Test
    public void testCreateAnsweredPaper() throws Exception {
        ResultActions r = this.mockMvc.perform(post("/create-answered-paper")
                .content("{\"studentId\":1,\"examId\":1,\"paramList\":[{\"questionId\":1,\"order\":1,\"mark\":5,\"answer\":\"B\"},{\"questionId\":2,\"order\":2,\"mark\":3,\"answer\":\"A,B,D\"},{\"questionId\":3,\"order\":3,\"mark\":2,\"answer\":\"Robe Pike\"}]}")
                .header(HttpHeaders.CONTENT_TYPE, "application/json"));
        r = r.andDo(print()).andExpect(status().isOk());
    }

    // todo
    @Test
    public void testFetchWritingQuestions() throws Exception {

        testCreateBlankPaper();
        testCreateAnsweredPaper();

        mockMvc.perform(MockMvcRequestBuilders
            .get("/fetch-writing-questions?studentId={studentId}&examId={examId}", 1, 1)
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.content().bytes(this.correctResultForFetch.getBytes()));
            //.andExpect(MockMvcResultMatchers.jsonPath("$.employees").exists())
            //.andExpect(MockMvcResultMatchers.jsonPath("$.employees[*].employeeId").isNotEmpty())
            //.andExpect(MockMvcResultMatchers.jsonPath("$.employeeId").value(1));
    }

    // todo! must make sure when only the necessary fields are set, the other fields in db will not be changed
    @Test
    public void testUpdateWritingScores() throws Exception {

        testFetchWritingQuestions();

        ResultActions r = this.mockMvc.perform(post("/update-writing-scores")
                .content("{\"studentId\":1,\"examId\":1,\"paramList\":[{\"questionId\":3,\"order\":3,\"mark\":2,\"score\":1.5}]}")
                .header(HttpHeaders.CONTENT_TYPE, "application/json"));
        r = r.andDo(print()).andExpect(status().isOk());
    }
}
