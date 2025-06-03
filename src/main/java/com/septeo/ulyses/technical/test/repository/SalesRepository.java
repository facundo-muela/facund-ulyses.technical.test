package com.septeo.ulyses.technical.test.repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.septeo.ulyses.technical.test.entity.Sales;

/**
 * Repository interface for Sales entity.
 */
@Repository
public interface SalesRepository {
    /**
     * Find all sales.
     * @param size 
     * @param page 
     *
     * @return a list of all sales
     */
    List<Sales> findAll(int page, int size);

    /**
     * Find a sale by its ID.
     *
     * @param id the ID of the sale to find
     * @return an Optional containing the sale if found, or empty if not found
     */
    Optional<Sales> findById(Long id);
    
    List<Sales> getAllByBrandId(Long id);
    
    List<Sales> getAllByVehicleId(Long id);
    
    List<Sales>  getTopFiveVehicles(Date startDate, Date endDate);

}
