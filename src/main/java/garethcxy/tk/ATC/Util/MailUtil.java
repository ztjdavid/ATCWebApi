package garethcxy.tk.ATC.Util;

import garethcxy.tk.ATC.Entity.Summary;
import org.springframework.stereotype.Component;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Component
public class MailUtil {
    private final String username = "sender@mail";
    private final String password = "senderPassword";
    private final Properties prop = new Properties();
    private final String addressListStr = "a@mail,b@mail,c@mail";

    public MailUtil(){
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
    }

    public void SendTestMail(){
        String subject = "ATCWeb Test Mail";
        String txt = "This is a test mail from ATCWeb. Please ignore and DO NOT reply.";
        SendAsync(username, addressListStr, subject, txt);
    }

    public void SendNewOrderNotification(Summary summary){
        String subject = "ATCWeb Notification";
        String txt = "有新的账单更新，具体请查看: http://www.ethanysh.cf\n\nDetails:" + summary.getTxtString();
        SendAsync(username, addressListStr, subject, txt);
    }

    public void SendCustomNotification(String txt, String targetAddress){
        String subject = "ATCWeb Notification";
        SendAsync(username, targetAddress, subject, txt);
    }

    public void SendAsync(String sourceAddress, String addressList, String subject, String txt){
        Thread task = new Thread(() -> SendMail(sourceAddress, addressList, subject, txt));
        task.start();
    }

    private Session AuthSession(Properties prop){
        return Session.getInstance(prop,
                new Authenticator(){
                    protected PasswordAuthentication getPasswordAuthentication(){
                        return new PasswordAuthentication(username, password);
                    }
                });
    }

    private void SendMail(String sourceAddress, String addressList, String subject, String txt){
        Session session = AuthSession(prop);
        try{
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(sourceAddress));
            msg.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(addressList)
            );
            msg.setSubject(subject);
            msg.setText(txt);

            Transport.send(msg);
        }catch (Exception ignored){}
    }
}
