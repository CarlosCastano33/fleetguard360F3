package com.FleetGuard360F3.services;

import java.net.URI;

public interface IEmailService {
    void sendSignupEmail(String recipientEmail, URI confirmationLink);
    void sendLoginEmail(String recipientEmail, URI loginLink);
}
