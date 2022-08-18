package com.pig.licitai.model.repository;


import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pig.licitai.model.entity.EmailModel;
import com.pig.licitai.model.enuns.StatusEmail;

@Repository
public interface EmailRepository extends JpaRepository<EmailModel, Long> {
	
	@Query(value = "select * from tb_email email where email.status_email = :status_email", nativeQuery = true)
	public ArrayList<EmailModel> findByStatusEmail(@Param("status_email") int statusEmail);
}
