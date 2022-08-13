package com.quorastudent.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.quorastudent.dto.UserDetailsDTO;

public interface UserRepository extends JpaRepository<UserDetailsDTO, Integer> {

	List<UserDetailsDTO> findByUsername(String username);

	List<UserDetailsDTO> findByEmailid(String username);

	List<UserDetailsDTO> findByUsernameAndPassword(String username, String password);

	List<UserDetailsDTO> findByEmailidAndPassword(String emailid, String password);

	List<UserDetailsDTO> findByUserid(Long userid);
	
	List<UserDetailsDTO> findByUsernameOrEmailid(String username, String emailid);
	

	@Transactional
	@Modifying
	@Query("update UserDetailsDTO u set u.interestspopup = 1 where u.userid = :userid ")
	void updateInterestsPopup(Long userid);
	
	@Transactional
	@Modifying
	@Query("update UserDetailsDTO u set u.emailidactivated = 1 where u.userid = :userid ")
	void updateEmailActivation(Long userid);

}