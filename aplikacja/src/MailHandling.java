import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;

/**
 * The class which is responsible for sending e-mails
 */
public class MailHandling {

    /**
     * A method which is responsible for preparing a message to be sent, which uses the 'javax.mail' API methods.
     * @param session The Session class represents a mail session and is not subclassed. It collects together properties and defaults used by the mail API
     * @param emailLogin sender's address - String provided by the API
     * @param recipient recipient's address - String provided by the user
     * @param topic subject of the message to be sent - String provided by the user
     * @param text the content of the e-mail to be sent - String provided by the user
     * @return Message (if the message is completed correctly and has the correct recipient) or null (if the message is completed incorrectly and has the wrong recipient)
     */
    public static Message prepareMessage(Session session, String emailLogin, String recipient, String topic, String text){
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emailLogin));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject(topic);
            message.setText(text);
            return message;
        } catch (Exception exception){
            System.err.println("Exception caught while sending an e-mail");
        }
        return null;
    }

    /**
     * A method which is responsible for sending e-mails, sets the appropriate settings, and ensures that the message is sent correctly.
     * [uses the method  prepareMessage(Session, String emailLogin, String recipient, String topic, String text)]
     * @param recipient recipient's address - String provided by the user
     * @param topic subject of the message to be sent - String provided by the user
     * @param text the content of the e-mail to be sent - String provided by the user
     */
    public static void sendMail(String recipient, String topic, String text){
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

            Message message = prepareMessage(session, emailLogin, recipient, topic, text);
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
