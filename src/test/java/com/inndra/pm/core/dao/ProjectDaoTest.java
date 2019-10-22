package com.inndra.pm.core.dao;

import com.inndra.pm.core.domain.model.Project;
import com.inndra.pm.core.mapper.ProjectMapper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Test for Project.
 *
 * @author edwin.rubio
 * @version 1.0
 * @since 2014-06-10
 */

@RunWith(MockitoJUnitRunner.class)
public class ProjectDaoTest {

    @InjectMocks
    private ProjectDaoImpl projectDao;

    @Mock
    private ProjectMapper projectMapper;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void close() {
        projectDao = null;
        projectMapper = null;
    }


    @Test
    public void testGetProjectById() {
        Mockito.when(projectMapper.getProjectById(Mockito.anyInt())).thenReturn(new Project());
        Project project = projectDao.getProjectById(1);
        Mockito.verify(projectMapper).getProjectById(Mockito.anyInt());
        Assert.assertNotNull(project);
    }

    @Test(expected = RuntimeException.class)
    public void testGetProjectByIdFailed() {
        Mockito.when(projectMapper.getProjectById(Mockito.anyInt())).thenThrow(RuntimeException.class);
        projectDao.getProjectById(1);
        Mockito.verify(projectMapper).getProjectById(Mockito.anyInt());
    }

    @Test
    public void testGetAllProjects() {
        Mockito.when(projectMapper.getAllProjects()).thenReturn(new ArrayList<Project>());
        List<Project> projects = projectDao.getAllProjects();
        Mockito.verify(projectMapper).getAllProjects();
        Assert.assertNotNull(projects);
    }

    @Test(expected = RuntimeException.class)
    public void testGetAllProjectsFailed() {
        Mockito.when(projectMapper.getAllProjects()).thenThrow(RuntimeException.class);
        projectDao.getAllProjects();
        Mockito.verify(projectMapper).getAllProjects();
    }

    @Test
    public void testInsertProject() {
        Mockito.doNothing().when(projectMapper).insertProject(Mockito.any(Project.class));
        projectDao.insertProject(new Project());
        Mockito.verify(projectMapper).insertProject(Mockito.any(Project.class));
    }

    @Test(expected = SQLException.class)
    public void testInsertProjectFailed() {
        Mockito.doThrow(SQLException.class).when(projectMapper).insertProject(Mockito.any(Project.class));
        projectDao.insertProject(new Project());
        Mockito.verify(projectMapper).insertProject(Mockito.any(Project.class));
    }

    @Test
    public void testUpdateProject() {
        Mockito.doNothing().when(projectMapper).updateProject(Mockito.any(Project.class));
        projectDao.updateProject(new Project());
        Mockito.verify(projectMapper).updateProject(Mockito.any(Project.class));
    }

    @Test(expected = SQLException.class)
    public void testUpdateProjectFailed() {
        Mockito.doThrow(SQLException.class).when(projectMapper).updateProject(Mockito.any(Project.class));
        projectDao.updateProject(new Project());
        Mockito.verify(projectMapper).updateProject(Mockito.any(Project.class));
    }

    @Test
    public void testDeleteProject() {
        Mockito.doNothing().when(projectMapper).deleteProject(Mockito.anyInt());
        projectDao.deleteProject(1);
        Mockito.verify(projectMapper).deleteProject(Mockito.anyInt());
    }

    @Test(expected = SQLException.class)
    public void testDeleteProjectFailed() {
        Mockito.doThrow(SQLException.class).when(projectMapper).deleteProject(Mockito.anyInt());
        projectDao.deleteProject(1);
        Mockito.verify(projectMapper).deleteProject(Mockito.anyInt());
    }
}
