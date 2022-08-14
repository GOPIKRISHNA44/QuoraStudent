package com.quorastudent.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.quorastudent.dto.CommentsDTO;
import com.quorastudent.dto.QuestionDTO;
import com.quorastudent.dto.UserDetailsDTO;
import com.quorastudent.notif.NotifService;
import com.quorastudent.repositories.AnswerRepository;
import com.quorastudent.repositories.CommentsRepository;
import com.quorastudent.repositories.QuestionRepository;
import com.quorastudent.repositories.UserRepository;

@Service
public class GenericNotifService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private AnswerRepository answerRepository;

	@Autowired
	private CommentsRepository commentsRepository;

	@Autowired
	private NotifService notifService;

	@Value("${currentUiHosting}")
	private String currentUiHosting;

	/*
	 * This if for Question or Entity
	 */

	public String getViewQuestionOrEntityUrl(Long eqid, String ctype) {
		String fullUrl = String.format("%s%s", currentUiHosting,
				String.format("/home/question?eqid=%d&ctype=%s", eqid, ctype));
		// String fullUrl = currentUiHosting + endPart;
		return fullUrl;

	}

	public void noifForQuestionOrEventHasBeenPosted(Long userid, String question, String ctype, Long eqid)
			throws Exception {
		try {
			UserDetailsDTO userDetails = getUserDetails(userid);
			if (!ObjectUtils.isEmpty(userDetails)) {
				String unvcode = userDetails.getUniversitycode();
				List<UserDetailsDTO> userDetailsDTOs = getUserDetailsOfSameUniversity(unvcode);
				if (!ObjectUtils.isEmpty(userDetailsDTOs) && userDetailsDTOs != null && userDetailsDTOs.size() > 0) {
					List<Long> userids = new ArrayList<Long>();
					for (UserDetailsDTO userDetailsDTO : userDetailsDTOs) {
						if (userDetailsDTO.getUserid() != userid) {
							userids.add(userDetailsDTO.getUserid());
						}
					}

					List<String> notifTexts = new ArrayList<String>();
					// morph to html question
					String htmlText = String.format("<span><span> <img src='../../assets/images/avatars/%s.png' style='height: 25px; width: 27px;border-radius: 10px;' class='notifImg' "
							+ " /> </span> <span><a href=%s target=_blank >%s has posted a %s ..view</a></span></span> ",
							String.valueOf(userDetails.getAvatarid()), getViewQuestionOrEntityUrl(eqid, ctype),
							StringUtils.capitalize(userDetails.getUsername()), ctype == "Q" ? "Question" : "Event");
					notifTexts.addAll(Collections.nCopies(userids.size(), htmlText));
					notifService.saveAnotifBulk(userids, notifTexts);
				}
			}

		} catch (

		Exception e) {
			// TODO: handle exception
			throw e;
		}
	}

	public void notifOnLikeQuestionOrEvent(Long likedUserid, Long eqid, String ctype) throws Exception {
		try {
			UserDetailsDTO userDetailsDTOOfWhoLikedTeQuestion = getUserDetails(likedUserid);
			QuestionDTO questionDTO = null;
			switch (ctype) {
			case "Q":
				Long qid = eqid;
				questionDTO = questionRepository.findByEqidAndCtype(qid, ctype);
				if (!ObjectUtils.isEmpty(questionDTO)) {
					Long userIdOfTheQuestion = questionDTO.getUserid();
					UserDetailsDTO userDetailsDTOOfWhoQuestionBelongs = getUserDetails(userIdOfTheQuestion);
					String htmlText = String.format("%s has liked your question ",
							userDetailsDTOOfWhoLikedTeQuestion.getUsername());
					notifService.saveAnotif(userDetailsDTOOfWhoQuestionBelongs.getUserid(), htmlText);
				}

				break;

			case "E":
				Long eid = eqid;
				questionDTO = questionRepository.findByEqidAndCtype(eid, ctype);
				if (!ObjectUtils.isEmpty(questionDTO)) {
					Long userIdOfTheQuestion = questionDTO.getUserid();
					UserDetailsDTO userDetailsDTOOfWhoQuestionBelongs = getUserDetails(userIdOfTheQuestion);
					String htmlText = String.format("%s has liked your Event based question ",
							userDetailsDTOOfWhoLikedTeQuestion.getUsername());
					notifService.saveAnotif(userDetailsDTOOfWhoQuestionBelongs.getUserid(), htmlText);
				}

				break;

			}
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}

	public void notifOnAnswerOnQuestionOrEvent(Long answerGivenByUserid, Long eqid, String ctype) throws Exception {
		try {
			QuestionDTO questionDTO = questionRepository.findByEqidAndCtype(eqid, ctype);
			if (!ObjectUtils.isEmpty(questionDTO)) {
				Long userIdOfQuestion = questionDTO.getUserid();
				UserDetailsDTO userDetailsDTOOfWhoQuestionBelongs = getUserDetails(userIdOfQuestion);
				UserDetailsDTO userDetailsForAnswerGivenBy = getUserDetails(answerGivenByUserid);
				String htmlText = String.format("%s has Answered Your %s ", userDetailsForAnswerGivenBy.getUsername(),
						ctype == "Q" ? "Question" : "Entity");
				notifService.saveAnotif(userDetailsDTOOfWhoQuestionBelongs.getUserid(), htmlText);
			}

		} catch (

		Exception e) {
			// TODO: handle exception
		}
	}

	public void notifOnCommentOnQuestionOrEvent(Long commentGivenByUserid, Long eqid, String ctype) throws Exception {
		try {
			QuestionDTO questionDTO = questionRepository.findByEqidAndCtype(eqid, ctype);
			if (!ObjectUtils.isEmpty(questionDTO)) {
				Long userIdOfParent = questionDTO.getUserid();
				UserDetailsDTO userDetailsDTOOfWhoQuestionBelongs = getUserDetails(userIdOfParent);
				UserDetailsDTO userDetailsForCommentGivenBy = getUserDetails(commentGivenByUserid);
				String htmlText = String.format("%s has Commented Your %s ", userDetailsForCommentGivenBy.getUsername(),
						ctype == "Q" ? "Question" : "Entity");
				notifService.saveAnotif(userDetailsDTOOfWhoQuestionBelongs.getUserid(), htmlText);
			}

		} catch (

		Exception e) {
			// TODO: handle exception
		}
	}

	public UserDetailsDTO getUserDetails(Long userid) {
		try {
			List<UserDetailsDTO> userDetailsDTOs = userRepository.findByUserid(userid);
			if (!ObjectUtils.isEmpty(userDetailsDTOs) && userDetailsDTOs != null && userDetailsDTOs.size() > 0) {
				return userDetailsDTOs.get(0);
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
		return null;
	}

	public List<UserDetailsDTO> getUserDetailsOfSameUniversity(String unvcode) {
		try {
			List<UserDetailsDTO> userDetailsDTOs = userRepository.findByUniversitycode(unvcode);
			if (!ObjectUtils.isEmpty(userDetailsDTOs) && userDetailsDTOs != null) {
				return userDetailsDTOs;
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
		return null;
	}

}
