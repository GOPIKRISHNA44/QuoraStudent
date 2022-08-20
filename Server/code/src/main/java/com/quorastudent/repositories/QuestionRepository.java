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
import com.quorastudent.dto.QuestionDTO;
import com.quorastudent.services.UtilityService;

public interface QuestionRepository extends JpaRepository<QuestionDTO, Long> {

	public static final UtilityService utilityService = new UtilityService();

	@Transactional
	@Modifying
	@Query("update QuestionDTO q  SET q.question = :question , q.updatedat=NOW() , q.tags = :tags  WHERE  q.eqid = :eqid and q.ctype = :ctype")
	void updateQuestion(Long eqid, String ctype, String question, String tags);

	@Transactional
	@Modifying
	@Query("delete  from   QuestionDTO q   where q.eqid = :eqid AND q.ctype = :ctype ")
	void deleteQuestionOrEvent(Long eqid, String ctype);

	@Query(value = "SELECT q.*, e.fromdate,e.todate,u.username AS usernameOfWhoAskedThisQuestion, u.avatarid AS avataridOfWhoAskedThisQuestion, COALESCE(SUM(ld.updwnvt = 1), 0) AS totalNumberOfLikes, COALESCE(SUM(ld.updwnvt = 0), 0) AS totalNumberOfDislikes, COUNT(c.parentid) AS totalNumberOfComments   "
			+ "			, CASE WHEN (  " + "SELECT COUNT(1)  " + "FROM likedislike lds  "
			+ "WHERE lds.parentid = q.eqid AND lds.ctype= :ctype  AND lds.userid = :userid AND lds.updwnvt=1) =1 THEN TRUE ELSE FALSE END AS likedByTheRequestedUser, CASE WHEN (  "
			+ "SELECT COUNT(1)  " + "FROM likedislike lds  "
			+ "WHERE lds.parentid = q.eqid AND lds.ctype= :ctype  AND lds.userid = :userid AND lds.updwnvt=0) =1 THEN TRUE ELSE FALSE END AS disLikedByTheRequestedUser, CASE WHEN q.userid = :userid THEN TRUE ELSE FALSE END AS questionOwnedByTheRequestedUser  "
			+ "FROM questions q  "
			+ "INNER JOIN userdetails u ON q.userid=u.userid AND u.universitycode = (SELECT ud.universitycode FROM userdetails ud WHERE ud.userid = :userid LIMIT 1)  "
			+ "LEFT JOIN `events` e ON e.eid = q.eqid  "
			+ "LEFT JOIN likedislike ld ON ld.ctype= :ctype  AND ld.parentid = q.eqid  "
			+ "LEFT JOIN comments c ON c.parentid = q.eqid AND q.ctype= :ctype   "
			+ "WHERE q.active=1 AND q.ctype= :ctype  AND q.eqid NOT IN ( :visitedEqids )  " + "GROUP BY q.eqid  "
			+ "ORDER BY q.doq DESC, totalNumberOfLikes DESC, totalNumberOfComments DESC, likedByTheRequestedUser ASC, questionOwnedByTheRequestedUser ASC,"
			+ " disLikedByTheRequestedUser ASC", nativeQuery = true)
	Page<List<Map<String, Object>>> getQuestionsFeed(Long userid, String ctype, List<Long> visitedEqids,
			Pageable pageable);

	@Query(value = "SELECT q.* FROM questions q  "
			+ " INNER JOIN userdetails u ON q.userid=u.userid AND u.universitycode = (SELECT ud.universitycode FROM userdetails ud WHERE ud.userid = :userid LIMIT 1)  "
			+ " LEFT JOIN `events` e ON e.eid = q.eqid  "
			+ " LEFT JOIN likedislike ld ON ld.ctype= :ctype  AND ld.parentid = q.eqid  "
			+ " LEFT JOIN comments c ON c.parentid = q.eqid AND q.ctype= :ctype   "
			+ " WHERE q.active=1 AND q.ctype= :ctype   GROUP BY q.eqid  " + " " + " ", nativeQuery = true)
	Page<List<Map<String, Object>>> getQuestionsFeedn(Long userid, String ctype, Pageable pageable);

	@Query(value = "SELECT q.*, e.fromdate,e.todate,u.username AS usernameOfWhoAskedThisQuestion, u.avatarid AS avataridOfWhoAskedThisQuestion, COALESCE(SUM(ld.updwnvt = 1), 0) AS totalNumberOfLikes, COALESCE(SUM(ld.updwnvt = 0), 0) AS totalNumberOfDislikes, COUNT(c.parentid) AS totalNumberOfComments   "
			+ "			, CASE WHEN (  " + "SELECT COUNT(1)  " + "FROM likedislike lds  "
			+ "WHERE lds.parentid = q.eqid AND lds.ctype= :ctype  AND lds.userid = :userid AND lds.updwnvt=1) =1 THEN TRUE ELSE FALSE END AS likedByTheRequestedUser, CASE WHEN (  "
			+ "SELECT COUNT(1)  " + "FROM likedislike lds  "
			+ "WHERE lds.parentid = q.eqid AND lds.ctype= :ctype  AND lds.userid = :userid AND lds.updwnvt=0) =1 THEN TRUE ELSE FALSE END AS disLikedByTheRequestedUser, CASE WHEN q.userid = :userid THEN TRUE ELSE FALSE END AS questionOwnedByTheRequestedUser  "
			+ "FROM questions q  "
			+ "INNER JOIN userdetails u ON q.userid=u.userid AND u.universitycode = (SELECT ud.universitycode FROM userdetails ud WHERE ud.userid = :userid LIMIT 1)  "
			+ "LEFT JOIN `events` e ON e.eid = q.eqid  "
			+ "LEFT JOIN likedislike ld ON ld.ctype= :ctype  AND ld.parentid = q.eqid  "
			+ "LEFT JOIN comments c ON c.parentid = q.eqid AND q.ctype= :ctype   "
			+ "WHERE q.active=1 AND q.ctype= :ctype    " + "GROUP BY q.eqid  "
			+ "ORDER BY q.doq DESC, totalNumberOfLikes DESC, totalNumberOfComments DESC, likedByTheRequestedUser ASC, questionOwnedByTheRequestedUser ASC,"
			+ " disLikedByTheRequestedUser ASC", nativeQuery = true)
	List<Map<String, Object>> getQuestionsFeedTest(Long userid, String ctype);

	@Query(value = Queries.GET_QUESTION_OR_ENTITY_UNV_BASED_QUERY, countQuery = Queries.GET_QUESTION_UNV_BASED_QUERY_COUNT_QUERY, nativeQuery = true)
	Page<List<Map<String, Object>>> getQuestionsOrEventFeed(Long userid, String ctype, String filterCondition,
			Pageable pageRef);

	QuestionDTO findByEqidAndCtype(Long eqid, String ctype);

	@Query(value = Queries.GET_QUESTION_OR_ENTITY_UNV_BASED_QUERY_USER_BASED, nativeQuery = true)
	List<Map<String, Object>> getQuestionsOrEventsOfAUser(Long userid, String ctype,String filterCondition);

}
