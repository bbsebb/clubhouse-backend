package fr.hoenheimsports.bookdomain.spi;

public interface EmailProvider {

    void sendEmail(String to, String subject, String text);

    String findRecipient();
}
