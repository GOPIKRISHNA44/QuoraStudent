package com.quorastudent.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.quorastudent.dto.CommentsDTO;
import com.quorastudent.dto.QuestionDTO;

public interface CommentsRepository extends JpaRepository<CommentsDTO, Long> {



	@Transactional
	@Modifying
	@Query("update CommentsDTO c  SET c.comment= :comment , c.updatedat=NOW() WHERE c.cid= :cid ")
	void updateComment(Long cid, String comment);

	@Transactional
	@Modifying
	@Query("DELETE  FROM  CommentsDTO c WHERE c.cid= :cid ")
	void deleteComment(Long cid);

	
	@Transactional
	@Modifying
	@Query("delete  from  CommentsDTO c  where c.parentid = :parentid and c.ctype = :ctype ")
	void deleteComments(Long parentid, String ctype);

	CommentsDTO findByParentidAndCtype(Long parentid, String ctype);
}
