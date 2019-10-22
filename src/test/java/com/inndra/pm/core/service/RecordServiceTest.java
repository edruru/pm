package com.inndra.pm.core.service;

import static org.hamcrest.core.Is.is;

import com.inndra.pm.core.dao.RecordDao;
import com.inndra.pm.core.domain.model.Record;
import org.junit.After;
import org.junit.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;


/**
 * Created by edwin.rubio on 6/16/2014.
 */
@RunWith(MockitoJUnitRunner.class)
public class RecordServiceTest {

    @InjectMocks
    private RecordServiceImpl recordService;

    @Mock
    private RecordDao recordDao;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void close() { recordService = null; recordDao = null;
    }

    @Test
    public void getRecordByIdTest(){
        Mockito.when(recordDao.getRecordById(Mockito.anyInt())).thenReturn(new Record());
        Assert.assertNotNull(recordService.getRecordById(Mockito.anyInt()));
        Mockito.verify(recordDao).getRecordById(Mockito.anyInt());
    }

    @Test(expected = RuntimeException.class)
    public void getRecordByIdTestFailed(){
        Mockito.when(recordDao.getRecordById(Mockito.anyInt())).thenThrow(RuntimeException.class);
        recordService.getRecordById(Mockito.anyInt());
        Mockito.verify(recordDao).getRecordById(Mockito.anyInt());
    }

    @Test
    public void getAllRecordsTest(){
        Mockito.when(recordDao.getAllRecord()).thenReturn(new ArrayList<Record>());
        Assert.assertNotNull(recordService.getAllRecord());
        Mockito.verify(recordDao).getAllRecord();
    }

    @Test(expected = RuntimeException.class)
    public void getAllRecordsTestFailed(){
        Mockito.when(recordDao.getAllRecord()).thenThrow(RuntimeException.class);
        recordService.getAllRecord();
        Mockito.verify(recordDao).getAllRecord();
    }

    @Test
    public void insertRecordTest(){
        Mockito.doNothing().when(recordDao).insertRecord(Mockito.any(Record.class));
        recordService.insertRecord(new Record());
        Mockito.verify(recordDao).insertRecord(Mockito.any(Record.class));
    }

    @Test(expected = RuntimeException.class)
    public void insertRecordTestFailed(){
        Mockito.doThrow(RuntimeException.class).when(recordDao).insertRecord(Mockito.any(Record.class));
        recordService.insertRecord(new Record());
        Mockito.verify(recordDao).insertRecord(Mockito.any(Record.class));
    }

    @Test
    public void updateRecordTest(){
        Mockito.doNothing().when(recordDao).updateRecord(Mockito.any(Record.class));
        recordService.updateRecord(new Record());
        Mockito.verify(recordDao).updateRecord(Mockito.any(Record.class));
    }

    @Test(expected = RuntimeException.class)
    public void updateRecordTestFailed(){
        Mockito.doThrow(RuntimeException.class).when(recordDao).updateRecord(Mockito.any(Record.class));
        recordService.updateRecord(new Record());
        Mockito.verify(recordDao).updateRecord(Mockito.any(Record.class));
    }

    @Test
    public void deleteRecordTest(){
        Mockito.doNothing().when(recordDao).deleteRecord(Mockito.anyInt());
        recordService.deleteRecord(new Integer(1));
        Mockito.verify(recordDao).deleteRecord(Mockito.anyInt());
    }

    @Test(expected = RuntimeException.class)
    public void deleteRecordTestFailed(){
        Mockito.doThrow(RuntimeException.class).when(recordDao).deleteRecord(Mockito.anyInt());
        recordService.deleteRecord(new Integer(1));
        Mockito.verify(recordDao).deleteRecord(Mockito.anyInt());
    }

    @Test
    public void getRecordByUserTest()
    {
        Mockito.when(recordDao.getRecordByUser(Mockito.anyString())).thenReturn(new ArrayList<Record>());
        Assert.assertNotNull(recordService.getRecordByUser(new String()));
        Mockito.verify(recordDao).getRecordByUser(Mockito.anyString());
    }

    @Test(expected = RuntimeException.class)
    public void getRecordByUserTestFailed()
    {
        Mockito.when(recordDao.getRecordByUser(Mockito.anyString())).thenThrow(RuntimeException.class);
        recordService.getRecordByUser(new String());
        Mockito.verify(recordDao).getRecordByUser(Mockito.anyString());
    }

    @Test
    public void getRecordByTextTest()
    {
        Mockito.when(recordDao.getRecordByText(Mockito.anyString())).thenReturn(new ArrayList<Record>());
        Assert.assertNotNull(recordService.getRecordByText(new String("")));
        Mockito.verify(recordDao).getRecordByText(Mockito.anyString());
    }

    @Test(expected = RuntimeException.class)
    public void getRecordByTextTestFailed()
    {
        Mockito.when(recordDao.getRecordByText(Mockito.anyString())).thenThrow(RuntimeException.class);
        recordService.getRecordByText(new String(""));
        Mockito.verify(recordDao).getRecordByText(Mockito.anyString());
    }



}
