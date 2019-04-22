package com.alan.storkcaramelapplication.services;

import com.alan.storkcaramelapplication.commands.WorkExperienceCommand;
import org.springframework.stereotype.Service;

@Service
public interface WorkExperienceService {
    WorkExperienceCommand findByStudentIdAndWorkExperienceId(Long studentId, Long WorkId);
    WorkExperienceCommand saveWorkExperience(WorkExperienceCommand command, Long studentId);
    void deleteByIdWorkExperience(Long studentId, Long idToDelete);
}