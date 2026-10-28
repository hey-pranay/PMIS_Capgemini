package com.spring.quickRelif.service;

import com.spring.quickRelif.entity.Supply;
import com.spring.quickRelif.repository.SupplyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplyService {

    private final SupplyRepository supplyRepository;

    public  SupplyService(SupplyRepository supplyRepository){
        this.supplyRepository = supplyRepository;
    }

    // business logic for retrieving all supplies
    public List<Supply> getAllSupplies(){
        return supplyRepository.findAll();
    }



}