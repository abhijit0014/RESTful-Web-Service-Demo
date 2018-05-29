package com.avi.RESTfulWebServiceDemo.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {
	
	@GetMapping("/v1/person")
	public PersonV1 personV1() {
		PersonV1 personV1 = new PersonV1("OVI J");
		return personV1;
	}
	
	@GetMapping("/v2/person")
	public PersonV2 personV2() {
		PersonV2 personV2 = new PersonV2(new Name("ovi", "j"));
		return personV2;
	}	
}
