package com.quorastudent.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.quorastudent.constants.AppConstants;
import com.quorastudent.constants.ErrorMsgs;
import com.quorastudent.dto.CommentsDTO;
import com.quorastudent.dto.CommentsRequestingDTO;
import com.quorastudent.dto.CommentsResponseListViewDTO;
import com.quorastudent.repositories.CommentsRepository;
import com.quorastudent.repositories.JdbcQueryService;

@Service
public class CommentsService {

	@Autowired
	private CommentsRepository commentsRepository;

	@Autowired
	private DateUtility dateUtility;

	@Autowired
	private JdbcQueryService jdbcQueryService;

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

	public List<CommentsResponseListViewDTO> getCommentsList(CommentsRequestingDTO commentsRequestingDTO) throws Exception {
		try {

			if (!ObjectUtils.isEmpty(commentsRequestingDTO)) {
				List<CommentsResponseListViewDTO> lsQs = jdbcQueryService.getCommentsList(
						commentsRequestingDTO.getRequestingUserId(), commentsRequestingDTO.getCtype(),
						commentsRequestingDTO.getEqabcid());
				return lsQs;
			}

		} catch (

		Exception e) {

			throw e;
		}
		return null;
	}

}
