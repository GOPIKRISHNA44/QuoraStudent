package com.quorastudent.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.quorastudent.dto.LikedislikeDTO;

public interface LikesDislikeRepository extends JpaRepository<LikedislikeDTO, Long> {

	List<LikedislikeDTO> findByParentidAndCtypeAndUserid(Long parentid, String ctype, Long userid);

	@Transactional
	@Modifying
	@Query("update LikedislikeDTO l set l.updwnvt = :updwnvt, l.updatedon=:updatedon where l.userid = :userid and l.ctype=:ctype and l.parentid =:parentid ")
	void updateLikeDislike(Long parentid, String ctype, Long userid, int updwnvt, Date updatedon);
	
	@Transactional
	@Modifying
	@Query("delete  from  LikedislikeDTO l  where l.parentid = :parentid and l.ctype = :ctype ")
	void deleteLikings(Long parentid, String ctype);

}