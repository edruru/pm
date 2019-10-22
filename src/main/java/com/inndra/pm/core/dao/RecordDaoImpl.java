package com.inndra.pm.core.dao;

import com.inndra.pm.core.domain.model.Record;
import com.inndra.pm.core.mapper.RecordMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by edwin.rubio on 5/30/2014.
 */

@Transactional
@Repository("recordDao")
public class RecordDaoImpl implements RecordDao {

    private static final Logger log = LoggerFactory.getLogger(RecordDaoImpl.class);

    @Resource
    private RecordMapper recordMapper;

    @Override
    public void insertRecord(Record record) {
        log.debug("insertRecordDao" + record);
        try {
            recordMapper.insertRecord(record);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            throw new RuntimeException();
        }
    }

    @Override
    public void updateRecord(Record record) {
        log.debug("updateRecordDao" + record);
        try {
            recordMapper.updateRecord(record);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            throw new RuntimeException();
        }
    }

    @Override
    public void deleteRecord(Integer id) {
        log.debug("deleteRecordDao" + id);
        try {
            recordMapper.deleteRecord(id);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            throw new RuntimeException();
        }
    }

    @Override
    public Record getRecordById(Integer id) {
        log.debug("getRecordByIdDao" + id);
        try {
            return recordMapper.getRecordById(id);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            throw new RuntimeException();
        }
    }

    @Override
    public List<Record> getAllRecord() {
        log.debug("getAllRecordDao");
        try {
            return recordMapper.getAllRecord();
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            throw new RuntimeException();
        }
    }

    @Override
    public List<Record> getRecordByUser(String name) {
        log.debug("getRecordByUserDao" + name);
        try {
            return recordMapper.getRecordByUser("%" + name + "%");
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            throw new RuntimeException();
        }
    }

    public List<Record> getRecordByText(String description) {
        log.debug("getRecordByTextDao" + description);
        try {
            return recordMapper.getRecordByText("%" + description + "%");
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            throw new RuntimeException();
        }
    }

    public List<Record> getLastRecords(String date) {
        log.debug("getLastRecordsDao" + date);
        try {
            return recordMapper.getLastRecords("%" + date + "%");
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            throw new RuntimeException();
        }
    }

    public void setRecordMapper(RecordMapper recordMapper) {
        this.recordMapper = recordMapper;
    }
}
