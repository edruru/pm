package com.inndra.pm.core.mapper;


import com.inndra.pm.core.domain.model.Record;

import java.util.List;

/**
 * Created by edwin.rubio on 5/29/2014.
 */
public interface RecordMapper {

    void insertRecord(Record record);
    void updateRecord(Record record);
    void deleteRecord(Integer id);
    Record getRecordById(Integer id);
    List<Record> getRecordByUser(String name);
    List<Record> getRecordByText(String description);
    List<Record> getAllRecord();
    List<Record> getLastRecords(String date);
}
