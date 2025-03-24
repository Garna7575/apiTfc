package com.tfc.apitfc.service;

import com.tfc.apitfc.domain.dao.CommonAreaInterface;
import com.tfc.apitfc.domain.entity.CommonArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommonAreaService {
    @Autowired
    CommonAreaInterface commonAreaInterface;

    public List<CommonArea> getAllCommonAreas(){
        return commonAreaInterface.findAll();
    }

    public CommonArea getCommonAreaById(int id){
        return commonAreaInterface.findById(id);
    }

    public List<CommonArea> getCommonAreaByNeighborhood(int id){
        return commonAreaInterface.findByNeighborhoodId(id);
    }

    public void save(CommonArea commonArea){
        commonAreaInterface.save(commonArea);
    }

    public void delete(CommonArea commonArea){

        if (commonArea != null) {
            commonAreaInterface.delete(commonArea);
        }
    }
}
