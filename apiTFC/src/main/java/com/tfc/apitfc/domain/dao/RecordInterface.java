package com.tfc.apitfc.domain.dao;

import com.tfc.apitfc.domain.entity.Record;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecordInterface extends CrudRepository<Record, Integer> {
    Optional<Record> findById(int id);

    List<Record> findAll();

    List<Record> findByNeighborhoodId(int id);
}
