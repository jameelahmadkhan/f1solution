package org.f1team.communications;


import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.sun.mail.smtp.SMTPTransport;

import java.security.Security;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;





/**
 *
 * @author jameel
 */
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class GoogleMail extends HttpServlet {
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		      throws ServletException, IOException {
				System.out.print("dopost");
		      doGet(request, response);
		   }

	public void doGet(HttpServletRequest request, HttpServletResponse response)
		      throws ServletException, IOException {

		final String username = "f1teamforsolutions@gmail.com";
		final String password = "283245283245";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("f1teamforsolutions@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse("f1teamforsolutions@gmail.com"));
			message.setSubject(request.getParameter("fullname")+" || "+request.getParameter("email"));
			message.setText(request.getParameter("message"));

			Transport.send(message);

			System.out.println("Done");
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			//out.print("<h4> Thank you for your email </h4>");
			
			RequestDispatcher rd = request.getRequestDispatcher("/popupmsg.html");
			rd.include(request, response);
			
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
