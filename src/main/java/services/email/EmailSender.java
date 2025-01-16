package services.email;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class EmailSender {

    public static void sendEmail(String recipientEmail, String subject, String code) throws MessagingException {
        String host = "smtp.gmail.com";
        String fromEmail = "antonsavcenko128@gmail.com"; // Ваш Gmail
        String password = "laww jbsg xacw pmcj";    // Ваш App Password (створіть у Google)

        // Налаштування SMTP
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        // Аутентифікація
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        String emailContent = getEmailTemplate(code); // Завантаження шаблону
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(fromEmail));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
        message.setSubject(subject);
        message.setContent(emailContent, "text/html; charset=UTF-8");

        // Надсилання
        Transport.send(message);
        System.out.println("Лист успішно відправлено на " + recipientEmail);
    }

    private static String getEmailTemplate(String code) {
            return "<!DOCTYPE html>" +
                    "<html lang=\"uk\">" +
                    "<head>" +
                    "    <meta charset=\"UTF-8\">" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
                    "    <title>Код підтвердження</title>" +
                    "    <style>" +
                    "        body {" +
                    "            font-family: Arial, sans-serif;" +
                    "            background-color: #f4f4f4;" +
                    "            margin: 0;" +
                    "            padding: 0;" +
                    "        }" +
                    "        .email-container {" +
                    "            max-width: 600px;" +
                    "            margin: 30px auto;" +
                    "            background-color: #ffffff;" +
                    "            border-radius: 8px;" +
                    "            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);" +
                    "            overflow: hidden;" +
                    "        }" +
                    "        .email-header {" +
                    "            background-color: #4caf50;" +
                    "            color: white;" +
                    "            text-align: center;" +
                    "            padding: 20px;" +
                    "        }" +
                    "        .email-header h1 {" +
                    "            margin: 0;" +
                    "            font-size: 24px;" +
                    "        }" +
                    "        .email-body {" +
                    "            padding: 20px;" +
                    "            color: #333333;" +
                    "        }" +
                    "        .email-body p {" +
                    "            margin: 10px 0;" +
                    "            font-size: 16px;" +
                    "        }" +
                    "        .code-box {" +
                    "            text-align: center;" +
                    "            background-color: #f9f9f9;" +
                    "            border: 1px dashed #4caf50;" +
                    "            padding: 15px;" +
                    "            margin: 20px 0;" +
                    "            font-size: 24px;" +
                    "            color: #4caf50;" +
                    "            font-weight: bold;" +
                    "            border-radius: 5px;" +
                    "        }" +
                    "        .email-footer {" +
                    "            background-color: #f4f4f4;" +
                    "            text-align: center;" +
                    "            padding: 10px;" +
                    "            font-size: 12px;" +
                    "            color: #777777;" +
                    "        }" +
                    "    </style>" +
                    "</head>" +
                    "<body>" +
                    "    <div class=\"email-container\">" +
                    "        <div class=\"email-header\">" +
                    "            <h1>Код підтвердження</h1>" +
                    "        </div>" +
                    "        <div class=\"email-body\">" +
                    "            <p>Доброго дня!</p>" +
                    "            <p>Ви отримали цей лист, оскільки запитали скидання пароля у нашій системі. Щоб продовжити, використайте наступний код підтвердження:</p>" +
                    "            <div class=\"code-box\">" + code + "</div>" +
                    "            <p>Якщо ви не запитували зміну пароля, проігноруйте цей лист.</p>" +
                    "            <p>Дякуємо, що користуєтесь нашою системою!</p>" +
                    "        </div>" +
                    "        <div class=\"email-footer\">" +
                    "            <p>© 2025 Ваша Компанія. Усі права захищено.</p>" +
                    "        </div>" +
                    "    </div>" +
                    "</body>" +
                    "</html>";
        }

}
