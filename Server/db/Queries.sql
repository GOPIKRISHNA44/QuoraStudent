SELECT q.*,u.username, COALESCE(SUM(ld.updwnvt = 1), 0) AS totalNumberOfLikes, COALESCE(SUM(ld.updwnvt = 0), 0) AS totalNumberOfDislikes, COUNT(c.parentid) AS totalNumberOfComments
, CASE WHEN (
SELECT COUNT(1)
FROM likedislike lds
WHERE lds.parentid = q.eqid AND lds.ctype='q' AND lds.userid = 6 AND lds.updwnvt=1) =1 THEN TRUE ELSE FALSE END AS isLikedByTheRequestedUser, CASE WHEN (
SELECT COUNT(1)
FROM likedislike lds
WHERE lds.parentid = q.eqid AND lds.ctype='q' AND lds.userid = 6 AND lds.updwnvt=0) =1 THEN TRUE ELSE FALSE END AS isLikedByTheRequestedUser, CASE WHEN q.userid = 11 THEN TRUE ELSE FALSE END AS isQuestionOwnedByTheRequestedUser
FROM questions q
INNER JOIN userdetails u ON q.userid=u.userid
LEFT JOIN likedislike ld ON ld.ctype = 'q' AND ld.parentid = q.eqid
LEFT JOIN comments c ON c.parentid = q.eqid AND q.ctype = 'q'
WHERE q.active=1 AND q.ctype = 'q'
GROUP BY q.eqid;

SELECT a.*,u.username AS answeredByUsername,u.avatarid AS answeredByUsernameAvatarid, COALESCE(SUM(ld.updwnvt = 1), 0) AS totalNumberOfLikes, COALESCE(SUM(ld.updwnvt = 0), 0) AS totalNumberOfDislikes, COUNT(c.parentid) AS totalNumberOfComments, CASE WHEN (
SELECT COUNT(1)
FROM likedislike lds
WHERE lds.parentid = a.aid AND lds.ctype='A' AND lds.userid = 6 AND lds.updwnvt=1) =1 THEN TRUE ELSE FALSE END AS isLikedByTheRequestedUser, CASE WHEN (
SELECT COUNT(1)
FROM likedislike lds
WHERE lds.parentid = a.aid AND lds.ctype='A' AND lds.userid = 6 AND lds.updwnvt=0) =1 THEN TRUE ELSE FALSE END AS isDisLikedByTheRequestedUser, CASE WHEN a.userid = 11 THEN TRUE ELSE FALSE END AS isAnswerOwnedByTheRequestedUser
FROM answers a
INNER JOIN userdetails u ON a.userid = u.userid AND a.eqid = 2 AND a.ctype='Q'
LEFT JOIN likedislike ld ON ld.ctype = 'A' AND ld.parentid = a.aid
LEFT JOIN comments c ON c.parentid = a.aid AND a.ctype = 'A'
WHERE a.active=1 AND a.ctype='Q' 
GROUP BY a.aid;


SELECT c.*,u.username AS commentedByUsername,u.avatarid AS commentedByUsernameAvatarid, COALESCE(SUM(ld.updwnvt = 1), 0) AS totalNumberOfLikes, COALESCE(SUM(ld.updwnvt = 0), 0) AS totalNumberOfDislikes, COUNT(c.parentid) AS totalNumberOfComments, CASE WHEN (
SELECT COUNT(1)
FROM likedislike lds
WHERE lds.parentid = c.cid AND lds.ctype='c' AND lds.userid = 6 AND lds.updwnvt=1) =1 THEN TRUE ELSE FALSE END AS isLikedByTheRequestedUser, CASE WHEN (
SELECT COUNT(1)
FROM likedislike lds
WHERE lds.parentid = c.cid AND lds.ctype='c' AND lds.userid = 6 AND lds.updwnvt=0) =1 THEN TRUE ELSE FALSE END AS isDisLikedByTheRequestedUser, CASE WHEN c.userid = 6 THEN TRUE ELSE FALSE END AS isCommentOwnedByTheRequestedUser
FROM comments c
INNER JOIN userdetails u ON u.userid = c.userid AND c.parentid =2
LEFT JOIN likedislike ld ON c.cid = ld.parentid AND  ld.ctype='c'
WHERE c.parentid =2 AND c.ctype = 'q' 
GROUP BY c.cid;




