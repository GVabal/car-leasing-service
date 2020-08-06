package dev.vabalas.carleasing.controller;

import dev.vabalas.carleasing.entity.Application;
import dev.vabalas.carleasing.service.ApplicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/carleasing", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApplicationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationController.class);
    private final ApplicationService service;

    @Autowired
    public ApplicationController(ApplicationService service) {
        this.service = service;
    }

    @GetMapping(path = "/application/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Application getOne(@PathVariable Integer id) {
        LOGGER.info("GET /application/" + id);
        return service.getApplicationById(id);
    }

    @PostMapping(path = "/apply")
    @ResponseStatus(HttpStatus.CREATED)
    public Application apply(@RequestBody @Valid ApplicationDto applicationDto) {
        LOGGER.info("POST /apply with body: " + applicationDto);
        return service.apply(applicationDto);
    }

}
