package com.avee.utility;

import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {
	@Autowired
	private JavaMailSenderImpl javaMailSender;
	public static int noOfQuickServiceThreads = 20;
	private ScheduledExecutorService quickService = Executors.newScheduledThreadPool(noOfQuickServiceThreads);


	public static void main(String[] args) {
//		Map<String,Object> map = new HashMap<>();
//		map.put("from", "chirag@easyway3e.com");
//		map.put("to", "amit@easyway3e.com");
//		map.put("host", "e3sw2k301");
//		map.put("port", "25");
//		map.put("url", "");
//		map.put("subject", "Forgot Password");
	}
	public void setJavaMailSender(JavaMailSenderImpl javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	
	
	
	
	public void sendMailConfig(Map<String, Object> map)
	{
		String host = (String) map.get("host");
		String port = (String) map.get("port");
		String to = (String) map.get("to");
		String from = (String) map.get("from");
		String url = (String) map.get("url");
		String strSubject = (String) map.get("subject");
		String pwd = (String) map.get("pwd");
		Properties properties = System.getProperties();

		// Setup mail server
		properties.setProperty("mail.smtp.ssl.trust", host);
		properties.setProperty("mail.smtp.starttls.enable", "true");
		properties.setProperty("mail.smtp.auth", "true");
		javaMailSender.setHost(host);
		javaMailSender.setPort(Integer.parseInt(port));
		javaMailSender.setUsername(from);
		javaMailSender.setPassword(pwd);
		javaMailSender.setJavaMailProperties(properties);

		final MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			String[] toArr=to.split(",");
			
			String[] cc= new String[toArr.length-1];
			
			for (int i=0;i<toArr.length;i++)
			{
				
				if(i==0)
					helper.setTo(toArr[i]);
				else
				{
					cc[i-1]=toArr[i];
		
				}
				
			}
			if(cc.length!=0)
			helper.setCc(cc);
			
			
			helper.setSubject(strSubject);
			mimeMessage.setContent(url, "text/html; charset=utf-8");
			mimeMessage.setFrom(new InternetAddress(from));
			quickService.submit(new Runnable() {
				@Override
				public void run() {
					try{
						javaMailSender.send(mimeMessage);
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			});
	
			
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
}