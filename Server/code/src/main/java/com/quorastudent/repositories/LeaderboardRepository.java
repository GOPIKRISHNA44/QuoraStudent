package com.quorastudent.repositories;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.quorastudent.dto.LeaderboardDTO;

public interface LeaderboardRepository extends JpaRepository<LeaderboardDTO, Integer> {

	@Transactional
	@Modifying
	@Query("delete  from  LeaderboardDTO l  where l.unvcode = :unvcode ")
	void deleteLeaderBoardForAnUniversity(String unvcode);

	@Query(value = "SELECT :unvcode as unvcode ,Q.usernameOfWhoAskedThisQuestion AS username,avataridOfWhoAskedThisQuestion as useravatarid, SUM(Q.totalNumberOfLikes) AS numberoflikes  "
			+ "			       FROM (       "
			+ "			       SELECT q.*, e.fromdate,e.todate,u.username AS usernameOfWhoAskedThisQuestion, u.avatarid AS avataridOfWhoAskedThisQuestion, COALESCE(SUM(ld.updwnvt = 1), 0) AS totalNumberOfLikes,  "
			+ "					  COALESCE(SUM(ld.updwnvt = 0), 0) AS totalNumberOfDislikes        "
			+ "			       			  "
			+ "			       FROM questions q             INNER JOIN userdetails u ON q.userid=u.userid AND u.universitycode = :unvcode   "
			+ "			       LEFT JOIN `events` e ON e.eid = q.eqid       "
			+ "			       LEFT JOIN likedislike ld ON ld.ctype = 'Q' AND ld.parentid = q.eqid       "
			+ "			       WHERE q.active=1 AND q.ctype = 'Q'      "
			+ "			       GROUP BY q.eqid             								             				) q       "
			+ "			                 GROUP BY q.usernameOfWhoAskedThisQuestion     ORDER BY numberoflikes desc limit 10 "
			+ "			       ", nativeQuery = true)
	List<Map<String, Object>> getLeaderBoard(String unvcode);

	List<LeaderboardDTO> findByUnvcode(String unvcode);
}
