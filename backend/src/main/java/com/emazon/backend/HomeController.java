package com.emazon.backend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Smeunzo
 */
@RestController
public class HomeController {


    /**
     * Returns a Message class to get json preview.
     *
     * @return A Message with the string "message"
     */
    @GetMapping(path = "/home")
    public Message message() {
        return new Message("message");
    }
}
