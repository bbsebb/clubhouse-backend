package fr.hoenheimsports.bookdomain.spi.stub;

import fr.hoenheimsports.bookdomain.spi.EmailProvider;

import java.util.ArrayList;
import java.util.List;

public class EmailProviderStub implements EmailProvider {

    private final List<Email> emails;

    public EmailProviderStub() {
        this.emails = new ArrayList<>();
    }

    @Override
    public void sendEmail(String to, String subject, String text) {
        this.emails.add(new Email(to, subject, text));
    }

    @Override
    public String findRecipient() {
        return "sebastien.burckhardt@gmail.com";
    }


    public List<Email> getEmails() {
        return emails;
    }


    public record Email(String to, String subject, String text) {
    }


}
