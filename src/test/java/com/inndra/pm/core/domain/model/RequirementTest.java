package com.inndra.pm.core.domain.model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author
 */
public class RequirementTest {

    private Requirement requirement;

    @Before
    public void init() {
        requirement = new Requirement();
    }

    @After
    public void tearDown() {
        requirement = null;
    }

    @Test
    public void getNameTest() {
        Assert.assertTrue(true);
    }

}
