package com.tfc.apitfc.controllers;

import com.tfc.apitfc.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.tfc.apitfc.domain.entity.Record;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pocket/records")
public class RecordController {
    @Autowired
    private RecordService recordService;

    @GetMapping
    public ResponseEntity<List<Record>> findAll() {
        List<Record> records = recordService.findAll();

        if (!records.isEmpty()) {
            return ResponseEntity.ok().body(records);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
    

    @GetMapping("/{neighborhoodId}")
    public ResponseEntity<List<Record>> findByNeighborhoodId(@PathVariable int neighborhoodId) {
        List<Record> records = recordService.findByNeighborhoodId(neighborhoodId);

        if (!records.isEmpty()) {
            return ResponseEntity.ok().body(records);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadRecord(@PathVariable int id) {
        Optional<Record> record = recordService.getRecordById(id);

        if (record.isPresent() && record.get() != null) {
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + record.get().getTitle() + ".pdf\"")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(record.get().getFile());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadRecord(@RequestParam("name") String name, @RequestParam("description") String description, @RequestParam("date") Date date, @RequestParam("file") MultipartFile file) {
        try {
            Record savedRecord = recordService.save(name, description, date, file);
            return ResponseEntity.ok("Archivo subido con éxito. ID: " + savedRecord.getId());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar el archivo.");
        }
    }

}
