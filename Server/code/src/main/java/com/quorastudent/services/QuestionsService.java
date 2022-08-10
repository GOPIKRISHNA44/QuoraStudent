package com.quorastudent.services;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.quorastudent.constants.AppConstants;
import com.quorastudent.constants.ErrorMsgs;
import com.quorastudent.dto.AskAquestionDTO;
import com.quorastudent.dto.FeedRequestDTO;
import com.quorastudent.dto.LikedislikeDTO;
import com.quorastudent.dto.QuestionDTO;
import com.quorastudent.dto.QuestionOrEventViewDTO;
import com.quorastudent.repositories.AnswerRepository;
import com.quorastudent.repositories.CommentsRepository;
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

	@Autowired
	private AnswerRepository answerRepository;

	@Autowired
	private CommentsRepository commentsRepository;

	@Autowired
	private PageableService pageableService;

	public QuestionDTO askAquestion(AskAquestionDTO askAquestionDTO) throws Exception {
		try {
			if (!ObjectUtils.isEmpty(askAquestionDTO) && !ObjectUtils.isEmpty(askAquestionDTO.getText())
					&& !ObjectUtils.isEmpty(askAquestionDTO.getUserid())) {
				QuestionDTO qDto = getQuestionDTOonAskAQuestion(askAquestionDTO);
				qDto = questionRepository.save(qDto);
				return qDto;
			} else {
				throw new Exception(ErrorMsgs.DATAMISSING);
			}

		} catch (Exception e) {

			throw e;
		}
	}

	public boolean deleteAQuestion(AskAquestionDTO askAquestionDTO) throws Exception {
		try {
			if (!ObjectUtils.isEmpty(askAquestionDTO)) {
				deleteQuestionOrEvent(askAquestionDTO.getEqid(), AppConstants.QUESTIONSTR);
			} else {
				throw new Exception(ErrorMsgs.DATAMISSING);
			}

		} catch (Exception e) {

			throw e;
		}
		return true;
	}

	public boolean updateQuestion(AskAquestionDTO askAquestionDTO) throws Exception {
		try {
			if (!ObjectUtils.isEmpty(askAquestionDTO)) {
				askAquestionDTO.setCtype(AppConstants.QUESTIONSTR);
				QuestionDTO qDto = getQuestionDTOFromAskAnQuestionDTOforUpdateTransactions(askAquestionDTO);
				updateAnQuestionOrEvent(qDto);
				return true;
			} else {
				throw new Exception(ErrorMsgs.DATAMISSING);
			}

		} catch (Exception e) {

			throw e;
		}
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

	public QuestionOrEventViewDTO getQuestionOnQuestionID(QuestionDTO questionDTO) {
		try {

			QuestionOrEventViewDTO lsQs = jdbcQueryService.findByEqidAndCtype(questionDTO.getEqid(),
					questionDTO.getCtype(), questionDTO.getUserid());
			if (!ObjectUtils.isEmpty(lsQs)) {
				return lsQs;
			}

		} catch (

		Exception e) {

			throw e;
		}
		return null;
	}

	public boolean updateAnQuestionOrEvent(QuestionDTO questionDTO) throws Exception {
		try {
			questionRepository.updateQuestion(questionDTO.getEqid(), questionDTO.getCtype(), questionDTO.getQuestion(),
					questionDTO.getTags());
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}

	public QuestionDTO getQuestionDTOFromAskAnQuestionDTOforUpdateTransactions(AskAquestionDTO askAquestionDTO) {
		QuestionDTO questionDTO = new QuestionDTO();
		questionDTO.setEqid(askAquestionDTO.getEqid());
		questionDTO.setQuestion(askAquestionDTO.getText());
		questionDTO.setCtype(askAquestionDTO.getCtype());
		String tags = AppConstants.INTERESTSEPERATOR
				+ utilityService.joinListOfIntWithSeperator(askAquestionDTO.getTags(), AppConstants.INTERESTSEPERATOR)
				+ AppConstants.INTERESTSEPERATOR;
		questionDTO.setTags(tags);
		return questionDTO;
	}

	public boolean deleteQuestionOrEvent(Long eqid, String ctype) throws Exception {
		try {
			questionRepository.deleteQuestionOrEvent(eqid, ctype);
			answerRepository.deleteAnswerOrEntityByEqidAndCtype(eqid, ctype); // write a cron job for this answers
																				// comments deletion
			commentsRepository.deleteComments(eqid, ctype);
			likesDislikeRepository.deleteLikings(eqid, ctype);
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
		return true;
	}

	public Page<List<Map<String, Object>>> getQuestionsFeed(FeedRequestDTO feedRequestDTO) {
		try {
			
			Pageable pageRef = pageableService.getPageableRef(feedRequestDTO.getPageNumber(),
					feedRequestDTO.getNumberOfPostsRequired());
			Page<List<Map<String, Object>>> pageTuts;
			pageTuts = questionRepository.getQuestionsFeedn(feedRequestDTO.getUserid(), feedRequestDTO.getCtype(),
					pageRef);
//			List<Map<String, Object>> o =  questionRepository.getQuestionsFeedTest(feedRequestDTO.getUserid(), feedRequestDTO.getCtype());
//			return o;
			return pageTuts;
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}
}
