package dev.vabalas.carleasing.service;

import dev.vabalas.carleasing.controller.ApplicationDto;
import dev.vabalas.carleasing.entity.Application;
import dev.vabalas.carleasing.entity.Car;
import dev.vabalas.carleasing.entity.Person;
import dev.vabalas.carleasing.error.ApplicationNotFoundException;
import dev.vabalas.carleasing.repository.ApplicationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationService.class);
    private final ApplicationRepository repository;

    @Autowired
    public ApplicationService(ApplicationRepository repository) {
        this.repository = repository;
    }

    public Application apply(ApplicationDto applicationDto) {
        LOGGER.info("Processing application " + applicationDto);
        Car car = new Car(applicationDto.getMake(),
                        applicationDto.getModel(),
                        applicationDto.getYear(),
                        Car.FuelType.decode(applicationDto.getFuelType().toLowerCase()),
                        Car.Damage.decode(applicationDto.getDamage().toLowerCase()));

        Person person = new Person(applicationDto.getFirstName(),
                applicationDto.getLastName(),
                applicationDto.getSocialSecurityNumber(),
                applicationDto.getDependents(),
                applicationDto.getMonthlyIncome());

        Application application = new Application(car, person, applicationDto.getAmount());

        return repository.save(application);
    }

    public Application getApplicationById(Integer id) {
        LOGGER.info("Getting application with id " + id);
        return repository.findById(id)
                .orElseThrow(() -> new ApplicationNotFoundException("Application not found with id " + id));
    }
}
