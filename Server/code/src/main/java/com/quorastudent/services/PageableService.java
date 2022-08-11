package com.quorastudent.services;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PageableService {

	public Pageable getPageableRef(int page, int size) {
		Pageable paging = PageRequest.of(page, size);
		return paging;
	}

}
