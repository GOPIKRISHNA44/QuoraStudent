package com.quorastudent.constants;

public final class Queries {

	public static final String FEED_QUERY = "SELECT q.*, u.username,u.avatarid,   "
			+ " COALESCE(SUM(ld.updwnvt = 1), 0) AS likes,   "
			+ "       COALESCE(SUM(ld.updwnvt = 0), 0) AS dislikes   " + "from questions q    "
			+ "INNER JOIN userdetails u ON u.userid=q.userid AND u.universitycode = ?   "
			+ "left JOIN likedislike ld ON ld.ctype = 'q' AND ld.parentid = q.eqid   "
			+ "WHERE q.active=1 AND q.ctype = 'q'  GROUP BY q.eqid ";

	public static final String GET_QUESTION_QUERY = "\r\n"
			+ "SELECT q.*, COUNT(c.parentid) AS totalNumberOfComments  FROM (\r\n"
			+ "SELECT q.*, e.fromdate,e.todate,u.username AS usernameOfWhoAskedThisQuestion, u.avatarid AS avataridOfWhoAskedThisQuestion, COALESCE(SUM(ld.updwnvt = 1), 0) AS totalNumberOfLikes, COALESCE(SUM(ld.updwnvt = 0), 0) AS totalNumberOfDislikes\r\n"
			+ "			, CASE WHEN (\r\n" + "SELECT COUNT(1)\r\n" + "FROM likedislike lds\r\n"
			+ "WHERE lds.parentid = q.eqid AND lds.ctype=    :ctype AND lds.userid =  :userid AND lds.updwnvt=1) =1 THEN TRUE ELSE FALSE END AS likedByTheRequestedUser, CASE WHEN (\r\n"
			+ "SELECT COUNT(1)\r\n" + "FROM likedislike lds\r\n"
			+ "WHERE lds.parentid = q.eqid AND lds.ctype=    :ctype AND lds.userid =  :userid AND lds.updwnvt=0) =1 THEN TRUE ELSE FALSE END AS disLikedByTheRequestedUser, CASE WHEN q.userid =  :userid THEN TRUE ELSE FALSE END AS questionOwnedByTheRequestedUser\r\n"
			+ "FROM questions q\r\n" + "INNER JOIN userdetails u ON q.userid=u.userid\r\n"
			+ "LEFT JOIN `events` e ON e.eid = q.eqid\r\n"
			+ "LEFT JOIN likedislike ld ON ld.ctype =     :ctype AND ld.parentid = q.eqid\r\n" + "\r\n"
			+ "WHERE q.active=1 AND q.ctype =     :ctype AND q.eqid =    :eqid\r\n" + "GROUP BY q.eqid\r\n" + ") q \r\n"
			+ "\r\n" + "\r\n" + "LEFT JOIN comments c ON c.parentid = q.eqid AND q.ctype =     :ctype\r\n" + "\r\n"
			+ "LIMIT 1";

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
			+ "WHERE a.active=1 AND a.ctype=:ctype   GROUP BY a.aid  order by a.doa desc, totalNumberOfLikes desc "
			+ "";

	public static final String GET_COMMENTS_LIST = "SELECT c.*,u.username AS commentedByUsername,u.avatarid as commentedByUsernameAvatarid,  "
			+ "COALESCE(SUM(ld.updwnvt = 1), 0) AS totalNumberOfLikes,           "
			+ "COALESCE(SUM(ld.updwnvt = 0), 0) AS totalNumberOfDislikes, COUNT(c.parentid) AS totalNumberOfComments,  "
			+ "case when (SELECT COUNT(1) from likedislike lds WHERE lds.parentid = c.cid AND lds.ctype= :ctype AND lds.userid = :userid AND lds.updwnvt=1 )  =1 then TRUE ELSE FALSE END AS likedByTheRequestedUser ,  "
			+ "case when (SELECT COUNT(1) from likedislike lds WHERE lds.parentid = c.cid AND lds.ctype= :ctype AND lds.userid = :userid AND lds.updwnvt=0 )  =1 then TRUE ELSE FALSE END  AS disLikedByTheRequestedUser,  "
			+ "case when c.userid = :userid then TRUE ELSE FALSE END AS commentOwnedByTheRequestedUser  "
			+ " FROM comments c   " + " INNER join userdetails u ON u.userid = c.userid AND c.parentid = :eqabcid  "
			+ "LEFT JOIN likedislike ld ON c.cid = ld.parentid and ld.ctype='C'  "
			+ "WHERE c.parentid = :eqabcid AND c.ctype = :ctype  "
			+ "GROUP BY c.cid order by c.doc desc , totalNumberOfLikes desc ";

