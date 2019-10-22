package com.inndra.pm.core.service;

import com.inndra.pm.core.dao.ProjectDao;
import com.inndra.pm.core.dao.ProjectDaoImpl;
import com.inndra.pm.core.domain.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by edwin.rubio on 29/05/2014.
 */
@Service("projectService")
public class ProjectServiceImpl implements ProjectService{

    @Autowired
    private ProjectDao projectDao;

    public ProjectServiceImpl(){
        projectDao = new ProjectDaoImpl();
    }

    @Override
    public void insertProject( Project project ){
        //TODO: Perform validation & exception handling
        projectDao.insertProject( project );
    }

    @Override
    public Project getProjectById(Integer id) {
        //TODO: Perform validation & exception handling
        return projectDao.getProjectById(id);
    }

    @Override
    public List<Project> getAllProjects() {
        //TODO: Perform validation & exception handling
        return projectDao.getAllProjects();
    }

    @Override
    public void updateProject( Project project ){
        //TODO: Perform validation & exception handling
        projectDao.updateProject(project);
    }

    @Override
    public void deleteProject(Integer id){
        //TODO: Perform validation & exception handling
        projectDao.deleteProject(id);
    }

    @Override
    public List<Project> getProjectByName(String name) {
        return projectDao.getProjectByName(name);
    }

    public void setProjectDao(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }
}
