package garethcxy.tk.ATC.Util;

import org.springframework.stereotype.Component;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Component
public class MailUtil {
    private final String username = "atcweb513@gmail.com";
    private final String password = "atc_513666";
    private Properties prop = new Properties();
    private final String addressListStr = "cxy329288574@gmail.com";

    public MailUtil(){
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
    }

    public void SendNewOrderNotification(){
        String subject = "ATCWeb Notification";
        String txt = "有新的账单更新，请尽快查看。\nhttp://www.ethanysh.cf";
        Thread task = new Thread(() -> SendMail(username, addressListStr, subject, txt));
        task.start();
    }

    public void SendCustomNotification(String txt, String targetAddress){
        String subject = "ATCWeb Notification";
        Thread task = new Thread(() -> SendMail(username, targetAddress, subject, txt));
        task.start();
    }

    private Session AuthSesssion(Properties prop){
        return Session.getInstance(prop,
                new Authenticator(){
                    protected PasswordAuthentication getPasswordAuthentication(){
                        return new PasswordAuthentication(username, password);
                    }
                });
    }

    boolean SendMail(String sourceAddress, String addressList, String subject, String txt){
        Session session = AuthSesssion(prop);
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
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
