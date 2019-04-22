package com.alan.storkcaramelapplication.converters;

import com.alan.storkcaramelapplication.commands.SkillSetCommand;
import com.alan.storkcaramelapplication.domain.SkillSet;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class SkillSetCommandToSkillSet implements Converter<SkillSetCommand,SkillSet> {
    @Synchronized
    @Nullable
    @Override
    public SkillSet convert(SkillSetCommand skillSetCommand) {
        if (skillSetCommand == null){
            return null;
        }
        final SkillSet skillSet = new SkillSet();

        skillSet.setId(skillSetCommand.getId());

        skillSet.setDescription(skillSetCommand.getDescription());
        skillSet.setSkillName(skillSetCommand.getSkillName());
        return skillSet;
    }
}

