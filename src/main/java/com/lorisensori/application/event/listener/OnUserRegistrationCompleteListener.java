package com.lorisensori.application.event.listener;


import freemarker.template.TemplateException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.lorisensori.application.domain.Medewerker;
import com.lorisensori.application.event.OnUserRegistrationCompleteEvent;
import com.lorisensori.application.exceptions.MailSendException;
import com.lorisensori.application.service.EmailVerificationTokenService;
import com.lorisensori.application.service.MailService;

import javax.mail.MessagingException;
import java.io.IOException;

@Component
public class OnUserRegistrationCompleteListener implements ApplicationListener<OnUserRegistrationCompleteEvent> {

    @Autowired
    private EmailVerificationTokenService emailVerificationTokenService;

    @Autowired
    private MailService mailService;

    private static final Logger logger = Logger.getLogger(OnUserRegistrationCompleteListener.class);

    /**
     * As soon as a registration event is complete, invoke the email verification
     * asynchronously in an another thread pool
     */
    @Override
    @Async
    public void onApplicationEvent(OnUserRegistrationCompleteEvent onUserRegistrationCompleteEvent) {
        sendEmailVerification(onUserRegistrationCompleteEvent);
    }

    /**
     * Send email verification to the user and persist the token in the database.
     */
    private void sendEmailVerification(OnUserRegistrationCompleteEvent event) {
        Medewerker medewerker = event.getMedewerker();
        String token = emailVerificationTokenService.generateNewToken();
        emailVerificationTokenService.createVerificationToken(medewerker, token);

        String recipientAddress = medewerker.getEmail();
        String emailConfirmationUrl = event.getRedirectUrl().queryParam("token", token).toUriString();

        try {
            mailService.sendEmailVerification(emailConfirmationUrl, recipientAddress);
        } catch (IOException | TemplateException | MessagingException e) {
            logger.error(e);
            throw new MailSendException(recipientAddress, "Email Verification");
        }
    }
}
