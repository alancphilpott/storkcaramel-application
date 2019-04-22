package com.alan.storkcaramelapplication.services;

import com.alan.storkcaramelapplication.commands.WorkExperienceCommand;
import com.alan.storkcaramelapplication.converters.UnitOfDurationCommandToUnitOfDuration;
import com.alan.storkcaramelapplication.converters.UnitOfDurationToUnitOfDurationCommand;
import com.alan.storkcaramelapplication.converters.WorkExperienceCommandToWorkExperience;
import com.alan.storkcaramelapplication.converters.WorkExperienceToWorkExperienceCommand;
import com.alan.storkcaramelapplication.domain.StudentProfile;
import com.alan.storkcaramelapplication.domain.WorkExperience;
import com.alan.storkcaramelapplication.repositories.StudentProfileRepository;
import com.alan.storkcaramelapplication.repositories.UnitOfDurationRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class WorkExperienceServiceImplTest {
    private final WorkExperienceCommandToWorkExperience workExperienceCommandToWorkExperience;
    private final WorkExperienceToWorkExperienceCommand workExperienceToWorkExperienceCommand;

    @Mock
    StudentProfileRepository studentProfileRepository;

    @Mock
    UnitOfDurationRepository unitOfDurationRepository;

    WorkExperienceService workExperienceService;

    //init converters
      public WorkExperienceServiceImplTest() {
        this.workExperienceCommandToWorkExperience = new WorkExperienceCommandToWorkExperience(new UnitOfDurationCommandToUnitOfDuration());
        this.workExperienceToWorkExperienceCommand = new WorkExperienceToWorkExperienceCommand(new UnitOfDurationToUnitOfDurationCommand());
    }

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        workExperienceService = new WorkExperienceServiceImpl(workExperienceToWorkExperienceCommand,studentProfileRepository,workExperienceCommandToWorkExperience,unitOfDurationRepository);
    }

    @Test
    public void findByRecipeIdAndId() throws Exception {
    }

    @Test
    public void findByStudentIdAndWorkExperienceId() throws Exception {
        //given
        StudentProfile  studentProfile = new StudentProfile();
        studentProfile.setId(1L);

        WorkExperience workExperience = new WorkExperience();
        workExperience.setId(1L);

        WorkExperience workExperience1 = new WorkExperience();
        workExperience1.setId(1L);

        WorkExperience workExperience2 = new WorkExperience();
        workExperience2.setId(3L);

        studentProfile.addWorkExperience(workExperience);
        studentProfile.addWorkExperience(workExperience1);
        studentProfile.addWorkExperience(workExperience2);
        Optional<StudentProfile> studentProfileOptional = Optional.of(studentProfile);

        when(studentProfileRepository.findById(anyLong())).thenReturn(studentProfileOptional);

        //then
        WorkExperienceCommand workExperienceCommand  = workExperienceService.findByStudentIdAndWorkExperienceId(1L, 3L);

        //when
        assertEquals(Long.valueOf(3L), workExperienceCommand.getId());
        assertEquals(Long.valueOf(1L), workExperienceCommand.getStudentProfileId());
        verify(studentProfileRepository, times(1)).findById(anyLong());
    }


    @Test
    public void testSaveWorkExperienceCommand() throws Exception {
        //given
        WorkExperienceCommand command = new WorkExperienceCommand();
        command.setId(3L);
        command.setStudentProfileId(2L);

        Optional<StudentProfile> studentProfileOptional = Optional.of(new StudentProfile());

        StudentProfile savedStudentProfile = new StudentProfile();
        savedStudentProfile.addWorkExperience(new WorkExperience());
        savedStudentProfile.getWorkExperiences().iterator().next().setId(3L);

        when(studentProfileRepository.findById(anyLong())).thenReturn(studentProfileOptional);
        when(studentProfileRepository.save(any())).thenReturn(savedStudentProfile);

        //when
        WorkExperienceCommand savedCommand = workExperienceService.saveWorkExperience(command,2L);

        //then
        assertEquals(Long.valueOf(3L), savedCommand.getId());
        verify(studentProfileRepository, times(1)).findById(anyLong());
        verify(studentProfileRepository, times(1)).save(any(StudentProfile.class));

    }
}
