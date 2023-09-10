package fr.hoenheimsports.bookdomain.spi.stub;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmailProviderStubTest {
    private EmailProviderStub emailProviderStub;

    @BeforeEach
    void setUp() {
        emailProviderStub = new EmailProviderStub();
    }

    @Test
    void sendEmail_ShouldAddEmailToList() {
        emailProviderStub.sendEmail("test@example.com", "Subject", "Body text");

        List<EmailProviderStub.Email> emails = emailProviderStub.getEmails();
        assertEquals(1, emails.size());

        EmailProviderStub.Email email = emails.get(0);
        assertEquals("test@example.com", email.to());
        assertEquals("Subject", email.subject());
        assertEquals("Body text", email.text());
    }

    @Test
    void findRecipient_ShouldReturnCorrectEmail() {
        assertEquals("sebastien.burckhardt@gmail.com", emailProviderStub.findRecipient());
    }
}