	public static final String GET_QUESTION_OR_ENTITY_UNV_BASED_QUERY = "SELECT q.*, COUNT(c.parentid) AS totalNumberOfComments  "
			+ "FROM (  "
			+ "SELECT q.*, e.fromdate,e.todate,u.username AS usernameOfWhoAskedThisQuestion, u.avatarid AS avataridOfWhoAskedThisQuestion, COALESCE(SUM(ld.updwnvt = 1), 0) AS totalNumberOfLikes, COALESCE(SUM(ld.updwnvt = 0), 0) AS totalNumberOfDislikes   "
			+ "			, CASE WHEN (  " + "SELECT COUNT(1)  " + "FROM likedislike lds  "
			+ "WHERE lds.parentid = q.eqid AND lds.ctype=:ctype AND lds.userid = :userid AND lds.updwnvt=1) =1 THEN TRUE ELSE FALSE END AS likedByTheRequestedUser, CASE WHEN (  "
			+ "SELECT COUNT(1)  " + "FROM likedislike lds  "
			+ "WHERE lds.parentid = q.eqid AND lds.ctype=:ctype AND lds.userid = :userid AND lds.updwnvt=0) =1 THEN TRUE ELSE FALSE END AS disLikedByTheRequestedUser, CASE WHEN q.userid = :userid THEN TRUE ELSE FALSE END AS questionOwnedByTheRequestedUser  "
			+ "FROM questions q  " + "INNER JOIN userdetails u ON q.userid=u.userid AND u.universitycode = (  "
			+ "SELECT ud.universitycode  " + "FROM userdetails ud  " + "WHERE ud.userid = :userid)  "
			+ "LEFT JOIN `events` e ON e.eid = q.eqid  "
			+ "LEFT JOIN likedislike ld ON ld.ctype = :ctype AND ld.parentid = q.eqid  "
			+ "WHERE q.active=1 AND q.ctype = :ctype AND q.question LIKE CONCAT('%',:filterCondition,'%')  "
			+ "GROUP BY q.eqid  " + "								  " + "				) q  "
			+ "LEFT JOIN comments c ON c.parentid = q.eqid AND q.ctype = :ctype  " + "GROUP BY q.eqid  "
			+ "ORDER BY 	 q.todate ASC, q.doq DESC, totalNumberOfLikes DESC, questionOwnedByTheRequestedUser ASC, disLikedByTheRequestedUser ASC ";

	public static final String GET_QUESTION_UNV_BASED_QUERY_COUNT_QUERY = "SELECT A.* FROM ( "
			+ GET_QUESTION_OR_ENTITY_UNV_BASED_QUERY + " ) A";

	public static final String GET_BLOG_UNV_BASED_QUERY = "SELECT b.*, COUNT(c.parentid) AS totalNumberOfComments   "
			+ "FROM (   " + "SELECT b.*,u.username AS usernameOfWhoAskedThisBlog,    "
			+ "			 u.avatarid AS avataridOfWhoAskedThisBlog, COALESCE(SUM(ld.updwnvt = 1), 0) AS totalNumberOfLikes, COALESCE(SUM(ld.updwnvt = 0), 0) AS totalNumberOfDislikes   "
			+ "			    " + "			 			, CASE WHEN (   " + "SELECT COUNT(1)   "
			+ "FROM likedislike lds   "
			+ "WHERE lds.parentid = b.bid AND lds.ctype='B' AND lds.userid = :userid AND lds.updwnvt=1) =1 THEN TRUE ELSE FALSE END AS likedByTheRequestedUser, CASE WHEN (   "
			+ "SELECT COUNT(1)   " + "FROM likedislike lds   "
			+ "WHERE lds.parentid = b.bid AND lds.ctype='B' AND lds.userid = :userid AND lds.updwnvt=0) =1 THEN TRUE ELSE FALSE END AS disLikedByTheRequestedUser, CASE WHEN b.userid = :userid THEN TRUE ELSE FALSE END AS blogOwnedByTheRequestedUser   "
			+ "FROM blog b   " + "INNER JOIN userdetails u ON b.userid=u.userid AND u.universitycode = (   "
			+ "SELECT ud.universitycode   " + "FROM userdetails ud   " + "WHERE ud.userid = :userid)   "
			+ "LEFT JOIN likedislike ld ON ld.ctype = 'B' AND ld.parentid = b.bid   " + "GROUP BY b.bid    "
			+ "			) b   " + "LEFT JOIN comments c ON c.parentid = b.bid AND c.ctype = 'B'   "
			+ "WHERE b.active=1 AND b.content LIKE CONCAT('%',:filterCondition,'%')   " + "GROUP BY b.bid   "
			+ "ORDER BY b.doblog DESC, totalNumberOfLikes DESC, blogOwnedByTheRequestedUser ASC, disLikedByTheRequestedUser ASC ";

	public static final String GET_BLOG_UNV_BASED_QUERY_COUNT_QUERY = "SELECT A.* FROM ( " + GET_BLOG_UNV_BASED_QUERY
			+ " ) A";

