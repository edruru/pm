package com.inndra.pm.core.service;

import com.inndra.pm.core.dao.ProjectDao;
import com.inndra.pm.core.domain.model.Project;
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
 * Test for ProjectService.
 *
 * @author edwin.rubio
 * @version 1.0
 * @since 2014-06-10
 */

@RunWith(MockitoJUnitRunner.class)
public class ProjectServiceTest {

    @InjectMocks
    private ProjectServiceImpl projectService;

    @Mock
    private ProjectDao projectDao;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void close() {
        projectService = null;
        projectDao = null;
    }

    @Test
    public void testGetProjectById() {
        Mockito.when(projectDao.getProjectById(Mockito.anyInt())).thenReturn(new Project());
        Project project = projectService.getProjectById(1);
        Mockito.verify(projectDao).getProjectById(Mockito.anyInt());
        Assert.assertNotNull(project);
    }

    @Test(expected = RuntimeException.class)
    public void testGetProjectByIdFailed() {
        Mockito.when(projectDao.getProjectById(Mockito.anyInt())).thenThrow(RuntimeException.class);
        projectService.getProjectById(1);
        Mockito.verify(projectDao).getProjectById(Mockito.anyInt());
    }

    @Test
    public void testGetAllProjects() {
        Mockito.when(projectDao.getAllProjects()).thenReturn(new ArrayList<Project>());
        List<Project> projects = projectService.getAllProjects();
        Mockito.verify(projectDao).getAllProjects();
        Assert.assertNotNull(projects);
    }

    @Test(expected = RuntimeException.class)
    public void testGetAllProjectsFailed() {
        Mockito.when(projectDao.getAllProjects()).thenThrow(RuntimeException.class);
        projectService.getAllProjects();
        Mockito.verify(projectDao).getAllProjects();
    }

    @Test
    public void testInsertProject() {
        Mockito.doNothing().when(projectDao).insertProject(Mockito.any(Project.class));
        projectService.insertProject(new Project());
        Mockito.verify(projectDao).insertProject(Mockito.any(Project.class));
    }

    @Test(expected = SQLException.class)
    public void testInsertProjectFailed() {
        Mockito.doThrow(SQLException.class).when(projectDao).insertProject(Mockito.any(Project.class));
        projectService.insertProject(new Project());
        Mockito.verify(projectDao).insertProject(Mockito.any(Project.class));
    }

    @Test
    public void testUpdateProject() {
        Mockito.doNothing().when(projectDao).updateProject(Mockito.any(Project.class));
        projectService.updateProject(new Project());
        Mockito.verify(projectDao).updateProject(Mockito.any(Project.class));
    }

    @Test(expected = SQLException.class)
    public void testUpdateProjectFailed() {
        Mockito.doThrow(SQLException.class).when(projectDao).updateProject(Mockito.any(Project.class));
        projectService.updateProject(new Project());
        Mockito.verify(projectDao).updateProject(Mockito.any(Project.class));
    }

    @Test
    public void testDeleteProject() {
        Mockito.doNothing().when(projectDao).deleteProject(Mockito.anyInt());
        projectService.deleteProject(1);
        Mockito.verify(projectDao).deleteProject(Mockito.anyInt());
    }

    @Test(expected = SQLException.class)
    public void testDeleteProjectFailed() {
        Mockito.doThrow(SQLException.class).when(projectDao).deleteProject(Mockito.anyInt());
        projectService.deleteProject(1);
        Mockito.verify(projectDao).deleteProject(Mockito.anyInt());
    }
}
