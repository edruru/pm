package com.inndra.pm.core.mapper;

import com.inndra.pm.core.domain.model.Project;

import java.util.List;

/**
 * Created by edwin.rubio on 29/05/2014.
 */
public interface ProjectMapper {

    public void insertProject(Project project);

    public Project getProjectById(Integer id);

    public List<Project> getProjectByName(String name);

    public List<Project> getAllProjects();

    public void updateProject(Project project);

    public void deleteProject(Integer id);
}
