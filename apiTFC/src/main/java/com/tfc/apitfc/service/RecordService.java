package com.tfc.apitfc.service;

import com.tfc.apitfc.domain.entity.Record;
import com.tfc.apitfc.domain.dao.RecordInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RecordService {

    @Autowired
    RecordInterface recordInterface;

    public List<Record> findAll() {
        return recordInterface.findAll();
    }

    public List<Record> findByNeighborhoodId(int id){
        return recordInterface.findByNeighborhoodId(id);
    }


    public Record save(String name, String description, Date date, MultipartFile file) throws IOException {
        Record record = new Record();

        record.setTitle(name);
        record.setDescription(description);
        record.setDate(date);
        record.setFile(file.getBytes());
        return recordInterface.save(record);
    }

    public Optional<Record> getRecordById(int id) {
        return recordInterface.findById(id);
    }
}
