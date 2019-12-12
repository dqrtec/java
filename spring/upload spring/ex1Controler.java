package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ex1Controler {
	private static final String template = "Hello, %s!";

	@RequestMapping("/ex1")
    public ex1 retornaMensagem(@RequestParam(value="name", defaultValue="World") String name) {
        return new ex1(5,String.format(template, name));
    }
}