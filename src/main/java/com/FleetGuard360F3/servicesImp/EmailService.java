package com.FleetGuard360F3.servicesImp;

import com.FleetGuard360F3.services.IEmailService;
import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailOptions;
import com.resend.services.emails.model.CreateEmailResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
public class EmailService implements IEmailService {

    Resend resend;
    private final String fromEmail;

    public EmailService(@Value("${resend.api.key}") String apiKey,
                        @Value("${resend.from.name}") String fromName,
                        @Value("${resend.from.email}") String fromEmailValue) {
//        this.resend = new Resend(System.getenv("RESEND_API_KEY"));
//        this.fromEmail = System.getenv("RESEND_FROM_NAME") + " <" + System.getenv("RESEND_FROM_EMAIL") + ">";
        this.resend = new Resend(apiKey);
        this.fromEmail = fromName + " <" + fromEmailValue + ">";

    }

    @Override
    public void sendSignupEmail(String recipientEmail, URI confirmationLink) {
        String SIGNUP_EMAIL_SUBJECT = "Confirmación de registro en FleetGuard360";
        String SIGNUP_EMAIL_TEMPLATE = """
                <html>
                <body>
                <p>¡Hola!</p>
                <p>Gracias por iniciar tu registro en FleetGuard360. Estás a un solo paso de activar tu cuenta.</p>
                <p>Para completar el proceso, por favor haz clic en el siguiente enlace de confirmación:</p>
                <a href="%s">Confirmar registro</a>
                <p>Si tienes problemas para hacer clic en el enlace anterior, copia y pega la URL directamente en la barra de direcciones de tu navegador.</p>
                <p>Importante: Este enlace de confirmación es único y expirará en 30 minutos por motivos de seguridad. Si no confirmas tu correo dentro de este plazo, deberás iniciar el proceso de registro nuevamente.</p>
                <p>Una vez confirmado, podrás acceder a todas las funcionalidades de FleetGuard360.</p>
                <p>Si no intentaste crear una cuenta en FleetGuard360, por favor ignora este correo.</p>
                <p>¡Esperamos verte pronto en la plataforma!</p>
                </body>
                </html>
                """;

        String signupEmailContent = String.format(SIGNUP_EMAIL_TEMPLATE, confirmationLink);

        CreateEmailOptions params = CreateEmailOptions.builder().from(fromEmail).to(recipientEmail).subject(SIGNUP_EMAIL_SUBJECT).html(signupEmailContent).build();

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
                <html>
                <body>
                <p>¡Hola!</p>
                <p>Has solicitado acceder a tu cuenta de FleetGuard360. Para continuar, por favor haz clic en el siguiente enlace:</p>
                <a href="%s">Acceder a mi cuenta</a>
                <p>Si tienes problemas para hacer clic en el enlace anterior, copia y pega la URL directamente en la barra de direcciones de tu navegador.</p>
                <p>Importante: Este enlace es único y expirará en 30 minutos por motivos de seguridad. Si no accedes dentro de este plazo, deberás solicitar un nuevo enlace de acceso.</p>
                <p>Si no solicitaste este acceso, por favor ignora este correo.</p>
                <p>¡Gracias por usar FleetGuard360!</p>
                </body>
                </html>
                """;

        String loginEmailContent = String.format(LOGIN_EMAIL_TEMPLATE, loginLink);

        CreateEmailOptions params = CreateEmailOptions.builder().from(fromEmail).to(recipientEmail).subject(LOGIN_EMAIL_SUBJECT).html(loginEmailContent).build();

        try {
            CreateEmailResponse response = resend.emails().send(params);
            System.out.println("Login email sent successfully: " + response.getId());
        } catch (ResendException e) {
            System.out.println("ResendException: " + e.getMessage());
        }
    }

    @Override
    public void sendReservationConfirmationEmail(String recipientEmail, String confirmationNumber) {
        String RESERVATION_EMAIL_SUBJECT = "Confirmación de Reserva en FleetGuard360";
        String RESERVATION_EMAIL_TEMPLATE = """
                <html>
                <body>
                <p>¡Hola!</p>
                <p>Tu reserva ha sido confirmada exitosamente. Aquí están los detalles:</p>
                <p><strong>Número de Confirmación:</strong> %s</p>
                <p>Gracias por elegir FleetGuard360. Si tienes alguna pregunta o necesitas asistencia, no dudes en contactarnos.</p>
                <p>¡Esperamos verte pronto!</p>
                </body>
                </html>
                """;

        String reservationEmailContent = String.format(RESERVATION_EMAIL_TEMPLATE, confirmationNumber);

        CreateEmailOptions params = CreateEmailOptions.builder().from(fromEmail).to(recipientEmail).subject(RESERVATION_EMAIL_SUBJECT).html(reservationEmailContent).build();

        try {
            CreateEmailResponse response = resend.emails().send(params);
            System.out.println("Reservation confirmation email sent successfully: " + response.getId());
        } catch (ResendException e) {
            System.out.println("ResendException: " + e.getMessage());
        }
    }
}
