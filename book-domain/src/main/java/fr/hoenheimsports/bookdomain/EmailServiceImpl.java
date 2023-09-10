package fr.hoenheimsports.bookdomain;

import fr.hoenheimsports.bookdomain.annotation.DomainService;
import fr.hoenheimsports.bookdomain.api.EmailService;
import fr.hoenheimsports.bookdomain.model.Booking;
import fr.hoenheimsports.bookdomain.spi.EmailProvider;

@DomainService
public class EmailServiceImpl implements EmailService {
    private final EmailProvider emailProvider;


    private final String recipient;

    public EmailServiceImpl(EmailProvider emailProvider) {
        this.emailProvider = emailProvider;
        this.recipient = this.emailProvider.findRecipient();
    }

    private String formatText(String text, Booking booking) {
        return text.formatted(booking.getHall().name(), booking.getTimeslot().start(), booking.getTimeslot().end(), booking.getState());
    }


    @Override
    public void sendEmailNotifyBookingToUser(Booking booking) {
        String subjectUser = "Réservation n° %s";
        subjectUser = String.format(subjectUser, booking.getId().toString());
        String textUser = """
                Bonjour,
                            
                Votre réservation de la salle %s du %s à %s a été %s
                            
                Cordialement,
                            
                le service de réservation de l'AS Hoenheim Sports.
                """;
        textUser = this.formatText(textUser, booking);
        this.emailProvider.sendEmail(booking.getUser().getEmail(), subjectUser, textUser);
    }

    @Override
    public void sendEmailNotifyBookingToRecipient(Booking booking) {
        String subjectRecipient = "Réservation n° %s";
        subjectRecipient = String.format(subjectRecipient, booking.getId().toString());
        String textRecipient = """
                Bonjour,
                            
                Une réservation de la salle %s du %s à %s est passé au statut %s.
                            
                Cordialement,
                            
                le service de réservation""";
        textRecipient = this.formatText(textRecipient, booking);
        this.emailProvider.sendEmail(this.recipient, subjectRecipient, textRecipient);
    }

}
