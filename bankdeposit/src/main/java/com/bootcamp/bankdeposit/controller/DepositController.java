package com.bootcamp.bankdeposit.controller;

import com.bootcamp.bankdeposit.dto.AccountDto;
import com.bootcamp.bankdeposit.dto.DepositDto;
import com.bootcamp.bankdeposit.service.DepositService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

//@Slf4j
@RestController
@RequestMapping(path = "/api/deposit")
public class DepositController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DepositController.class);

    @Value("${spring.application.name}")
    private String appName;
    @Value("${microservice-accounts.uri}")
    private String urlAccounts;
    @Value("${apiclient.uri}")
    private String urlApigateway;
    @Autowired(required = false)
    private WebClient.Builder webClient;
    @Resource
    private DepositService depositService;

    @GetMapping
    public Flux<DepositDto> getDeposit(){
        LOGGER.debug("Getting Deposit!");
        LOGGER.debug("Application cloud property: " + appName);
        return depositService.getDeposit();
    }

    @GetMapping("/{id}")
    public Mono<DepositDto> getDeposit(@PathVariable String id){
        LOGGER.debug("Getting a deposit!");
        return depositService.getDepositById(id);
    }

    @PostMapping
    public Mono<DepositDto> saveDeposit(@RequestBody DepositDto depositDtoMono){
        LOGGER.debug("Saving deposit!");
       /* Mono<AccountDto> monoDto = webClient.build().get().uri(urlApigateway+urlAccounts,depositDtoMono.getToAccountId())
                .retrieve()
                .bodyToMono(AccountDto.class);
        AccountDto dto = ((AccountDto) monoDto.block());
        LOGGER.debug("dto !"+dto);*/
        return depositService.saveDeposit(depositDtoMono);
    }

    @PutMapping("/{id}")
    public Mono<DepositDto> updateDeposit(@RequestBody Mono<DepositDto> depositDtoMono, @PathVariable String id){
        LOGGER.debug("Updating deposit!");
        return depositService.updateDeposit(depositDtoMono,id);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteDeposit(@PathVariable String id){
        LOGGER.debug("Deleting deposit!");
        return depositService.deleteDeposit(id);
    }

}
