package com.alan.storkcaramelapplication.services;

import com.alan.storkcaramelapplication.commands.StudentProfileCommand;
import com.alan.storkcaramelapplication.converters.StudentProfileCommandToStudentProfile;
import com.alan.storkcaramelapplication.converters.StudentProfileToStudentProfileCommand;
import com.alan.storkcaramelapplication.domain.StudentProfile;
import com.alan.storkcaramelapplication.exceptions.NotFoundException;
import com.alan.storkcaramelapplication.repositories.StudentProfileRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class StudentProfileServiceImplTest {

    StudentProfileServiceImpl studentProfileService;

    @Mock
    StudentProfileRepository studentProfileRepository;

    @Mock
    StudentProfileCommandToStudentProfile studentProfileCommandToStudentProfile;

    @Mock
    StudentProfileToStudentProfileCommand studentProfileToStudentProfileCommand;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        studentProfileService = new StudentProfileServiceImpl(studentProfileRepository,studentProfileCommandToStudentProfile,studentProfileToStudentProfileCommand);
    }

    @Test
    public void getRecipeByIdTest() throws Exception {
        StudentProfile studentProfile = new StudentProfile();
        studentProfile.setId(1L);
        Optional<StudentProfile> studentProfileOptional = Optional.of(studentProfile);

        when(studentProfileRepository.findById(anyLong())).thenReturn(studentProfileOptional);

        StudentProfile studentProfileReturned = studentProfileService.findById(1L);

        assertNotNull("Null student Profile returned", studentProfileReturned);
        verify(studentProfileRepository, Mockito.times(1)).findById(anyLong());
        verify(studentProfileRepository, never()).findAll();
    }

    @Test(expected = NotFoundException.class)
    public void getStudentByIdTestNotFound() throws Exception {

        Optional<StudentProfile> studentProfileOptional= Optional.empty();

        when(studentProfileRepository.findById(anyLong())).thenReturn(studentProfileOptional);

        StudentProfile studentProfileReturned = studentProfileService.findById(1L);
    }
    @Test
    public void getStudentProfileCommandByIdTest() throws Exception {
        StudentProfile studentProfile= new StudentProfile();
        studentProfile.setId(1L);
        Optional<StudentProfile> studentProfileOptional = Optional.of(studentProfile);

        when(studentProfileRepository.findById(anyLong())).thenReturn(studentProfileOptional);

        StudentProfileCommand studentProfileCommand = new StudentProfileCommand();
        studentProfileCommand.setId(1L);

        when(studentProfileToStudentProfileCommand.convert(any())).thenReturn(studentProfileCommand);

        StudentProfileCommand commandById = studentProfileService.findCommandById(1L);

        assertNotNull("Null student returned", commandById);
        verify(studentProfileRepository, times(1)).findById(anyLong());
        verify(studentProfileRepository, never()).findAll();
    }
    @Test
    public void getStudentProfile() throws Exception {
        StudentProfile studentProfile = new StudentProfile();

        HashSet studentData = new HashSet();
        studentData.add(studentProfile);

        when(studentProfileService.getStudentProfile()).thenReturn(studentData);
        Set<StudentProfile> studentProfiles = studentProfileService.getStudentProfile();

        assertEquals(studentProfiles.size(),1);
        verify(studentProfileRepository, times(1)).findAll();
    }
    @Test
    public void testDeleteById() throws Exception {
        Long deleteId = Long.valueOf(2L);
        studentProfileService.deleteById(deleteId);

        verify(studentProfileRepository, Mockito.times(1)).deleteById(anyLong());
    }


}