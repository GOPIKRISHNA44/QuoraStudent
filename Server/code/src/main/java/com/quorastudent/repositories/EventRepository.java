package com.quorastudent.repositories;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.quorastudent.dto.EventDTO;

public interface EventRepository extends JpaRepository<EventDTO, Long> {

	@Transactional
	@Modifying
	@Query("DELETE  FROM  EventDTO e WHERE e.eid= :eid ")
	void deleteEvent(Long eid);

	@Transactional
	@Modifying
	@Query("update EventDTO e  SET e.fromdate = :fromDate, e.todate = :toDate  WHERE e.eid = :eid")
	void updateEvent(Long eid, Date fromDate, Date toDate);

	
}
