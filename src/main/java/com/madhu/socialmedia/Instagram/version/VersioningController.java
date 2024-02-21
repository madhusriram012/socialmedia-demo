package com.madhu.socialmedia.Instagram.version;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningController {
	@GetMapping("/v1/person")
	public PersonV1 getFirstVersion() {
		return new PersonV1("Madhu Sriram");
	}
	@GetMapping("/v2/person")
	public PersonV2 getSecondVersion() {
		return new PersonV2(new Name("Madhu","Sriram"));
	}
	
	@GetMapping(path = "/person",params = "version=1")
	public PersonV1 getFirstVersionUsingParam() {
		return new PersonV1("Madhu Sriram");
	}
	
	@GetMapping(path = "/person",params = "version=2")
	public PersonV2 getSecondVersionUsingParam() {
		return new PersonV2(new Name("Madhu","Sriram"));
	}
}
