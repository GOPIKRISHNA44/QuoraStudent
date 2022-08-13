package com.quorastudent.email;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.quorastudent.repositories.UserRepository;
import com.quorastudent.services.DateUtility;
import com.quorastudent.services.UtilityService;

@Service
public class EmailService {

	@Value("${spring.mail.username}")
	private String sender;

	@Value("${currentHosting}")
	private String currentHosting;

	@Value("${activateEmailBody}")
	private String activateEmailBody;

	@Autowired
	private DateUtility dateUtility;

	@Autowired
	private EmailRepository emailRepository;

	@Autowired
	private JavaMailSender emailSender;

	@Autowired
	private UserRepository userRepository;

	public void sendSimpleMessage(String to, String subject, String text) {

		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				SimpleMailMessage message = new SimpleMailMessage();
				message.setFrom("noreply@QuoraStudent.com");
				message.setTo(to);
				message.setSubject(subject);
				message.setText(text);
				emailSender.send(message);
			}
		});
		t1.start();

	}

	public void sendActivationEmail(Long userId, String emailId) throws Exception {
		String randomString = generateRandomString();
		String activationURL = currentHosting.concat("/account/activateEmail?q=").concat(randomString);
		String subject = "Activate your account";
		String text = activateEmailBody + activationURL;
		EmailactivatorDTO emailactivatorDTO = new EmailactivatorDTO();
		emailactivatorDTO.setCreatedat(dateUtility.getCurrentDateAndTime());
		emailactivatorDTO.setEmailid(emailId);
		emailactivatorDTO.setUserid(userId);
		emailactivatorDTO.setEmailactivatingtext(randomString);
		emailRepository.save(emailactivatorDTO);
		sendSimpleMessage(emailId, subject, text);
	}

	public boolean activateEmail(String q) throws Exception {
		try {
			List<EmailactivatorDTO> ls = emailRepository.findByEmailactivatingtext(q);
			if (!ObjectUtils.isEmpty(ls) && ls.size() == 1) {
				Long userid = ls.get(0).getUserid();
				userRepository.updateEmailActivation(userid);
				emailRepository.deleteActivatedRecords(userid);
				return true;
			} else {
				throw new Exception(MailConstants.BAD_KEY);
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}

	}

	public String generateRandomString() {
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789abcdefghizkfusegiugefwieutiwet";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 50) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;
	}

}