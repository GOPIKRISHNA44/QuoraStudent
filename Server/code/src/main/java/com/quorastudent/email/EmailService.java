package com.quorastudent.email;

import java.util.List;
import java.util.Random;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.quorastudent.dto.UserDetailsDTO;
import com.quorastudent.repositories.UserRepository;
import com.quorastudent.services.DateUtility;
import com.quorastudent.services.UtilityService;

@Service
public class EmailService {

	@Value("${spring.mail.username}")
	private String sender;

	
	private String currentHosting="http://localhost:4200";

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

	@Autowired
	private PasswordResetRepository passwordResetRepository;

	@Autowired
	private UtilityService utilityService;

	@Value("${currentUiHosting}")
	private String currentUiHosting;

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

	public void sendSimpleMessageWithHTML(String to, String subject, String emailContent) throws Exception {

		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					
					
					MimeMessage mimeMessage = emailSender.createMimeMessage();
					mimeMessage.setSubject(subject,"UTF-8");
					MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true ,"utf-8");
					String htmlMsg = emailContent;
					//mimeMessage.setContent(htmlMsg, "text/html"); /** Use this or below line **/
					helper.setText(emailContent, true); // Use this or above line.
					helper.setTo(to);
					helper.setFrom("noreply@QuoraStudent.com");
//					mimeMessage.setContent(htmlMsg, "text/html");
					emailSender.send(mimeMessage);
				} catch (Exception e) {
					System.out.print("******** " + e);
				}

			}
		});
		t1.start();

	}

	public void sendActivationEmail(Long userId, String emailId) throws Exception {
		String randomString = generateRandomString();
		String activationURL = String.format("http://localhost:8080/quoraStudent/account/activateEmail?q=%s",randomString);
		String subject = "Activate your account";
		String text = activateEmailBody + activationURL;
		EmailactivatorDTO emailactivatorDTO = new EmailactivatorDTO();
		emailactivatorDTO.setCreatedat(dateUtility.getCurrentDateAndTime());
		emailactivatorDTO.setEmailid(emailId);
		emailactivatorDTO.setUserid(userId);
		emailactivatorDTO.setEmailactivatingtext(randomString);
		emailRepository.save(emailactivatorDTO);
		String htmlStr =  String.format("<html><body><a href= %s >Click here to activate your email</a>"
				+ " <br /> Thanks <br /> QuoraStudent"
				+ "</body></html>", activationURL);
		sendSimpleMessageWithHTML(emailId, subject, htmlStr);
//		sendSimpleMessage(emailId, subject, text);
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
		while (salt.length() < 200) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;
	}

	public boolean sendPasswordResetLink(String emailid) throws Exception {
		try {
			List<UserDetailsDTO> ls = userRepository.findByEmailidAndEmailidactivated(emailid,
					MailConstants.DEFAULT_ACTIVE_STATUS);
			if (!ObjectUtils.isEmpty(ls) && ls.size() > 0) {
				List<PasswordresetDTO> pls = passwordResetRepository.findByEmailid(emailid);
				String passwordkeyparam = null;
				if (!ObjectUtils.isEmpty(pls) && pls.size() > 0) {
					passwordkeyparam = pls.get(0).getPasswordresetlink();
				} else {
					PasswordresetDTO passwordresetDTO = new PasswordresetDTO();
					passwordresetDTO.setCreatedat(dateUtility.getCurrentDateAndTime());
					passwordresetDTO.setEmailid(emailid);
					passwordkeyparam = generateRandomString();
					passwordresetDTO.setPasswordresetlink(passwordkeyparam);
					passwordResetRepository.save(passwordresetDTO);
				}
				sendPasswordResetMail(emailid, passwordkeyparam);
				return true;
			} else {
				throw new Exception(MailConstants.NO_SUCH_EMAIL);
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}

	public boolean sendPasswordResetMail(String emailid, String passwordlink) throws Exception {
		try {
			String activationURL = String.format("http://localhost:4200/account/resetPassword?e=%s&q=%s",emailid,passwordlink);
			String subject = "Reset Password";
			String htmlStr =  String.format("<html><body><a href= %s >Click here to reset password</a>"
					+ " <br /> Thanks <br /> QuoraStudent"
					+ "</body></html>", activationURL);
			System.out.print(htmlStr);
			sendSimpleMessageWithHTML(emailid, subject, htmlStr);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}

	public boolean validatePasswordResetLink(String emailid, String link) throws Exception {
		try {
			List<PasswordresetDTO> pls = passwordResetRepository.findByEmailidAndPasswordresetlink(emailid, link);
			if (!ObjectUtils.isEmpty(pls) && pls.size() > 0) {
				passwordResetRepository.updatePasswordLinkActiveStatus(emailid);
				return true;
			} else {
				throw new Exception(MailConstants.BAD_KEY);
			}
		} catch (

		Exception e) {
			// TODO: handle exception
			throw e;
		}
	}

	public boolean updatePassword(String emailid, String password, String link) throws Exception {
		try {

			List<PasswordresetDTO> pls = passwordResetRepository.findByEmailidAndPasswordresetlink(emailid, link);
			if (!ObjectUtils.isEmpty(pls) && pls.size() > 0) {
				passwordResetRepository.deleteActivatedRecords(emailid, link);
				String encodedPassword = utilityService.generateEncodedPassword(password);
				userRepository.updatePassworOnEmailid(emailid, encodedPassword);
				return true;
			} else {
				throw new Exception(MailConstants.BAD_KEY);
			}

		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}

}