package com.septeo.ulyses.technical.test.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.septeo.ulyses.technical.test.entity.Sales;
import com.septeo.ulyses.technical.test.repository.SalesRepository;

/**
 * Implementation of the SalesService interface.
 * This class provides the implementation for all sales-related operations.
 */
@Service
@Transactional(readOnly = false)
public class SalesServiceImpl implements SalesService {

    @Autowired
    private SalesRepository salesRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Sales> getAllSales(int page, int size) {
        return salesRepository.findAll(page, size);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Sales> getSalesById(Long id) {
        return salesRepository.findById(id);
    }
    
    @Override
    public List<Sales> getAllByBrandId(Long id) {
    	return salesRepository.getAllByBrandId(id);
    }
    
    @Override
    public List<Sales> getAllByVehicleId(Long id) {
    	return salesRepository.getAllByVehicleId(id);
    }
    
    @Override
    public List<Sales> getTopFiveVehicles(Date startDate, Date endDate) {
    	return salesRepository.getTopFiveVehicles(startDate, endDate);
    }

}
