package com.tfc.apitfc.domain.dao;

import com.tfc.apitfc.domain.entity.CommonArea;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommonAreaInterface extends CrudRepository<CommonArea, Integer> {
    List<CommonArea> findAll();

    CommonArea findById(int id);

    List<CommonArea> findByNeighborhoodId(Integer id);
}
