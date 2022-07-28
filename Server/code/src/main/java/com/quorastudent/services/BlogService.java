package com.quorastudent.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.quorastudent.constants.AppConstants;
import com.quorastudent.constants.ErrorMsgs;
import com.quorastudent.dto.BlogDTO;
import com.quorastudent.repositories.BlogRepository;

@Service
public class BlogService {

	@Autowired
	private DateUtility dateUtility;

	@Autowired
	private BlogRepository blogRepository;

	public boolean saveBlog(BlogDTO blogDTO) throws Exception {
		try {

			if (!ObjectUtils.isEmpty(blogDTO)) {

				Date currentDateTime = dateUtility.getCurrentDateAndTime();
				blogDTO.setDoblog(currentDateTime);
				blogDTO.setUpdatedat(currentDateTime);
				blogDTO.setActive(AppConstants.DEFAULT_ACTIVE_STATUS);
				blogRepository.save(blogDTO);

			} else {
				throw new Exception(ErrorMsgs.DATAMISSING);
			}

		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
		return true;

	}

	public boolean updateBlog(BlogDTO blogDTO) throws Exception {
		try {

			if (!ObjectUtils.isEmpty(blogDTO)) {
				Date currentDateTime = dateUtility.getCurrentDateAndTime();
				blogDTO.setDoblog(currentDateTime);
				blogDTO.setUpdatedat(currentDateTime);
				blogRepository.save(blogDTO);

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
