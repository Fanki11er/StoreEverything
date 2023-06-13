package com.example.storeeverything.Services;

import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.stereotype.Service;
import java.io.IOException;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.SendGrid;
import io.github.cdimascio.dotenv.Dotenv;



@Service
public class MailService {

    public String sendEmail(String fromUser) throws IOException {

        Dotenv dotenv = Dotenv.load();
        String SgApiKey = dotenv.get("SG_API_KEY");
        Email from = new Email("dziedzic.k@hotmail.com");
        String subject = "Udostępniono Ci nową notatke";
        Email to = new Email("dziedzic.kdz@gmail.com");
        Content content = new Content("text/plain", "Użytkownik " + fromUser + " Udostępnił Ci nową notatkę");
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(SgApiKey);
        //SendGrid sg = new SendGrid("");
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            return response.getBody();
        } catch (IOException ex) {
            throw ex;
        }
    }

}
