package com.quorastudent.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.quorastudent.constants.AppConstants;
import com.quorastudent.constants.ErrorMsgs;
import com.quorastudent.dto.CommentsDTO;
import com.quorastudent.repositories.CommentsRepository;

@Service
public class CommentsService {

	@Autowired
	private CommentsRepository commentsRepository;

	@Autowired
	private DateUtility dateUtility;

	public boolean addComment(CommentsDTO commentsDTO) throws Exception {
		try {

			if (!ObjectUtils.isEmpty(commentsDTO)) {
				commentsDTO.setActive(AppConstants.DEFAULT_COMMENT_STATUS);
				commentsDTO.setDoc(dateUtility.getCurrentDateAndTime());
				commentsDTO.setUpdatedat(dateUtility.getCurrentDateAndTime());
				commentsRepository.save(commentsDTO);
			} else {
				throw new Exception(ErrorMsgs.DATAMISSING);
			}

		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
		return true;

	}

	public boolean updateComment(CommentsDTO commentsDTO) throws Exception {
		try {

			if (!ObjectUtils.isEmpty(commentsDTO) && !ObjectUtils.isEmpty(commentsDTO.getCid())
					&& !ObjectUtils.isEmpty(commentsDTO.getComment())) {
				commentsRepository.updateComment(commentsDTO.getCid(), commentsDTO.getComment());
			} else {
				throw new Exception(ErrorMsgs.DATAMISSING);
			}

		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
		return true;

	}

	public boolean deleteComment(CommentsDTO commentsDTO) throws Exception {
		try {

			if (!ObjectUtils.isEmpty(commentsDTO) && !ObjectUtils.isEmpty(commentsDTO.getCid())) {
				commentsRepository.deleteComment(commentsDTO.getCid());
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
