package com.inndra.pm.core.domain.model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Test for Project.
 *
 * @author edwin.rubio
 * @version 1.0
 * @since 2014-06-10
 */

public class ProjectTest {

    private Project project;

    @Before
    public void init() {
        project = new Project();
    }

    @After
    public void close() {
        project = null;
    }

    @Test
    public void testSetDescription() {
        project.setDescription(new String());
        Assert.assertNotNull(project);
    }

    @Test
    public void testSetId() {
        project.setId(1);
        Assert.assertNotNull(project);
    }

    @Test
    public void testSetName() {
        project.setName(new String());
        Assert.assertNotNull(project);
    }

    @Test
    public void testGetDescription() {
        Assert.assertEquals("default", project.getDescription());
    }

    @Test
    public void testGetId() {
        Assert.assertEquals(0, project.getId());
    }

    @Test
    public void testGetName() {
        Assert.assertEquals("default", project.getName());
    }

    @Test
    public void testArgumentContructor() {
        //TODO
    }

}
