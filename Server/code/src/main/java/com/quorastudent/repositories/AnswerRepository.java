package com.quorastudent.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.quorastudent.dto.AnswerDTO;

public interface AnswerRepository extends JpaRepository<AnswerDTO, Long> {

	
	@Transactional
	@Modifying
	@Query("update AnswerDTO a  SET a.content= :content, a.updatedat = NOW() WHERE a.aid= :aid ")
	void updateAnswer(Long aid, String content );
	
	@Transactional
	@Modifying
	@Query("delete  from  AnswerDTO a  where a.aid = :aid ")
	void deleteAnswer(Long aid );
}
