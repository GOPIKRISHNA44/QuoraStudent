package com.quorastudent.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.quorastudent.constants.AppConstants;
import com.quorastudent.constants.ErrorMsgs;
import com.quorastudent.dto.AnswerDTO;
import com.quorastudent.repositories.AnswerRepository;

@Service
public class AnswerService {

	@Autowired
	private AnswerRepository answerRepository;

	@Autowired
	private DateUtility dateUtility;

	public boolean saveAnswer(AnswerDTO answerDTO) throws Exception {
		try {

			if (!ObjectUtils.isEmpty(answerDTO)) {
				Date currentDate = dateUtility.getCurrentDateAndTime();
				answerDTO.setDoa(currentDate);
				answerDTO.setUpdatedat(currentDate);
				answerDTO.setActive(AppConstants.DEFAULT_ACTIVE_STATUS);
				answerRepository.save(answerDTO);

			} else {
				throw new Exception(ErrorMsgs.DATAMISSING);
			}

		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
		return true;

	}

	public boolean updateAnswer(AnswerDTO answerDTO) throws Exception {
		try {

			if (!ObjectUtils.isEmpty(answerDTO)) {

				answerRepository.updateAnswer(answerDTO.getAid(), answerDTO.getContent());

			} else {
				throw new Exception(ErrorMsgs.DATAMISSING);
			}

		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
		return true;

	}
	
	public boolean deleteAnswer(AnswerDTO answerDTO) throws Exception {
		try {

			if (!ObjectUtils.isEmpty(answerDTO)) {

				answerRepository.deleteAnswer(answerDTO.getAid());

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
