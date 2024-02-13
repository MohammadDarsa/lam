package com.actionprime.logging.app;

import org.springframework.boot.SpringApplication;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

/**
 * Main class of the dummy application. This application is to be used for testing purposes.
 */
public class DummyApplication {

    /**
     * Main method of the application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        System.out.println("hello");
        SpringApplication.run(DummyApplication.class, args);
    }

    /**
     * REST controller for the dummy API.
     */
    @RestController
    @RequestMapping("/api/v1/dummy")
    public static class DummyController {

        private final DummyService dummyService;

        /**
         * Creates a new instance of {@link DummyController}.
         *
         * @param dummyService the dummy service
         */
        public DummyController(DummyService dummyService) {
            this.dummyService = dummyService;
        }

        /**
         * Returns a greeting message.
         *
         * @return the greeting message
         */
        @GetMapping
        public String hello() {
            return dummyService.hello();
        }

        /**
         * throws a runtime exception.
         */
        @GetMapping("/exception")
        public void exception() {
            throw new RuntimeException("exception thrown");
        }
    }

    /**
     * Service for the dummy API.
     */
    @Service
    public static class DummyService {

        private final DummyRepository dummyRepository;

        /**
         * Creates a new instance of {@link DummyService}.
         *
         * @param dummyRepository the dummy repository
         */
        public DummyService(DummyRepository dummyRepository) {
            this.dummyRepository = dummyRepository;
        }

        /**
         * Returns a greeting message.
         *
         * @return the greeting message
         */
        public String hello() {
            return dummyRepository.hello();
        }
    }

    /**
     * Repository for the dummy API.
     */
    @Repository
    public static class DummyRepository {

        /**
         * Returns a greeting message.
         *
         * @return the greeting message
         */
        public String hello() {
            return "Hello World";
        }
    }

    @ControllerAdvice
    public static class DummyControllerAdvice {
        @ExceptionHandler(RuntimeException.class)
        @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
        public String handleActionNotFoundException(RuntimeException e) {
            return e.getMessage();
        }
    }
}
