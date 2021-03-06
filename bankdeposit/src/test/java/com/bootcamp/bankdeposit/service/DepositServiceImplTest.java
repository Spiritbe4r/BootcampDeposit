package com.bootcamp.bankdeposit.service;

import com.bootcamp.bankdeposit.models.entities.Deposit;
import com.bootcamp.bankdeposit.models.dto.DepositDTO;
import com.bootcamp.bankdeposit.repository.DepositRepository;
import com.bootcamp.bankdeposit.service.impl.DepositServicesImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

class DepositServiceImplTest {

    @Mock
    private DepositRepository depositRepository;
    @InjectMocks
    private DepositServicesImpl service;

    private Flux<Deposit> fluxDto;

    private Mono<DepositDTO> depositDtoMono;
    private Mono<DepositDTO> depositeCreated;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        fluxDto = Flux.just(new Deposit("098765432", 13100.00, "USD", "007", "", "876543222", "Pepe", "8765432",""));

        depositDtoMono = Mono.just(new DepositDTO(13100.00, "USD", "007", "", "876543222", "Pepe", "8765432"));
    }

    @Test
    void getDeposit() {
        Mockito.when(depositRepository.findAll()).thenReturn(fluxDto);

        Assertions.assertNotNull(service.getDeposits());
    }

    @Test
    void saveDeposit() {
//        Mockito.when(depositRepository.save(ArgumentMatchers.any(depositDtoMono.getClass()))).thenReturn();

        //Assertions.assertNotNull(service.saveDeposit(depositDtoMono));
        //Mockito.verify(depositRepository, Mockito.times(1)).save(ArgumentMatchers.any(depositDtoMono.getClass())));
    }
}