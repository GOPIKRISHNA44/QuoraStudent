package com.quorastudent.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.quorastudent.dto.BlogDTO;

public interface BlogRepository extends JpaRepository<BlogDTO, Long> {

	@Transactional
	@Modifying
	@Query("update BlogDTO b  SET b.content= :content, b.updatedat = NOW() WHERE b.bid= :bid ")
	void updateBlog(Long bid, String content);
	
	@Transactional
	@Modifying
	@Query("delete  from   BlogDTO b   where b.bid = :bid ")
	void deleteBlog(Long bid);

}
