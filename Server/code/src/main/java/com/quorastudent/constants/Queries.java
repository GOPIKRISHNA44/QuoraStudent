package com.quorastudent.constants;

public final class Queries {

	public static final String FEED_QUERY = "SELECT q.*, u.username,u.avatarid,   "
			+ " COALESCE(SUM(ld.updwnvt = 1), 0) AS likes,   "
			+ "       COALESCE(SUM(ld.updwnvt = 0), 0) AS dislikes   " + "from questions q    "
			+ "INNER JOIN userdetails u ON u.userid=q.userid AND u.universitycode = ?   "
			+ "left JOIN likedislike ld ON ld.ctype = 'q' AND ld.parentid = q.eqid   "
			+ "WHERE q.active=1 AND q.ctype = 'q'  GROUP BY q.eqid ";

	public static final String GET_QUESTION_QUERY = "SELECT q.*,u.username,COALESCE(SUM(ld.updwnvt = 1), 0) AS totalNumberOfLikes,            "
			+ "COALESCE(SUM(ld.updwnvt = 0), 0) AS totalNumberOfDislikes, COUNT(c.parentid) AS totalNumberOfComments   "
			+ ",    "
			+ "case when (SELECT COUNT(1) from likedislike lds WHERE lds.parentid = q.eqid AND lds.ctype=:ctype AND lds.userid = :userid AND lds.updwnvt=1 )  =1 then TRUE ELSE FALSE END AS likedByTheRequestedUser ,   "
			+ "case when (SELECT COUNT(1) from likedislike lds WHERE lds.parentid = q.eqid AND lds.ctype=:ctype AND lds.userid = :userid AND lds.updwnvt=0 )  =1 then TRUE ELSE FALSE END  AS disLikedByTheRequestedUser,   "
			+ "case when q.userid = :userid then TRUE ELSE FALSE END AS questionOwnedByTheRequestedUser   "
			+ " FROM questions q  inner join userdetails  u ON q.userid=u.userid    "
			+ "left JOIN likedislike ld ON ld.ctype = :ctype AND ld.parentid = q.eqid   "
			+ "LEFT JOIN comments c ON c.parentid = q.eqid AND q.ctype = :ctype   "
			+ "WHERE q.active=1 AND q.ctype = :ctype and q.eqid = :eqid  GROUP BY q.eqid   " + "LIMIT 1   " + "   "
			+ "";

}
