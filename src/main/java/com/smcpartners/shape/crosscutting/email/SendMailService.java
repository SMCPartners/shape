package com.smcpartners.shape.crosscutting.email;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.apache.deltaspike.core.api.config.ConfigProperty;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.logging.Logger;

/**
 * Responsible:</br>
 * 1. </br>
 * <p>
 * <p>
 * Created by johndestefano on 10/6/15.
 * </p>
 * <p>
 * <p>
 * Changes:</br>
 * 1. </br>
 * </p>
 */
@Stateless
public class SendMailService {

    @Inject
    private Logger log;

    /**
     * Mail smtp server name
     */
    @Inject
    @ConfigProperty(name = "com.smc.server-core.MAIL_SERVER_ADDRESS", defaultValue = "smtp.gmail.com")
    private String smtpSever;

    /**
     * Mail from address
     */
    @Inject
    @ConfigProperty(name = "com.smc.server-core.MAIL_FROM_ADDRESS", defaultValue = "dummy3@gmail.com")
    private String fromAddress;

    /**
     * Password to mail server from account account
     */
    @Inject
    @ConfigProperty(name = "com.smc.server-core.MAIL_FROM_PWD")
    private String fromPwd;

    /**
     * smtp server port
     */
    @Inject
    @ConfigProperty(name = "com.smc.server-core.MAIL_SMTP_PORT", defaultValue = "587")
    private String smtpPort;

    /**
     * Enable tls for sending message
     */
    @Inject
    @ConfigProperty(name = "com.smc.server-core.MAIL_TLS_ENABLED", defaultValue = "true")
    private String tlsEnabled;

    /**
     * Turn debug on
     */
    @Inject
    @ConfigProperty(name = "com.smc.server-core.MAIL_DEBUG", defaultValue = "false")
    private String debugEnabled;

    /**
     * Default constructor
     */
    public SendMailService() {
    }

    /**
     * Send a mail message
     *
     * @param mailDTO
     * @throws Exception
     */
    public void sendEmailMsg(MailDTO mailDTO) throws Exception {
        Email email = this.createEmail();
        email.setSubject(mailDTO.getSubject());
        email.setMsg(mailDTO.getMessage());
        email.addTo(mailDTO.getToEmail());
        email.send();
    }

    private Email createEmail() throws Exception {
        Email email = new SimpleEmail();
        email.setSmtpPort(Integer.parseInt(smtpPort));
        email.setAuthenticator(new DefaultAuthenticator(fromAddress, fromPwd));
        email.setDebug(Boolean.parseBoolean(debugEnabled));
        email.setHostName(smtpSever);
        email.setFrom(fromAddress);
        email.setTLS(Boolean.parseBoolean(tlsEnabled));
        email.setSocketTimeout(10000);
        email.setSocketConnectionTimeout(12000);
        return email;
    }

}
