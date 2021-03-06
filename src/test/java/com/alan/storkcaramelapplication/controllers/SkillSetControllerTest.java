package com.alan.storkcaramelapplication.controllers;

import com.alan.storkcaramelapplication.commands.StudentProfileCommand;
import com.alan.storkcaramelapplication.services.SkillSetService;
import com.alan.storkcaramelapplication.services.StudentProfileService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class SkillSetControllerTest {
    @Mock
    StudentProfileService studentProfileService;
    SkillSetController controller;
    MockMvc mockMvc;
    @Mock
    SkillSetService skillSetService;
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        controller = new SkillSetController(studentProfileService,skillSetService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void listEducationalQualifications() throws Exception {
        StudentProfileCommand command = new StudentProfileCommand();
        command.setId(2L);

        when(studentProfileService.findCommandById(anyLong())).thenReturn(command);

        mockMvc.perform(get("/student/1/skillSets")).andExpect(status().isOk())
                .andExpect(view().name("student/skillSet/skillList"))
                .andExpect(model().attributeExists("student"));

        verify(studentProfileService,times(1)).findCommandById(anyLong());
    }

}