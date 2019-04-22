package com.alan.storkcaramelapplication.services;

import com.alan.storkcaramelapplication.commands.StudentProfileCommand;
import com.alan.storkcaramelapplication.domain.StudentProfile;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
public interface StudentProfileService {

    Set<StudentProfile> getStudentProfile();
    StudentProfile findById(Long l);
    StudentProfileCommand saveStudentProfileCommand( StudentProfileCommand studentProfileCommand);
    StudentProfileCommand findCommandById(Long l);
    void deleteById(Long l);

}
