package travel.Email;

import com.google.api.services.gmail.model.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.AddressException;

import java.io.IOException;

public interface Gmailer {

    public Message SendEmail(String toEmailAddress, String messageSubject, String bodyText) throws MessagingException, IOException;

}

