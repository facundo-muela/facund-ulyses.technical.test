package com.septeo.ulyses.technical.test.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import com.septeo.ulyses.technical.test.entity.Sales;

/**
 * Service interface for Sales operations.
 */
public interface SalesService {

    /**
     * Get all sales.
     * @param size 
     * @param page 
     *
     * @return a list of all sales
     */
    List<Sales> getAllSales(int page, int size);

    /**
     * Get a sales by its ID.
     *
     * @param id the ID of the sales to find
     * @return an Optional containing the sales if found, or empty if not found
     */
    Optional<Sales> getSalesById(Long id);
    
    
    List<Sales> getAllByBrandId(Long id);
    
    List<Sales> getAllByVehicleId(Long id);
    
    List<Sales> getTopFiveVehicles(Date startDate, Date endDate);

}
