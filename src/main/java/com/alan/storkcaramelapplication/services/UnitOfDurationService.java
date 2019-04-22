package com.alan.storkcaramelapplication.services;

import com.alan.storkcaramelapplication.commands.UnitOfDurationCommand;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
public interface UnitOfDurationService {
    Set<UnitOfDurationCommand> listAllUod();
}
