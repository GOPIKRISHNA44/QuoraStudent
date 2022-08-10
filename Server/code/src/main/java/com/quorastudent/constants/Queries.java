package com.quorastudent.constants;

public final class Queries {

	public static final String FEED_QUERY = "SELECT q.*, u.username,u.avatarid,   "
			+ " COALESCE(SUM(ld.updwnvt = 1), 0) AS likes,   "
			+ "       COALESCE(SUM(ld.updwnvt = 0), 0) AS dislikes   " + "from questions q    "
			+ "INNER JOIN userdetails u ON u.userid=q.userid AND u.universitycode = ?   "
			+ "left JOIN likedislike ld ON ld.ctype = 'q' AND ld.parentid = q.eqid   "
			+ "WHERE q.active=1 AND q.ctype = 'q'  GROUP BY q.eqid ";

	public static final String GET_QUESTION_QUERY = "SELECT q.*, e.fromdate,e.todate,u.username as usernameOfWhoAskedThisQuestion, u.avatarid as avataridOfWhoAskedThisQuestion,COALESCE(SUM(ld.updwnvt = 1), 0) AS totalNumberOfLikes,            "
			+ "COALESCE(SUM(ld.updwnvt = 0), 0) AS totalNumberOfDislikes, COUNT(c.parentid) AS totalNumberOfComments   "
			+ ",    "
			+ "case when (SELECT COUNT(1) from likedislike lds WHERE lds.parentid = q.eqid AND lds.ctype=:ctype AND lds.userid = :userid AND lds.updwnvt=1 )  =1 then TRUE ELSE FALSE END AS likedByTheRequestedUser ,   "
			+ "case when (SELECT COUNT(1) from likedislike lds WHERE lds.parentid = q.eqid AND lds.ctype=:ctype AND lds.userid = :userid AND lds.updwnvt=0 )  =1 then TRUE ELSE FALSE END  AS disLikedByTheRequestedUser,   "
			+ "case when q.userid = :userid then TRUE ELSE FALSE END AS questionOwnedByTheRequestedUser   "
			+ " FROM questions q  inner join userdetails  u ON q.userid=u.userid  LEFT JOIN `events` e ON e.eid = q.eqid     "
			+ "left JOIN likedislike ld ON ld.ctype = :ctype AND ld.parentid = q.eqid   "
			+ "LEFT JOIN comments c ON c.parentid = q.eqid AND q.ctype = :ctype   "
			+ "WHERE q.active=1 AND q.ctype = :ctype and q.eqid = :eqid  GROUP BY q.eqid   " + "LIMIT 1   " + "   "
			+ "";

	public static final String GET_ANSWERS_FOR_QUESTION_OR_ENTITY = "SELECT a.*,u.username AS answeredByUsername,u.avatarid as answeredByUsernameAvatarid,  "
			+ " COALESCE(SUM(ld.updwnvt = 1), 0) AS totalNumberOfLikes,         "
			+ "COALESCE(SUM(ld.updwnvt = 0), 0) AS totalNumberOfDislikes, COUNT(c.parentid) AS totalNumberOfComments,  "
			+ "case when (SELECT COUNT(1) from likedislike lds WHERE lds.parentid = a.aid AND lds.ctype='A' AND lds.userid = :userid AND lds.updwnvt=1 )  =1 then TRUE ELSE FALSE END AS likedByTheRequestedUser ,  "
			+ "case when (SELECT COUNT(1) from likedislike lds WHERE lds.parentid = a.aid AND lds.ctype='A' AND lds.userid = :userid AND lds.updwnvt=0 )  =1 then TRUE ELSE FALSE END  AS disLikedByTheRequestedUser,  "
			+ "case when a.userid = :userid then TRUE ELSE FALSE END AS answerOwnedByTheRequestedUser  "
			+ " FROM answers a   "
			+ "INNER JOIN userdetails u ON  a.userid = u.userid AND a.eqid = :eqid AND a.ctype= :ctype   "
			+ "left JOIN likedislike ld ON ld.ctype = 'A' AND ld.parentid = a.aid   "
			+ "LEFT JOIN comments c ON c.parentid = a.aid AND a.ctype = 'A'  "
			+ "WHERE a.active=1 AND a.ctype=:ctype   GROUP BY a.aid  " + "";

