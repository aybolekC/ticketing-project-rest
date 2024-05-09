package com.aya.controller;

import com.aya.dto.ProjectDTO;
import com.aya.dto.RoleDTO;
import com.aya.dto.UserDTO;
import com.aya.enums.Gender;
import com.aya.enums.Status;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class ProjectControllerTest {

    @Autowired
    private MockMvc mvc;

    static UserDTO userDTO;
    static ProjectDTO projectDTO;

    static  String token;

    @BeforeAll
    static void setUp(){

        token="Bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJrclR5TXd5SjMxamV2VS1CQmRia2k5bkUwV2dvRnFYTWNOQ1Q4R2o0SWM4In0.eyJleHAiOjE3MTUyNTU0OTcsImlhdCI6MTcxNTIzNzQ5NywianRpIjoiOTE3NDk4ZjUtNjJkNy00OGMwLTkzY2UtNTVjMWFkZTI0NDBlIiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgwL3JlYWxtcy9heWEtdWF0IiwiYXVkIjoiYWNjb3VudCIsInN1YiI6IjIzOTk3NjU2LTgyYmMtNDE1Yy1hNDBkLWIwMDhkOWVlOGY4ZCIsInR5cCI6IkJlYXJlciIsImF6cCI6InRpY2tldGluZyIsInNlc3Npb25fc3RhdGUiOiI2NDg4NTc4ZS01NGRhLTQ4NTYtYThhNy1mNjIxMTE3NDNmNzciLCJhY3IiOiIxIiwiYWxsb3dlZC1vcmlnaW5zIjpbImh0dHA6Ly9sb2NhbGhvc3Q6ODA4MSIsIi8qIl0sInJlYWxtX2FjY2VzcyI6eyJyb2xlcyI6WyJvZmZsaW5lX2FjY2VzcyIsInVtYV9hdXRob3JpemF0aW9uIiwiZGVmYXVsdC1yb2xlcy1heWEtdWF0Il19LCJyZXNvdXJjZV9hY2Nlc3MiOnsidGlja2V0aW5nIjp7InJvbGVzIjpbIk1hbmFnZXIiXX0sImFjY291bnQiOnsicm9sZXMiOlsibWFuYWdlLWFjY291bnQiLCJtYW5hZ2UtYWNjb3VudC1saW5rcyIsInZpZXctcHJvZmlsZSJdfX0sInNjb3BlIjoib3BlbmlkIGVtYWlsIHByb2ZpbGUiLCJzaWQiOiI2NDg4NTc4ZS01NGRhLTQ4NTYtYThhNy1mNjIxMTE3NDNmNzciLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwicHJlZmVycmVkX3VzZXJuYW1lIjoibWlrZSIsImdpdmVuX25hbWUiOiIiLCJmYW1pbHlfbmFtZSI6IiJ9.mtMEB8GzvAoejygu_YuFHQ39NDxkilu20IHue2z8GficotI-_95jYwGq0Aj2uOaWHvfzV3SbEo17_90WrokekJM8VIVAch6EY4nfDX_HnN5rdB53gU-Ms39JHcd1QX7xrXqralJ9geOZYDgPUTNTEOw42tfIHx2HKBAqERB4UxridbyrUG9uTJVoqPIQYwmCiTR4NrmTmF5B7mJpPTT1AXUKyE8twGqdxRX1Xc0kkVr5Ieie6BfHNdXhFFEXzozGU-h9AeIeT_qrvUdi5n_AzgAFUNXBLHjbmeQWmyMZhKuqOi5IyV21Y61HgXKZoCJDxmofiREUjSyagjNGPyZgGg";




        userDTO= UserDTO.builder()
                .id(2L)
                .firstName("admin_user")
                .lastName("admin_user")
                .userName("mike")
                .passWord("test123")
                .confirmPassWord("test123")
                .role(new RoleDTO(1L,"Admin"))
                .gender(Gender.MALE).build();


        projectDTO=ProjectDTO.builder()
                .projectCode("Ap01")
                .projectName("Unit Testing")
                .assignedManager(userDTO)
                .projectStartDate(LocalDate.now())
                .projectEndDate(LocalDate.now().plusDays(5))
                .projectDetail("Testing unit test")
                .projectStatus(Status.OPEN)
                .build();

    }

    @Test
    public void givenNoToken_whenGetRequest() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/api/v1/project")).andExpect(status().is4xxClientError());

    }


    @Test
    public void givenToken_whenGetRequest() throws Exception {

        mvc.perform(MockMvcRequestBuilders
                .get("/api/v1/project")
                .header("Authorization", token)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].projectCode").exists());
//                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].assignedManager.userName").isNotEmpty());

    }

    @Test
    public void givenToken_createProject() throws Exception {

        mvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/project")
                        .header("Authorization", token)
                .content(toJsonString(projectDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

    }


    @Test
    public void givenToken_updateProject() throws Exception {

        projectDTO.setProjectName("aya's project");

        mvc.perform(MockMvcRequestBuilders
                        .put("/api/v1/project")
                        .header("Authorization", token)
                        .content(toJsonString(projectDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("message")
                        .value("Project is successfully updated"));

    }



    @Test
    public void givenToken_deleteProject() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .delete("/api/v1/project/" + projectDTO.getProjectCode())
                .header("Authorization", token)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }


    private static String toJsonString(final Object obj) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            objectMapper.registerModule(new JavaTimeModule());
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

