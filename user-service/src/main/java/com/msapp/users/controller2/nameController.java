package com.msapp.users.controller2;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/buddy",
consumes = MediaType.APPLICATION_JSON_VALUE,
produces = MediaType.APPLICATION_JSON_VALUE)

public class nameController
{

	@PostMapping("/")
	public void okay()
	{
		System.out.println("in");
	}
	@GetMapping("/")
	public void tes()
	{
		System.out.println("out");
	}
}
