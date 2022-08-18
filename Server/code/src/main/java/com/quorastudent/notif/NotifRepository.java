package com.quorastudent.notif;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface NotifRepository extends JpaRepository<NotifDTO, Long> {

	@Transactional
	@Modifying
	@Query(value="delete  from  notifications n  where n.userid = :userid and n.id in ( :ids ) ",nativeQuery = true)
	void deleteSeenNotifications(Long userid, List<Integer> ids);

	List<NotifDTO> findByUserid(Long userid);
}
