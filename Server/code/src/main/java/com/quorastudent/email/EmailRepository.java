package com.quorastudent.email;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface EmailRepository extends JpaRepository<EmailactivatorDTO, Long> {

	List<EmailactivatorDTO> findByEmailactivatingtext(String q);

	@Transactional
	@Modifying
	@Query("delete  from  EmailactivatorDTO e  where e.userid= :userid")
	void deleteActivatedRecords(Long userid);

}
