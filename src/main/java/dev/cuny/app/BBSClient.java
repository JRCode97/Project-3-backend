package dev.cuny.app;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import dev.cuny.entities.Client;

@Component
@FeignClient("BBS-Service")
//@FeignClient("http://localhost:9222")
public interface BBSClient {
//
	@RequestMapping(value = "/query/clients", method = RequestMethod.GET)
	Client getClientByEmail(@RequestParam String email);
	
	@RequestMapping(value = "/clients", method= RequestMethod.PUT)
	Client updateClient(@RequestBody Client c);
	

}
