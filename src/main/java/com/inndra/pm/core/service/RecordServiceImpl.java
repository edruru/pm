package com.inndra.pm.core.service;

import com.inndra.pm.core.dao.RecordDao;
import com.inndra.pm.core.domain.model.Record;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by edwin.rubio on 6/2/2014.
 */

@Service("recordService")
public class RecordServiceImpl implements RecordService {

    private static final Logger log = LoggerFactory.getLogger(RecordServiceImpl.class);

    @Autowired
    private RecordDao recordDao;

    @Override
    public void insertRecord(Record record) {
        log.debug("insertRecordSer" + record);
        try {
            recordDao.insertRecord(record);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            throw new RuntimeException();
        }
    }

    @Override
    public void updateRecord(Record record) {
        log.debug("updateRecordSer" + record);
        try {
            recordDao.updateRecord(record);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            throw new RuntimeException();
        }
    }

    @Override
    public void deleteRecord(Integer id) {
        log.debug("deleteRecordSer" + id);
        try {
            recordDao.deleteRecord(id);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            throw new RuntimeException();
        }
    }

    @Override
    public Record getRecordById(Integer id) {
        log.debug("getRecordByIdSer" + id);
        try {
            return recordDao.getRecordById(id);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            throw new RuntimeException();
        }
    }

    @Override
    public List<Record> getAllRecord() {
        log.debug("getAllRecordSer");
        try {
            return recordDao.getAllRecord();
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            throw new RuntimeException();
        }
    }

    @Override
    public List<Record> getRecordByUser(String name) {
        log.debug("getRecordByUserSer" + name);
        try {
            return recordDao.getRecordByUser(name);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            throw new RuntimeException();
        }
    }

    public List<Record> getRecordByText(String description) {
        log.debug("getRecordByTextSer" + description);
        try {
            return recordDao.getRecordByText(description);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            throw new RuntimeException();
        }
    }

    public List<Record> getLastRecords(String date){
        log.debug("getLastRecordsSer" + date);
        try {
            return recordDao.getLastRecords(date);
        }catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            throw new RuntimeException();
        }
    }

    public void setRecordDao(RecordDao recordDao) {
        this.recordDao = recordDao;
    }
}
