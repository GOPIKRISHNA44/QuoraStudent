package com.quorastudent.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.quorastudent.constants.AppConstants;
import com.quorastudent.constants.ErrorMsgs;
import com.quorastudent.dto.AskAquestionDTO;
import com.quorastudent.dto.LikedislikeDTO;
import com.quorastudent.dto.QuestionDTO;
import com.quorastudent.dto.QuestionViewDTO;
import com.quorastudent.repositories.JdbcQueryService;
import com.quorastudent.repositories.LikesDislikeRepository;
import com.quorastudent.repositories.QuestionRepository;

@Service
public class QuestionsService {

	@Autowired
	private UtilityService utilityService;

	@Autowired
	private DateUtility dateUtility;

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private LikesDislikeRepository likesDislikeRepository;

	@Autowired
	private JdbcQueryService jdbcQueryService;

	public boolean askAquestion(AskAquestionDTO askAquestionDTO) throws Exception {
		try {
			if (!ObjectUtils.isEmpty(askAquestionDTO) && !ObjectUtils.isEmpty(askAquestionDTO.getText())
					&& !ObjectUtils.isEmpty(askAquestionDTO.getUserid())) {
				QuestionDTO qDto = getQuestionDTOonAskAQuestion(askAquestionDTO);
				questionRepository.save(qDto);

			} else {
				throw new Exception(ErrorMsgs.DATAMISSING);
			}

		} catch (Exception e) {

			throw e;
		}

		return true;
	}

	public QuestionDTO getQuestionDTOonAskAQuestion(AskAquestionDTO askAquestionDTO) throws Exception {
		try {
			Long userId = askAquestionDTO.getUserid();
			String question = askAquestionDTO.getText();
			Date doq = dateUtility.getCurrentDateAndTime();
			Date updatedAt = doq;
			String ctype = AppConstants.QUESTIONSTR;
			int active = 1;
			String tags = AppConstants.INTERESTSEPERATOR + utilityService.joinListOfIntWithSeperator(
					askAquestionDTO.getTags(), AppConstants.INTERESTSEPERATOR) + AppConstants.INTERESTSEPERATOR;
			QuestionDTO questionDTONew = new QuestionDTO();
			questionDTONew.setActive(active);
			questionDTONew.setDoq(doq);
			questionDTONew.setUpdatedat(updatedAt);
			questionDTONew.setQuestion(question);
			if (ObjectUtils.isEmpty(askAquestionDTO.getCtype())) {
				questionDTONew.setCtype(ctype);
			} else {
				questionDTONew.setCtype(askAquestionDTO.getCtype());

			}
			questionDTONew.setUserid(userId);
			questionDTONew.setTags(tags);
			if (!ObjectUtils.isEmpty(askAquestionDTO.getEqid()) && askAquestionDTO.getEqid() != -1) {
				questionDTONew.setEqid(askAquestionDTO.getEqid());
			}
			return questionDTONew;
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}

	}

	public boolean updnvt(LikedislikeDTO likedislikeDTO) throws Exception {
		try {
			if (!ObjectUtils.isEmpty(likedislikeDTO) && !ObjectUtils.isEmpty(likedislikeDTO.getParentid())
					&& !ObjectUtils.isEmpty(likedislikeDTO.getCtype())
					&& !ObjectUtils.isEmpty(likedislikeDTO.getUpdwnvt())
					&& !ObjectUtils.isEmpty(likedislikeDTO.getUserid())) {
				Long parentid = likedislikeDTO.getParentid();
				int updwnvt = likedislikeDTO.getUpdwnvt();
				Long userid = likedislikeDTO.getUserid();
				String ctype = likedislikeDTO.getCtype();
				List<LikedislikeDTO> ls = likesDislikeRepository.findByParentidAndCtypeAndUserid(parentid, ctype,
						userid);
				if (ObjectUtils.isEmpty(ls)) {
					likedislikeDTO.setUpdatedon(dateUtility.getCurrentDateAndTime());
					likesDislikeRepository.save(likedislikeDTO);
				} else {
					likesDislikeRepository.updateLikeDislike(parentid, ctype, userid, updwnvt,
							dateUtility.getCurrentDateAndTime());
				}

			} else {
				throw new Exception(ErrorMsgs.DATAMISSING);
			}

		} catch (Exception e) {

			throw e;
		}

		return true;
	}

	public Long getTotalQuestions() throws Exception {
		try {
			return questionRepository.count();

		} catch (

		Exception e) {

			throw e;
		}
	}

	public QuestionViewDTO getQuestionOnQuestionID(QuestionDTO questionDTO) {
		try {

			QuestionViewDTO lsQs = jdbcQueryService.findByEqidAndCtype(questionDTO.getEqid(),
					questionDTO.getCtype(),questionDTO.getUserid());
			if (!ObjectUtils.isEmpty(lsQs) ) {
				return lsQs;
			}

		} catch (

		Exception e) {

			throw e;
		}
		return null;
	}

}
