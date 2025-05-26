package com.tfc.apitfc.controllers;

import com.tfc.apitfc.domain.dto.ReceiptDTO;
import com.tfc.apitfc.domain.entity.Receipt;
import com.tfc.apitfc.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/pocket/receipts")
public class ReceiptController {

    @Autowired
    ReceiptService receiptService;


    @GetMapping("/{id}")
    public ResponseEntity<List<Receipt>> getReceiptsByNeighborId(@PathVariable int id) {
        List<Receipt> receipts = receiptService.findByNeighborId(id);

        if (receipts != null) {
            return ResponseEntity.ok().body(receipts);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Receipt> createReceipt(@RequestBody ReceiptDTO receiptDTO) {
        try {
            Receipt createdReceipt = receiptService.save(receiptDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdReceipt);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/payment/{id}")
    public void payReceipt(@PathVariable int id) {
        Receipt receipt = receiptService.findById(id);

        receipt.setPaid(true);
        receiptService.update(receipt);
    }
}
