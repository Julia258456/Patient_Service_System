import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;

public class MailHandlingClass {

    public static Message prepareMessage(Session session, String emailLogin, String recepient, String topic, String text){
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emailLogin));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject(topic);
            message.setText(text);
            return message;
        } catch (Exception exception){
            System.err.println("Exception caught while sending an e-mail");
        }
        return null;
    }

    public static void sendMail(String recepient, String topic, String text){
        try {
            Properties properties = new Properties();
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "465");
            properties.put("mail.smtp.ssl.enable", "true");
            properties.put("mail.smtp.auth", "true");

            String emailLogin = "gabinetortodontycznyPW@gmail.com";
            String emailPassword = "Gabinet123$";

            Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(emailLogin, emailPassword);
                }
            });

            session.setDebug(true); // Try to debug set settings

            Message message = prepareMessage(session, emailLogin, recepient, topic, text);
            try {
                if (message != null) {
                    Transport.send(message);
                }
                System.out.println("Message sent successfully!");
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        } catch(Exception exception){
            System.out.println(exception.getMessage());
        }
    }



}
