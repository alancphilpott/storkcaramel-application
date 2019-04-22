package com.alan.storkcaramelapplication.services;

import com.alan.storkcaramelapplication.commands.SkillSetCommand;

public interface SkillSetService {
    SkillSetCommand findByStudentIdAndSkillSet(Long studentId, Long skillId);
}
