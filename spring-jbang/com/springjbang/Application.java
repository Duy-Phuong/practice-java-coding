//usr/bin/env jbang "$0" "$@" ; exit $?
//JAVA 17
//DEPS org.springframework.boot:spring-boot-starter-web:3.2.0

package com.springjbang;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestHeader;

import java.time.LocalDateTime;

@SpringBootApplication
@RestController
public class Application {

  public static void main(String... args) {
    SpringApplication.run(Application.class, args);
  }

  // Test Accept header
  @GetMapping(path = "/file")
  public String fallbackForAllAccept() {
    return java.time.LocalDateTime.now().toString();
  }

  @GetMapping(path = "/file", produces = "application/zip+base64")
  public String specificForBase64() {
    return java.util.Base64.getEncoder().encodeToString(java.time.LocalDateTime.now().toString().getBytes());
  }

  // Test headers => required headers: Accept-Override=application/zip+json
  @GetMapping(path = "/file", headers = "Accept-Override=application/zip+json")
  public String onlyAcceptWithAcceptOverrideHeader() {
    return "Accept-Override: " + java.time.LocalDateTime.now().toString();
  }

  // Test headers => not required headers: Accept-Override=application/zip+json or Accept=application/zip+json
  @GetMapping(path = "/file2")
  public String acceptWithAcceptOverrideHeader(@RequestHeader(value = "Accept-Override", required = false) String acceptOverride) {
    // Compare and override response and content type base on the acceptOverride
    if ("application/zip+json".equalsIgnoreCase(acceptOverride)) {
      return acceptOverride + ": " + java.time.LocalDateTime.now().toString();
    }
    return "Accept: " + java.time.LocalDateTime.now().toString();
  }
}
