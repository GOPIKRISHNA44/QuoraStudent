package com.quorastudent.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.quorastudent.dto.EventDTO;

public interface EventRepository extends JpaRepository<EventDTO, Long> {

	

	@Transactional
	@Modifying
	@Query("DELETE  FROM  EventDTO e WHERE e.eid= :eid ")
	void deleteComment(Long eid);

}
