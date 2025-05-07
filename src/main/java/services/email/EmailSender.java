package services.email;

import hibernate.entity.*;
import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.activation.FileDataSource;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import start.zine.StartApplication;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Properties;

import static controller.curator.WorkGroupController.*;
import static services.exportExel.ExelExportService.exportMultiSheetExcel;

public class EmailSender extends StartApplication {



    public static void sendCodeEmail(String recipientEmail, String subject, String code) throws MessagingException {
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

    public static void sentExcelDocumentEmail(String email,String filePath,String fileName) throws MessagingException, IOException {
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

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(fromEmail));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
        message.setSubject("Експорт даних студента");

        // Текст + прикріплення
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(getEmailTemplateForExcelExport(), "text/html; charset=UTF-8");

        MimeBodyPart attachmentPart = new MimeBodyPart();
        File file = new File(filePath);
        DataSource source = new FileDataSource(file) {
            @Override
            public String getContentType() {
                return "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
            }
        };
        attachmentPart.setDataHandler(new DataHandler(source));
        attachmentPart.setFileName(fileName); // переконайтесь, що тут є ".xlsx"і

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        multipart.addBodyPart(attachmentPart);

        message.setContent(multipart);

        Transport.send(message);
        System.out.println("Лист успішно відправлено на " + email);
    }

    public static void exportStudentInfoOnDisk(String StudentName,String StudentSurname,String StudentMiddleName,List<StudentInfo> studentInfoList,
                                     List<EducationInfo> educationInfoList,
                                     List<MilitaryService> militaryList,
                                     List<StudentParents> parentsList,
                                     List<StudentJob> jobList,
                                     List<CircleActivity> circleActivityList,
                                     List<SocialActivity> socialActivityList,
                                     List<Promotion> promotionList,
                                     List<IndividualSupport> individualSupportList,
                                     List<SocialPassport> socialPassportList){

        String downloadFolder = System.getProperty("user.home") + "\\Downloads";

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = dateFormat.format(new java.util.Date());
        String filePath = downloadFolder + "\\Повна_інформація_"+ StudentSurname + "_" + StudentName + "_" + StudentMiddleName + "_" + currentDate + ".xlsx";

        try {
            exportMultiSheetExcel(studentInfoList,educationInfoList,militaryList,parentsList,jobList,socialActivityList,circleActivityList,individualSupportList,promotionList,socialPassportList,filePath);
        }catch (Exception e){
            e.printStackTrace();

        }
    }

    public static void exportAndSendExcelByEmail(
            String studentEmail,
            String studentSurname,
            String studentName,
            String studentMiddleName,
            List<StudentInfo> studentInfoList,
            List<EducationInfo> educationInfoList,
            List<MilitaryService> militaryServiceList,
            List<StudentParents> studentParentsList,
            List<StudentJob> studentJobList,
            List<SocialActivity> socialActivityList,
            List<CircleActivity> circleActivityList,
            List<IndividualSupport> individualSupport,
            List<Promotion> promotionList,
            List<SocialPassport> socialPassportList
    ) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String currentDate = dateFormat.format(new java.util.Date());

            // 1. Створення тимчасового файлу
            String fileName = "Повна_інформація_" + studentSurname + "_" + studentName + "_" + studentMiddleName + "_" + currentDate + ".xlsx";
            File tempFile = new File(System.getProperty("java.io.tmpdir"), fileName);
            String filePath = tempFile.getAbsolutePath();


            // 2. Експорт Excel-файлу
            exportMultiSheetExcel(
                    studentInfoList,
                    educationInfoList,
                    militaryServiceList,
                    studentParentsList,
                    studentJobList,
                    socialActivityList,
                    circleActivityList,
                    individualSupport,
                    promotionList,
                    socialPassportList,
                    filePath
            );

            // 3. Надсилання листа з файлом
            sentExcelDocumentEmail(studentEmail, filePath,fileName);

            // 4. Видалення тимчасового файлу після надсилання
            if (tempFile.exists()) {
                tempFile.delete();
                System.out.println("Файл видалено: " + filePath);
            }

        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        }
    }

    private static String getEmailTemplateForExcelExport() {
        return "<!DOCTYPE html>" +
                "<html lang=\"uk\">" +
                "<head>" +
                "    <meta charset=\"UTF-8\">" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
                "    <title>Експорт даних</title>" +
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
                "            background-color: #3f51b5;" +
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
                "            <h1>Експорт даних студента</h1>" +
                "        </div>" +
                "        <div class=\"email-body\">" +
                "            <p>Доброго дня,!</p>" +
                "            <p>До цього листа прикріплено Excel-документ із повною інформацією про студента.</p>" +
                "            <p>Якщо ви не запитували цей експорт, будь ласка, проігноруйте цей лист або зверніться до адміністратора.</p>" +
                "            <p>Дякуємо, що користуєтесь нашою системою!</p>" +
                "        </div>" +
                "        <div class=\"email-footer\">" +
                "            <p>© 2025 Ваша Компанія. Усі права захищено.</p>" +
                "        </div>" +
                "    </div>" +
                "</body>" +
                "</html>";
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
