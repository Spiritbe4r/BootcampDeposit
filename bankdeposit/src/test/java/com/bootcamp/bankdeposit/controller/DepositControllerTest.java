package com.bootcamp.bankdeposit.controller;

import com.bootcamp.bankdeposit.models.entities.Deposit;
import com.bootcamp.bankdeposit.models.dto.DepositDTO;
import com.bootcamp.bankdeposit.repository.DepositRepository;
import com.bootcamp.bankdeposit.service.DepositService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;

class DepositControllerTest {
    @Mock
    private DepositService depositService;
    @Mock
    private DepositRepository depositRepository;
    @InjectMocks
    private DepositController controller;

    private DepositDTO dto;
    Flux<DepositDTO> fluxDto;
    Flux<Deposit> fluxDo;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        dto = new DepositDTO("098765432",13100.00,"USD","007","","876543222","Pepe",
                "8765432","");
        fluxDto = Flux.just(dto);
        fluxDo = Flux.just(new Deposit("098765432",13100.00,"USD","007","","876543222","Pepe","8765432",""));
    }

    @Test
    void getDeposit() {
        Mockito.when(depositService.getDeposits()).thenReturn(fluxDto);
        Assertions.assertNotNull(controller.getDeposit());
    }
}