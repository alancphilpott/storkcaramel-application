package com.alan.storkcaramelapplication.services;

import com.alan.storkcaramelapplication.commands.UnitOfDurationCommand;
import com.alan.storkcaramelapplication.converters.UnitOfDurationToUnitOfDurationCommand;
import com.alan.storkcaramelapplication.repositories.UnitOfDurationRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UnitOfDurationServiceImpl implements UnitOfDurationService {
    private  final UnitOfDurationRepository unitOfDurationRepository;
    private final UnitOfDurationToUnitOfDurationCommand unitOfDurationToUnitOfDurationCommand;

    public UnitOfDurationServiceImpl(UnitOfDurationRepository unitOfDurationRepository,
                                     UnitOfDurationToUnitOfDurationCommand unitOfDurationToUnitOfDurationCommand) {
        this.unitOfDurationRepository = unitOfDurationRepository;
        this.unitOfDurationToUnitOfDurationCommand = unitOfDurationToUnitOfDurationCommand;
    }

    @Override
    public Set<UnitOfDurationCommand> listAllUod() {
            return StreamSupport.stream(unitOfDurationRepository.findAll().spliterator(),false)
                    .map(unitOfDurationToUnitOfDurationCommand::convert).collect(Collectors.toSet());
    }
}
