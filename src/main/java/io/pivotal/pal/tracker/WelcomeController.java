package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {


    private String WELCOME_VALUE;

    public WelcomeController( @Value("${WELCOME_MESSAGE}") String WELCOME_VALUE) {
        this.WELCOME_VALUE = WELCOME_VALUE;
    }

    @GetMapping("/")
    public String sayHello() {
        return WELCOME_VALUE;
    }
}
