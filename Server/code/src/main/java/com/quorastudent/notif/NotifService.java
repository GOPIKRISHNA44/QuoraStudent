package com.quorastudent.notif;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quorastudent.services.DateUtility;

@Service
public class NotifService {

	@Autowired
	private NotifRepository notifRepository;

	@Autowired
	private DateUtility dateUtility;

	public List<NotifDTO> getNotifications(Long userid) throws Exception {

		try {
			return notifRepository.findByUserid(userid);
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}

	}

	public boolean deleteSeenNotifications(Long userid, List<Integer> ids) throws Exception {

		try {
			notifRepository.deleteSeenNotifications(userid, ids);
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
		return true;

	}

	public boolean saveAnotif(Long userid, String notifHTMLText) throws Exception {

		try {
			NotifDTO notifDTO = new NotifDTO();
			notifDTO.setNotifHTMLText(notifHTMLText);
			notifDTO.setUserid(userid);
			notifDTO.setCreatedat(dateUtility.getCurrentDateAndTime());
			notifRepository.save(notifDTO);
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
		return true;

	}

	public boolean saveAnotifBulk(List<Long> userids, List<String> notifHTMLTexts) throws Exception {

		try {
			List<NotifDTO> notifLists = new ArrayList<NotifDTO>();
			for (int index = 0; index < userids.size(); index++) {
				NotifDTO notifDTO = new NotifDTO();
				notifDTO.setNotifHTMLText(notifHTMLTexts.get(index));
				notifDTO.setUserid(userids.get(index));
				notifDTO.setCreatedat(dateUtility.getCurrentDateAndTime());
				notifLists.add(notifDTO);

			}
			notifRepository.saveAll(notifLists);

		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
		return true;

	}
}
