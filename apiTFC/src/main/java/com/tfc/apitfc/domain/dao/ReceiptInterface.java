package com.tfc.apitfc.domain.dao;

import com.tfc.apitfc.domain.entity.Receipt;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceiptInterface extends CrudRepository<Receipt, Integer> {

    Receipt findById(int receiptId);

    List<Receipt> findByNeighborIdOrderByDateDesc(int neighborId);
}
