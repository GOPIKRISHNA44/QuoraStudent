package com.quorastudent.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.quorastudent.constants.ErrorMsgs;
import com.quorastudent.dto.AskAnEventDTO;
import com.quorastudent.repositories.EventRepository;

@Service
public class EventService {

	@Autowired
	private EventRepository eventRepository;

	@Autowired
	private QuestionsService questionsService;

	@Autowired
	private DateUtility dateUtility;

	public boolean saveEvent(AskAnEventDTO askAnEventDTO) throws Exception {
		try {

			if (!ObjectUtils.isEmpty(askAnEventDTO)) {
				Long getTotalQuestions = questionsService.getTotalQuestions();
				askAnEventDTO.getAskAquestionDTO().setEqid(getTotalQuestions);
				askAnEventDTO.getEventDTO().setEid(getTotalQuestions);
				questionsService.askAquestion(askAnEventDTO.getAskAquestionDTO());
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
				Long getTotalQuestions = questionsService.getTotalQuestions();
				askAnEventDTO.getAskAquestionDTO().setEqid(getTotalQuestions);
				askAnEventDTO.getEventDTO().setEid(getTotalQuestions);
				questionsService.askAquestion(askAnEventDTO.getAskAquestionDTO());
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

}
