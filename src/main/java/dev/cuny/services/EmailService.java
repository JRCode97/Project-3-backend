package dev.cuny.services;

import dev.cuny.entities.EmailUser;

public interface EmailService {


	EmailUser createEmail(EmailUser user);

	EmailUser ResetClientPassword(String email);


}
