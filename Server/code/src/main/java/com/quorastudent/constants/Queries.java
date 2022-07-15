package com.quorastudent.constants;

public final class Queries {

	public static final String FEED_QUERY = "SELECT q.*, u.username,u.avatarid,   "
			+ " COALESCE(SUM(ld.updwnvt = 1), 0) AS likes,   "
			+ "       COALESCE(SUM(ld.updwnvt = 0), 0) AS dislikes   " + "from questions q    "
			+ "INNER JOIN userdetails u ON u.userid=q.userid AND u.universitycode = ?   "
			+ "left JOIN likedislike ld ON ld.ctype = 'q' AND ld.parentid = q.eqid   "
			+ "WHERE q.active=1 AND q.ctype = 'q'  GROUP BY q.eqid ";

}
