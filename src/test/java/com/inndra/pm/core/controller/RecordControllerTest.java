package com.inndra.pm.core.controller;

import com.inndra.pm.web.controller.RecordController;
import com.inndra.pm.core.service.ProjectService;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by edwin.rubio on 6/26/2014.
 */

@RunWith(MockitoJUnitRunner.class)
public class RecordControllerTest {

    @InjectMocks
    private RecordController recordController;

    @Mock
    private ProjectService recordService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void close() {
        recordController = null;
        recordService = null;
    }


}
