package dev.cuny.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import dev.cuny.services.EmailService;

@Component
@Controller
@CrossOrigin("*")
public class EmailController {

	@Autowired
	EmailService emailserv;

}
