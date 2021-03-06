package com.inndra.pm.core.dao;

import com.inndra.pm.core.domain.model.Project;

import java.util.List;

/**
 * Created by edwin.rubio on 29/05/2014.
 */
public interface ProjectDao {

    void insertProject(Project project);

    Project getProjectById(Integer id);

    List<Project> getAllProjects();

    void updateProject(Project project);

    void deleteProject(Integer id);

    List<Project> getProjectByName(String name);
}
