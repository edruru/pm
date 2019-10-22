package com.inndra.pm.core.service;

import com.inndra.pm.core.domain.model.Record;

import java.util.List;

/**
 * Created by edwin.rubio on 6/2/2014.
 */

public interface RecordService {

     void insertRecord(Record record);

     void updateRecord(Record record);

     void deleteRecord(Integer id);

     Record getRecordById(Integer id);

     List<Record> getRecordByText(String description);

     List<Record> getRecordByUser(String name);

     List<Record> getAllRecord();

     List<Record> getLastRecords(String date);
}
