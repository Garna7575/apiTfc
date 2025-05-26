package com.tfc.apitfc.service;

import com.tfc.apitfc.domain.dao.ReceiptInterface;
import com.tfc.apitfc.domain.dto.ReceiptDTO;
import com.tfc.apitfc.domain.entity.Neighbor;
import com.tfc.apitfc.domain.entity.Neighborhood;
import com.tfc.apitfc.domain.entity.Receipt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceiptService {

    @Autowired
    private ReceiptInterface receiptInterface;

    @Autowired
    private NeighborService neighborService;

    public List<Receipt> findByNeighborId(int neighborId){
        return receiptInterface.findByNeighborIdOrderByDateDesc(neighborId);
    }

    public Receipt findById(int receiptId){
        return receiptInterface.findById(receiptId);
    }

    public Receipt save(ReceiptDTO receiptDTO) {
        Neighbor neighbor = neighborService.findById(receiptDTO.getNeighborId());

        Receipt receipt = new Receipt();
        receipt.setTitle(receiptDTO.getTitle());
        receipt.setDescription(receiptDTO.getDescription());
        receipt.setValue(receiptDTO.getValue());
        receipt.setPaid(receiptDTO.isPaid());
        receipt.setDate(receiptDTO.getDate());
        receipt.setNeighbor(neighbor);

        return receiptInterface.save(receipt);
    }

    public Receipt update(Receipt receipt) {
        return receiptInterface.save(receipt);
    }
}
