package com.quorastudent.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.quorastudent.dto.UserinterestsDTO;

public interface UserInterestsRepositoy extends JpaRepository<UserinterestsDTO, Long> {

	@Transactional
	@Modifying
	@Query("update UserinterestsDTO u set  u.interests= :interests where u.userid = :userid")
	void updateInterests(Long userid, String interests);

	List<UserinterestsDTO> findByUserid(Long userid);
}
