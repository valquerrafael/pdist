package br.edu.ifpb.progdist.hellospring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloSpringController {

    @GetMapping("/hello")
    public String getHelloSpring() {
        return "Hello Valquer Rafael e Silva Souza";
    }

}
