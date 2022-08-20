package com.quorastudent.repositories;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.quorastudent.constants.Queries;
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

	@Query(value = Queries.GET_BLOG_UNV_BASED_QUERY, countQuery = Queries.GET_BLOG_UNV_BASED_QUERY_COUNT_QUERY, nativeQuery = true)
	Page<List<Map<String, Object>>> getBlogFeed(Long userid, String filterCondition, Pageable pageRef);

	@Query(value = Queries.GET_TOP_LIKED_BLOGS, nativeQuery = true)
	List<Map<String, Object>> getTopLikedBlogs(Long userid);

	@Query(value = Queries.GET_BLOG_UNV_BASED_QUERY_FOR_USER, nativeQuery = true)
	List<Map<String, Object>> getMyBlogs(Long userid, String filterCondition);
}