	public static final String GET_TAG_RELATED_QUESTIONS_HEADER_PART = "SELECT q.*, e.fromdate,e.todate,u.username as usernameOfWhoAskedThisQuestion, u.avatarid as avataridOfWhoAskedThisQuestion,COALESCE(SUM(ld.updwnvt = 1), 0) AS totalNumberOfLikes,                    "
			+ "			             COALESCE(SUM(ld.updwnvt = 0), 0) AS totalNumberOfDislikes, COUNT(c.parentid) AS totalNumberOfComments           "
			+ "			             ,            "
			+ "			             case when (SELECT COUNT(1) from likedislike lds WHERE lds.parentid = q.eqid AND lds.ctype= :ctype AND lds.userid = :userid AND lds.updwnvt=1 )  =1 then TRUE ELSE FALSE END AS likedByTheRequestedUser ,           "
			+ "			             case when (SELECT COUNT(1) from likedislike lds WHERE lds.parentid = q.eqid AND lds.ctype= :ctype AND lds.userid = :userid AND lds.updwnvt=0 )  =1 then TRUE ELSE FALSE END  AS disLikedByTheRequestedUser,           "
			+ "			             case when q.userid = :userid then TRUE ELSE FALSE END AS questionOwnedByTheRequestedUser           "
			+ "			              FROM questions q  inner join userdetails  u ON q.userid=u.userid and u.universitycode =  (SELECT ud.universitycode FROM userdetails ud WHERE ud.userid = :userid )          "
			+ "			               LEFT JOIN `events` e ON e.eid = q.eqid             "
			+ "			             left JOIN likedislike ld ON ld.ctype =  :ctype AND ld.parentid = q.eqid           "
			+ "			             LEFT JOIN comments c ON c.parentid = q.eqid AND q.ctype =  :ctype           "
			+ "			             WHERE q.active=1 AND q.ctype =  :ctype  ";

	public static final String GET_TAG_RELATED_QUESTIONS_FOOTER_PART = "	 GROUP BY q.eqid order by  e.todate ASC , q.doq desc, totalNumberOfLikes desc, questionOwnedByTheRequestedUser asc, disLikedByTheRequestedUser asc LIMIT 5 ";

	public static final String GET_TOP_LIKED_BLOGS = "SELECT b.*,u.username AS usernameOfWhoAskedThisBlog,         "
			+ "u.avatarid AS avataridOfWhoAskedThisBlog, COALESCE(SUM(ld.updwnvt = 1), 0) AS totalNumberOfLikes, COALESCE(SUM(ld.updwnvt = 0), 0) AS totalNumberOfDislikes, COUNT(c.parentid) AS totalNumberOfComments         "
			+ "			, CASE WHEN (        " + "SELECT COUNT(1)        " + "FROM likedislike lds        "
			+ "WHERE lds.parentid = b.bid AND lds.ctype='B' AND lds.userid = :userid AND lds.updwnvt=1) =1 THEN TRUE ELSE FALSE END AS likedByTheRequestedUser, CASE WHEN (        "
			+ "SELECT COUNT(1)        " + "FROM likedislike lds        "
			+ "WHERE lds.parentid = b.bid AND lds.ctype='B' AND lds.userid = :userid AND lds.updwnvt=0) =1 THEN TRUE ELSE FALSE END AS disLikedByTheRequestedUser, CASE WHEN b.userid = :userid THEN TRUE ELSE FALSE END AS blogOwnedByTheRequestedUser        "
			+ "FROM blog b        " + "INNER JOIN userdetails u ON b.userid=u.userid AND u.universitycode = (        "
			+ "SELECT ud.universitycode        " + "FROM userdetails ud        " + "WHERE ud.userid = :userid)        "
			+ "LEFT JOIN likedislike ld ON ld.ctype = 'B' AND ld.parentid = b.bid        "
			+ "LEFT JOIN comments c ON c.parentid = b.bid AND c.ctype = 'B'        "
			+ "WHERE b.active=1  GROUP BY b.bid        "
			+ "ORDER BY  totalNumberOfLikes DESC, b.doblog DESC,  blogOwnedByTheRequestedUser ASC, disLikedByTheRequestedUser ASC limit 5 ";

	public static final String GET_QUESTION_OR_ENTITY_UNV_BASED_QUERY_USER_BASED = "SELECT t.*, ("
			+ " Case when exists(select * from answers where eqid = t.eqid and ctype = :ctype and active =1  limit 1  ) then 0 else 1 end ) as isEditable FROM ( "
			+ Queries.GET_QUESTION_OR_ENTITY_UNV_BASED_QUERY + ") t where t.userid = :userid";

	public static final String GET_BLOG_UNV_BASED_QUERY_FOR_USER = "SELECT t.*  FROM ("
			+ Queries.GET_BLOG_UNV_BASED_QUERY + ") t where t.userid = :userid";

}
