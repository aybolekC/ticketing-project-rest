package com.aya.converter;

import com.aya.dto.ProjectDTO;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
@ConfigurationPropertiesBinding
public class ProjectDtoConverter implements Converter<String, ProjectDTO>{
    @Override
    public ProjectDTO convert(String source) {
        return null;
    }


//    ProjectService projectService;
//
//    public ProjectDtoConverter(ProjectService projectService) {
//        this.projectService = projectService;
//    }
//
//
//    @Override
//    public ProjectDTO convert(String source) {
//        return projectService.findById(source);
//    }
}



