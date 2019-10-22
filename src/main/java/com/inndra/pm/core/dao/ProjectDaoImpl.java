package com.inndra.pm.core.dao;

import com.inndra.pm.core.domain.model.Project;
import com.inndra.pm.core.mapper.ProjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by edwin.rubio on 29/05/2014.
 */
@Transactional
@Repository("projectDao")
public class ProjectDaoImpl implements ProjectDao {

    @Resource
    private ProjectMapper projectMapper;

    public void insertProject(Project project) {

        projectMapper.insertProject(project);
    }

    @Override
    public Project getProjectById(Integer id) {

        return projectMapper.getProjectById(id);
    }

    @Override
    public List<Project> getAllProjects() {

        return projectMapper.getAllProjects();
    }

    @Override
    public void updateProject(Project project) {

        projectMapper.updateProject(project);
    }

    @Override
    public void deleteProject(Integer id) {

        projectMapper.deleteProject(id);
    }

    @Override
    public List<Project> getProjectByName(String name) {
        return projectMapper.getProjectByName("%" + name + "%");
    }

    public void setProjectMapper(ProjectMapper projectMapper) {
        this.projectMapper = projectMapper;
    }
}
