package com.FleetGuard360F3.servicesImp;

import com.FleetGuard360F3.services.IEmailService;
import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailOptions;
import com.resend.services.emails.model.CreateEmailResponse;
import org.springframework.stereotype.Service;
import java.net.URI;

@Service
public class EmailService implements IEmailService {

    Resend resend;
    private final String fromEmail;

    public EmailService() {
        resend = new Resend(System.getenv("RESEND_API_KEY"));
        fromEmail = System.getenv("RESEND_FROM_NAME") + " <" + System.getenv("RESEND_FROM_EMAIL") + ">";
    }

    @Override
    public void sendSignupEmail(String recipientEmail, URI confirmationLink) {
        String SIGNUP_EMAIL_SUBJECT = "Confirmación de registro en FleetGuard360";
        String SIGNUP_EMAIL_TEMPLATE = """
                ¡Hola!
                
                Gracias por iniciar tu registro en FleetGuard360. Estás a un solo paso de activar tu cuenta.
                
                Para completar el proceso, por favor haz clic en el siguiente enlace de confirmación:
                %s
                
                Si tienes problemas para hacer clic en el enlace anterior, copia y pega la URL directamente en la barra de direcciones de tu navegador.
                
                Importante: Este enlace de confirmación es único y expirará en 30 minutos por motivos de seguridad. Si no confirmas tu correo dentro de este plazo, deberás iniciar el proceso de registro nuevamente.
                Una vez confirmado, podrás acceder a todas las funcionalidades de FleetGuard360.
                
                Si no intentaste crear una cuenta en FleetGuard360, por favor ignora este correo.
                ¡Esperamos verte pronto en la plataforma!
                """;

        String signupEmailContent = String.format(SIGNUP_EMAIL_TEMPLATE, confirmationLink);

        CreateEmailOptions params = CreateEmailOptions.builder().from(fromEmail).to(recipientEmail).subject(SIGNUP_EMAIL_SUBJECT).text(signupEmailContent).build();

        try {
            CreateEmailResponse response = resend.emails().send(params);
            System.out.println("Email sent successfully: " + response.getId());
        } catch (ResendException e) {
            System.out.println("ResendException: " + e.getMessage());
        }
    }

    @Override
    public void sendLoginEmail(String recipientEmail, URI loginLink) {
        String LOGIN_EMAIL_SUBJECT = "Acceso a FleetGuard360";
        String LOGIN_EMAIL_TEMPLATE = """
                ¡Hola!
                
                Has solicitado acceder a tu cuenta de FleetGuard360. Para continuar, por favor haz clic en el siguiente enlace:
                %s
                
                Si tienes problemas para hacer clic en el enlace anterior, copia y pega la URL directamente en la barra de direcciones de tu navegador.
                
                Importante: Este enlace es único y expirará en 30 minutos por motivos de seguridad. Si no accedes dentro de este plazo, deberás solicitar un nuevo enlace de acceso.
                
                Si no solicitaste este acceso, por favor ignora este correo.
                ¡Gracias por usar FleetGuard360!
                """;

        String loginEmailContent = String.format(LOGIN_EMAIL_TEMPLATE, loginLink);

        CreateEmailOptions params = CreateEmailOptions.builder().from(fromEmail).to(recipientEmail).subject(LOGIN_EMAIL_SUBJECT).text(loginEmailContent).build();

        try {
            CreateEmailResponse response = resend.emails().send(params);
            System.out.println("Login email sent successfully: " + response.getId());
        } catch (ResendException e) {
            System.out.println("ResendException: " + e.getMessage());
        }
    }
}

