package com.quorastudent.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.quorastudent.dto.QuestionDTO;

public interface QuestionRepository extends JpaRepository<QuestionDTO, Long> {

	List<QuestionDTO> findByEqidAndCtype(Long eqid, String ctype);

	@Transactional
	@Modifying
	@Query("update QuestionDTO q  SET q.question = :question , q.updatedat=NOW() , q.tags = :tags  WHERE  q.eqid = :eqid and q.ctype = :ctype")
	void updateQuestion(Long eqid, String ctype, String question, String tags);

	@Transactional
	@Modifying
	@Query("delete  from   QuestionDTO q   where q.eqid = :eqid AND q.ctype = :ctype ")
	void deleteQuestionOrEvent(Long eqid, String ctype);

}
