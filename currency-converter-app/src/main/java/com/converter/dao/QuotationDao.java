package com.converter.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.converter.model.Quotation;

public interface QuotationDao extends CrudRepository<Quotation, Long> {
	
	@Query(value="select q from Quotation q inner join q.user u where u.id = :userId order by q.time desc")
	List<Quotation> getLastQuotations(@Param("userId") Long userId, Pageable pageable);
	
}