	public static final String GET_COMMENTS_LIST = "SELECT c.*,u.username AS commentedByUsername,u.avatarid as commentedByUsernameAvatarid,  "
			+ "COALESCE(SUM(ld.updwnvt = 1), 0) AS totalNumberOfLikes,           "
			+ "COALESCE(SUM(ld.updwnvt = 0), 0) AS totalNumberOfDislikes, COUNT(c.parentid) AS totalNumberOfComments,  "
			+ "case when (SELECT COUNT(1) from likedislike lds WHERE lds.parentid = c.cid AND lds.ctype= :ctype AND lds.userid = :userid AND lds.updwnvt=1 )  =1 then TRUE ELSE FALSE END AS likedByTheRequestedUser ,  "
			+ "case when (SELECT COUNT(1) from likedislike lds WHERE lds.parentid = c.cid AND lds.ctype= :ctype AND lds.userid = :userid AND lds.updwnvt=0 )  =1 then TRUE ELSE FALSE END  AS disLikedByTheRequestedUser,  "
			+ "case when c.userid = :userid then TRUE ELSE FALSE END AS commentOwnedByTheRequestedUser  "
			+ " FROM comments c   " + " INNER join userdetails u ON u.userid = c.userid AND c.parentid = :eqabcid  "
			+ "LEFT JOIN likedislike ld ON c.cid = ld.parentid and ld.ctype='C'  "
			+ "WHERE c.parentid = :eqabcid AND c.ctype = :ctype  " + "GROUP BY c.cid order by c.doc   ";

	public static final String GET_QUESTION_UNV_BASED_QUERY = "SELECT q.*, e.fromdate,e.todate,u.username AS usernameOfWhoAskedThisQuestion, u.avatarid AS avataridOfWhoAskedThisQuestion, COALESCE(SUM(ld.updwnvt = 1), 0) AS totalNumberOfLikes, COALESCE(SUM(ld.updwnvt = 0), 0) AS totalNumberOfDislikes, COUNT(c.parentid) AS totalNumberOfComments   "
			+ "			, CASE WHEN (  " + "SELECT COUNT(1)  " + "FROM likedislike lds  "
			+ "WHERE lds.parentid = q.eqid AND lds.ctype= :ctype  AND lds.userid = :userid AND lds.updwnvt=1) =1 THEN TRUE ELSE FALSE END AS likedByTheRequestedUser, CASE WHEN (  "
			+ "SELECT COUNT(1)  " + "FROM likedislike lds  "
			+ "WHERE lds.parentid = q.eqid AND lds.ctype= :ctype  AND lds.userid = :userid AND lds.updwnvt=0) =1 THEN TRUE ELSE FALSE END AS disLikedByTheRequestedUser, CASE WHEN q.userid = :userid THEN TRUE ELSE FALSE END AS questionOwnedByTheRequestedUser  "
			+ "FROM questions q  "
			+ "INNER JOIN userdetails u ON q.userid=u.userid AND u.universitycode = (SELECT ud.universitycode FROM userdetails ud WHERE ud.userid = :userid LIMIT 1)  "
			+ "LEFT JOIN `events` e ON e.eid = q.eqid  "
			+ "LEFT JOIN likedislike ld ON ld.ctype= :ctype  AND ld.parentid = q.eqid  "
			+ "LEFT JOIN comments c ON c.parentid = q.eqid AND q.ctype= :ctype   "
			+ "WHERE q.active=1 AND q.ctype= :ctype  AND q.eqid NOT IN ()  " + "GROUP BY q.eqid  "
			+ "ORDER BY q.doq DESC, totalNumberOfLikes DESC, totalNumberOfComments DESC, likedByTheRequestedUser ASC, questionOwnedByTheRequestedUser ASC, disLikedByTheRequestedUser ASC";
}
