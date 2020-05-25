package dev.cuny.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import dev.cuny.entities.Client;
import dev.cuny.entities.EmailUser;
import dev.cuny.services.EmailService;

@Component
@Controller
@CrossOrigin("*")
public class EmailPasswordController {

	@Autowired
	EmailService emailserv;


	@ResponseBody
	@RequestMapping(value = "/resetpassword/{email}", method = RequestMethod.POST)
	public Client resetPasswordWithAnEmail(@PathVariable String email) {

		return		emailserv.ResetClientPassword(email);
	}

}
