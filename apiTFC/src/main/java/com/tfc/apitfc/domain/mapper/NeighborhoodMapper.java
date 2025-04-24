package com.tfc.apitfc.domain.mapper;

import com.tfc.apitfc.domain.entity.Neighborhood;
import com.tfc.apitfc.domain.entity.Record;
import com.tfc.apitfc.domain.dto.NeighborhoodDTO;
import com.tfc.apitfc.domain.dto.RecordDTO;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

public class NeighborhoodMapper {

    public static NeighborhoodDTO toDTO(Neighborhood neighborhood) {
        NeighborhoodDTO dto = new NeighborhoodDTO();
        dto.setId(neighborhood.getId());
        dto.setName(neighborhood.getName());

        if (neighborhood.getImage() != null) {
            dto.setBase64Image(Base64.getEncoder().encodeToString(neighborhood.getImage()));
        }

        if (neighborhood.getRecords() != null) {
            List<RecordDTO> recordDTOs = neighborhood.getRecords().stream()
                    .map(NeighborhoodMapper::toDTO)
                    .collect(Collectors.toList());
            dto.setRecords(recordDTOs);
        }

        return dto;
    }

    public static RecordDTO toDTO(Record record) {
        RecordDTO dto = new RecordDTO();
        dto.setId(record.getId());
        dto.setName(record.getTitle());
        dto.setDescription(record.getDescription());
        dto.setDate(record.getDate());

        if (record.getFile() != null) {
            dto.setFileBase64(Base64.getEncoder().encodeToString(record.getFile()));
        }

        return dto;
    }
}

