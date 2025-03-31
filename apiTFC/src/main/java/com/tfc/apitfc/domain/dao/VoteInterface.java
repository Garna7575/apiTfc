package com.tfc.apitfc.domain.dao;

import com.tfc.apitfc.domain.entity.Vote;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteInterface extends CrudRepository<Vote, Integer> {
    Vote findById(int id);
}
