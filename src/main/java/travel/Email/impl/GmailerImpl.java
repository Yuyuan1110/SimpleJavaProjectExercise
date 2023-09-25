package travel.Email.impl;

import com.google.api.client.googleapis.json.GoogleJsonError;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;
import jakarta.mail.MessagingException;
import travel.Email.CreateEmail;
import travel.Email.Gmailer;
import travel.util.mailUtils;
import java.io.IOException;

public class GmailerImpl implements Gmailer {
    private static final String fromEmailAddress = "z0975750027@gmail.com";
    @Override
    public Message SendEmail(String toEmailAddress, String messageSubject, String bodyText) throws MessagingException, IOException {

        NetHttpTransport netHttpTransport = new NetHttpTransport();
        Gmail service = new Gmail.Builder(netHttpTransport,
                GsonFactory.getDefaultInstance(),
                mailUtils.getCredentials(netHttpTransport))
                .setApplicationName("Gmail samples")
                .build();

        Message message = new CreateEmail().createEmail(toEmailAddress, fromEmailAddress, messageSubject, bodyText);

        try {
            // Create send message
            message = service.users().messages().send("me", message).execute();
            System.out.println("Message id: " + message.getId());
            System.out.println(message.toPrettyString());
            return message;
        } catch (GoogleJsonResponseException e) {
            // TODO(developer) - handle error appropriately
            GoogleJsonError error = e.getDetails();
            if (error.getCode() == 403) {
                System.err.println("Unable to send message: " + e.getDetails());
            } else {
                throw e;
            }
        }
        return null;
    }
}
