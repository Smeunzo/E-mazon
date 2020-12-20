/**
 *
 */
package com.emazon.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Smeunzo
 */
@SpringBootApplication
@RestController
public class BackendApplication {

    /**
     *
     * @param args CLI args
     */
    public static void main(final String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

}
