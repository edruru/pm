package com.inndra.pm.core.dao;

import com.inndra.pm.core.domain.model.Record;
import com.inndra.pm.core.mapper.RecordMapper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

/**
 * Created by edwin.rubio on 6/23/2014.
 */
public class RecordDaoTest {

    @InjectMocks
    private RecordDaoImpl recordDao;

    @Mock
    private RecordMapper recordMapper;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void close() { recordDao = null; recordMapper = null;
    }

    @Test
    public void getRecordByIdTest(){
        Mockito.when(recordMapper.getRecordById(Mockito.anyInt())).thenReturn(new Record());
        Assert.assertNotNull(recordDao.getRecordById(Mockito.anyInt()));
        Mockito.verify(recordMapper).getRecordById(Mockito.anyInt());
    }

    @Test(expected = RuntimeException.class)
    public void getRecordByIdTestFailed(){
        Mockito.when(recordMapper.getRecordById(Mockito.anyInt())).thenThrow(RuntimeException.class);
        recordDao.getRecordById(Mockito.anyInt());
        Mockito.verify(recordMapper).getRecordById(Mockito.anyInt());
    }

    @Test
    public void getAllRecordsTest(){
        Mockito.when(recordMapper.getAllRecord()).thenReturn(new ArrayList<Record>());
        Assert.assertNotNull(recordDao.getAllRecord());
        Mockito.verify(recordMapper).getAllRecord();
    }

    @Test(expected = RuntimeException.class)
    public void getAllRecordsTestFailed(){
        Mockito.when(recordMapper.getAllRecord()).thenThrow(RuntimeException.class);
        recordDao.getAllRecord();
        Mockito.verify(recordMapper).getAllRecord();
    }

    @Test
    public void insertRecordTest(){
        Mockito.doNothing().when(recordMapper).insertRecord(Mockito.any(Record.class));
        recordDao.insertRecord(new Record());
        Mockito.verify(recordMapper).insertRecord(Mockito.any(Record.class));
    }

    @Test(expected = RuntimeException.class)
    public void insertRecordTestFailed(){
        Mockito.doThrow(NullPointerException.class).when(recordMapper).insertRecord(Mockito.any(Record.class));
        recordDao.insertRecord(new Record());
        Mockito.verify(recordMapper).insertRecord(Mockito.any(Record.class));
    }

    @Test
    public void updateRecordTest(){
        Mockito.doNothing().when(recordMapper).updateRecord(Mockito.any(Record.class));
        recordDao.updateRecord(new Record());
        Mockito.verify(recordMapper).updateRecord(Mockito.any(Record.class));
    }

    @Test(expected = RuntimeException.class)
    public void updateRecordTestFailed(){
        Mockito.doThrow(NullPointerException.class).when(recordMapper).updateRecord(Mockito.any(Record.class));
        recordDao.updateRecord(new Record());
        Mockito.verify(recordMapper).updateRecord(Mockito.any(Record.class));
    }

    @Test
    public void deleteRecordTest(){
        Mockito.doNothing().when(recordMapper).deleteRecord(Mockito.anyInt());
        recordDao.deleteRecord(new Integer(1));
        Mockito.verify(recordMapper).deleteRecord(Mockito.anyInt());
    }

    @Test(expected = RuntimeException.class)
    public void deleteRecordTestFailed(){
        Mockito.doThrow(NullPointerException.class).when(recordMapper).deleteRecord(Mockito.anyInt());
        recordDao.deleteRecord(new Integer(1));
        Mockito.verify(recordMapper).deleteRecord(Mockito.anyInt());
    }

    @Test
    public void getRecordByUserTest(){
        Mockito.when(recordMapper.getRecordByUser(Mockito.anyString())).thenReturn(new ArrayList<Record>());
        Assert.assertNotNull(recordDao.getRecordByUser(new String()));
        Mockito.verify(recordMapper).getRecordByUser(Mockito.anyString());
    }

    @Test(expected = RuntimeException.class)
    public void getRecordByUserTestFailed(){
        Mockito.when(recordMapper.getRecordByUser(Mockito.anyString())).thenThrow(RuntimeException.class);
        recordDao.getRecordByUser(new String());
        Mockito.verify(recordMapper).getRecordByUser(Mockito.anyString());
    }

    @Test
    public void getRecordByTextTest(){
        Mockito.when(recordMapper.getRecordByText(Mockito.anyString())).thenReturn(new ArrayList<Record>());
        Assert.assertNotNull(recordDao.getRecordByText(new String("")));
        Mockito.verify(recordMapper).getRecordByText(Mockito.anyString());
    }

    @Test(expected = RuntimeException.class)
    public void getRecordByTextTestFailed(){
        Mockito.when(recordMapper.getRecordByText(Mockito.anyString())).thenThrow(RuntimeException.class);
        recordDao.getRecordByText(new String(""));
        Mockito.verify(recordMapper).getRecordByText(Mockito.anyString());
    }



}
