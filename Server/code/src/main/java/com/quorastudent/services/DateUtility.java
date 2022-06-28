package com.quorastudent.services;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class DateUtility {

	public Date getCurrentDateAndTime() throws Exception {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		Date date1 = sdf.parse(sdf.format(date));
		return date1;
	}

}
