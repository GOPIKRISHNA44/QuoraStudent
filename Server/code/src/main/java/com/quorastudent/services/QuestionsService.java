package com.quorastudent.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.quorastudent.constants.AppConstants;
import com.quorastudent.constants.ErrorMsgs;
import com.quorastudent.dto.AskAquestionDTO;
import com.quorastudent.dto.QuestionDTO;
import com.quorastudent.repositories.QuestionRepository;

@Service
public class QuestionsService {

	@Autowired
	private UtilityService utilityService;

	@Autowired
	private DateUtility dateUtility;

	@Autowired
	private QuestionRepository questionRepository;

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
			String etype = AppConstants.QUESTIONSTR;
			int active = 1;
			String tags = AppConstants.INTERESTSEPERATOR + utilityService.joinListOfIntWithSeperator(
					askAquestionDTO.getTags(), AppConstants.INTERESTSEPERATOR) + AppConstants.INTERESTSEPERATOR;
			QuestionDTO questionDTONew = new QuestionDTO();
			questionDTONew.setActive(active);
			questionDTONew.setDoq(doq);
			questionDTONew.setUpdatedat(updatedAt);
			questionDTONew.setQuestion(question);
			questionDTONew.setEtype(etype);
			questionDTONew.setUserid(userId);
			questionDTONew.setTags(tags);
			return questionDTONew;
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}

	}

}
