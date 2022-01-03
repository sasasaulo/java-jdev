package br.com.saulo.jdev.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingsController {

	@RequestMapping(value = "/mostranome/{name}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String greetingText(@PathVariable String name) {
		return "Jdev Java. \nDesenvolvedor: " + name + ".";
	}
	
	@RequestMapping(value = "/olamundo", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String olaMundo() {
		return "Olá Mundo";
	}
	
}