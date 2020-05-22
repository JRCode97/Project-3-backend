package dev.cuny.app;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.cloud.openfeign.FeignClient;

@Component
@FeignClient("Email-Service")
public interface EmailClient {

	@RequestMapping(value = "/emailservices", method = RequestMethod.POST)
	EmailClient createEmail();

	
	
}
