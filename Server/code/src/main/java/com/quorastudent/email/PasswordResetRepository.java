package com.quorastudent.email;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface PasswordResetRepository extends JpaRepository<PasswordresetDTO, Long> {

	List<PasswordresetDTO> findByEmailid(String emailid);

	List<PasswordresetDTO> findByEmailidAndPasswordresetlink(String emailid, String link);

	@Transactional
	@Modifying
	@Query("delete  from  PasswordresetDTO p  where p.emailid = :emailid and p.passwordresetlink = :link")
	void deleteActivatedRecords(String emailid, String link);
	

	@Transactional
	@Modifying
	@Query("update PasswordresetDTO u set u.active = 0 where u.emailid = :emailid ")
	void updatePasswordLinkActiveStatus(String emailid);
}
