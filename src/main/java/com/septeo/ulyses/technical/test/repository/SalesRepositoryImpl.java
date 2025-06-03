package com.septeo.ulyses.technical.test.repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.septeo.ulyses.technical.test.entity.Sales;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

/**
 * Implementation of the SalesRepository interface.
 * This class provides the implementation for all sales-related operations.
 */
@Repository
public class SalesRepositoryImpl implements SalesRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Sales> findAll(int page, int size) {
        String stringQuery = "SELECT s FROM Sales s";
        Query query = entityManager.createQuery(stringQuery);
        query.setFirstResult(page * size);
        query.setMaxResults(size);        
        return query.getResultList();
    }

    @Override
    public Optional<Sales> findById(Long id) {
        String stringQuery = "SELECT s FROM Sales s WHERE s.id = :id";
        Query query = entityManager.createQuery(stringQuery);
        query.setParameter("id", id);

        try {
            return Optional.of((Sales) query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

	@Override
	public List<Sales> getAllByBrandId(Long id) {
		String stringQuery = "SELECT s FROM Sales s WHERE s.brand.id = :id";
		Query query = entityManager.createQuery(stringQuery);
		query.setParameter("id", id);
		return query.getResultList();
	}
	
	@Override
	public List<Sales> getAllByVehicleId(Long id) {
		String stringQuery = "SELECT s FROM Sales s WHERE s.vehicle.id = :id";
		Query query = entityManager.createQuery(stringQuery);
		query.setParameter("id", id);
		return query.getResultList();	
	}
	
	@Override
	public List<Sales>  getTopFiveVehicles(Date startDate, Date endDate) {
		StringBuilder stringBuilder = new StringBuilder("SELECT s.vehicle.id, s.vehicle, COUNT(s) AS total FROM Sales s WHERE 1=1 ");
		if (startDate != null && endDate != null) {
			stringBuilder.append(" AND s.saleDate >= :startDate AND s.saleDate <= :endDate ");
	    }	
		stringBuilder.append(" GROUP BY s.vehicle ORDER BY total DESC ");
		Query query = entityManager.createQuery(stringBuilder.toString());
		query.setMaxResults(5);
		return query.getResultList();
	}
  
}
