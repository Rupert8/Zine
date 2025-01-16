package services.email;

import java.util.Random;

public class EmailService {
    private static String generatedCode; // Токен підтвердження
    private static final Random random = new Random();

    // Надсилання коду підтвердження на email
    public static void sendVerificationCode(String email) {
        generatedCode = String.format("%06d", random.nextInt(999999)); // Генерація 6-значного коду
        String subject = "Код підтвердження для скидання пароля";
        String message = "Ваш код підтвердження: " + generatedCode;

        try {
            EmailSender.sendEmail(email, subject, message); // Використовуємо SMTP-сервіс для надсилання
            System.out.println("Код підтвердження надіслано на email: " + email);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Не вдалося надіслати код на email.");
        }
    }

    // Перевірка введеного коду
    public static boolean isVerifyCode(String inputCode) {
        return generatedCode != null && generatedCode.equals(inputCode);
    }
}
