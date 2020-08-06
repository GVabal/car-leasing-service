package dev.vabalas.carleasing.service;

import dev.vabalas.carleasing.controller.ApplicationDto;
import dev.vabalas.carleasing.entity.Application;
import dev.vabalas.carleasing.repository.ApplicationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ApplicationServiceTest {

    private static final int APPLICATION_ID = 999;

    @Mock
    private ApplicationRepository repositoryMock;

    @InjectMocks
    private ApplicationService service;

    @Mock
    private Application applicationMock;

    private ApplicationDto applicationDto = new ApplicationDto(1, "make", "model", 1999,
            "other", "other", "firstName", "lastName",
            "socnr1234", 1, 1000);

    @Test
    void apply_savesProcessedApplicationToDb() {
        ArgumentCaptor<Application> applicationCaptor = ArgumentCaptor.forClass(Application.class);
        service.apply(applicationDto);
        Mockito.verify(repositoryMock).save(applicationCaptor.capture());
    }

    @Test
    void getApplicationById_returnsCorrectApplication() {
        Mockito.when(repositoryMock.findById(APPLICATION_ID)).thenReturn(Optional.of(applicationMock));
        Mockito.when(applicationMock.getId()).thenReturn(APPLICATION_ID);

        assertEquals(service.getApplicationById(APPLICATION_ID).getId(), APPLICATION_ID);
    }
}