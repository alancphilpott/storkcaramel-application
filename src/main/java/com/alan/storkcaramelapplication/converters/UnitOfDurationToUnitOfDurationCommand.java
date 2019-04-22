package com.alan.storkcaramelapplication.converters;

import com.alan.storkcaramelapplication.commands.UnitOfDurationCommand;
import com.alan.storkcaramelapplication.domain.UnitOfDuration;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UnitOfDurationToUnitOfDurationCommand implements Converter<UnitOfDuration,UnitOfDurationCommand> {
    @Synchronized
    @Nullable
    @Override
    public UnitOfDurationCommand convert(UnitOfDuration unitOfDuration) {
//        if (unitOfDuration == null) {
//            return null;
//        }
//        final UnitOfDurationCommand unitOfDurationCommand = new UnitOfDurationCommand();
//        unitOfDurationCommand.setId(unitOfDuration.getId());
//        unitOfDurationCommand.setUod(unitOfDuration.getUod());
//        return unitOfDurationCommand;

        if (unitOfDuration != null) {
            final UnitOfDurationCommand unitOfDurationCommand = new UnitOfDurationCommand();
            unitOfDurationCommand.setId(unitOfDuration.getId());
            unitOfDurationCommand.setUod(unitOfDuration.getUod());
            return unitOfDurationCommand;

        }
        return null;
    }
}