package com.quorastudent.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.quorastudent.constants.AppConstants;
import com.quorastudent.constants.ErrorMsgs;
import com.quorastudent.dto.AskAnEventDTO;
import com.quorastudent.dto.QuestionDTO;
import com.quorastudent.repositories.EventRepository;

@Service
public class EventService {

	@Autowired
	private EventRepository eventRepository;

	@Autowired
	private QuestionsService questionsService;

	@Autowired
	private DateUtility dateUtility;

	@Autowired
	private UtilityService utilityService;

	@Autowired
	private AnswerService answerService;

	public boolean saveEvent(AskAnEventDTO askAnEventDTO) throws Exception {
		try {
			if (!ObjectUtils.isEmpty(askAnEventDTO)) {
				askAnEventDTO.getAskAquestionDTO().setCtype(AppConstants.EVENTSTR);
				QuestionDTO qDto = questionsService.askAquestion(askAnEventDTO.getAskAquestionDTO());
				askAnEventDTO.getEventDTO().setEid(qDto.getEqid());
				eventRepository.save(askAnEventDTO.getEventDTO());
			} else {
				throw new Exception(ErrorMsgs.DATAMISSING);
			}

		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
		return true;

	}

	public boolean updateEvent(AskAnEventDTO askAnEventDTO) throws Exception {
		try {
			if (!ObjectUtils.isEmpty(askAnEventDTO)) {

				// check if the event already has an answer
				boolean isAnswerExists = answerService.isAnswerExistsForAnQuestionOrEntity(
						askAnEventDTO.getAskAquestionDTO().getEqid(), askAnEventDTO.getAskAquestionDTO().getCtype());
				if (!isAnswerExists) {
					askAnEventDTO.getAskAquestionDTO().setCtype(AppConstants.EVENTSTR);
					QuestionDTO questionDTO = questionsService.getQuestionDTOFromAskAnQuestionDTOforUpdateTransactions(
							askAnEventDTO.getAskAquestionDTO());
					questionsService.updateAnQuestionOrEvent(questionDTO);
					eventRepository.updateEvent(askAnEventDTO.getEventDTO().getEid(),
							askAnEventDTO.getEventDTO().getFrom(), askAnEventDTO.getEventDTO().getTo());
				} else {
					throw new Exception(ErrorMsgs.ANSWER_ALREADY_EXISTS);
				}
			} else {
				throw new Exception(ErrorMsgs.DATAMISSING);
			}

		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
		return true;
	}

	public boolean deleteAnEvent(Long eqid) throws Exception {
		try {
			
			String ctype = AppConstants.EVENTSTR;
			questionsService.deleteQuestionOrEvent(eqid, ctype);
			eventRepository.deleteEvent(eqid);
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
		return true;
	}

}
