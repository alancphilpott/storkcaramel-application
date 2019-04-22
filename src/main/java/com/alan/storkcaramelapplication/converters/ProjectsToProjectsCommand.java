package com.alan.storkcaramelapplication.converters;

import com.alan.storkcaramelapplication.commands.ProjectsCommand;
import com.alan.storkcaramelapplication.domain.Projects;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class ProjectsToProjectsCommand implements Converter<Projects,ProjectsCommand> {
    @Synchronized
    @Nullable
    @Override
    public ProjectsCommand convert(Projects projects) {
        if (projects == null){
            return null;
        }
        final ProjectsCommand projectsCommand = new ProjectsCommand();
        projectsCommand.setId(projects.getId());
        if (projects.getStudentProfile()!= null) {
            projectsCommand.setStudentProfileId(projects.getStudentProfile().getId());
        }
        projectsCommand.setProjectDescription(projects.getProjectDescription());
        projectsCommand.setProjectTitle(projects.getProjectTitle());

        projectsCommand.setTechnologiesUsed(projects.getTechnologiesUsed());
        return projectsCommand;
    }
}
