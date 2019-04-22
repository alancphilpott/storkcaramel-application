package com.alan.storkcaramelapplication.repositories;

import com.alan.storkcaramelapplication.domain.SkillSet;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SkillSetRepository extends CrudRepository<SkillSet,Long> {
    Optional<SkillSet>findBySkillName(String skillName);
}
