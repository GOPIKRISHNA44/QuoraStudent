package com.quorastudent.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quorastudent.dto.QuestionDTO;

public interface QuestionRepository extends JpaRepository<QuestionDTO, Long> {
	
	List<QuestionDTO> findByEqidAndCtype(Long eqid, String ctype);

}
