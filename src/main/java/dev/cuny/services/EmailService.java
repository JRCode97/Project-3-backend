package dev.cuny.services;

import dev.cuny.entities.Client;
import dev.cuny.entities.EmailUser;

public interface EmailService {



	Client ResetClientPassword(String email);


}
