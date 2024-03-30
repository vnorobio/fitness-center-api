package com.mambanegra.fitnesscenterapi.security.application.port.out;

public interface InscriptionEmailSender {
    void sendInscriptionConfirmation(String email);
}
