package com.bootcamp.bankdeposit.handler;

import com.bootcamp.bankdeposit.models.entities.Deposit;
import com.bootcamp.bankdeposit.service.DepositService;
import com.bootcamp.bankdeposit.service.TransactionDTOService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class DepositHandler {

  private static final Logger LOGGER = LoggerFactory.getLogger(DepositHandler.class);

  private final DepositService service;

  private final TransactionDTOService transactionService;

  /**
   * Find all mono.
   *
   * @param request the request
   * @return the mono
   */
  public Mono<ServerResponse> getAll(ServerRequest request) {
    return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
          .body(service.getAll(), Deposit.class).switchIfEmpty(ServerResponse.badRequest().build());
  }

  /**
   * Find debit mono.
   *
   * @param request the request
   * @return the mono
   */
  public Mono<ServerResponse> findDebit(ServerRequest request) {
    String id = request.pathVariable("id");
    return service.getById(id).flatMap((c -> ServerResponse
          .ok()
          .contentType(MediaType.APPLICATION_JSON)
          .body(BodyInserters.fromValue(c))
          .switchIfEmpty(ServerResponse.notFound().build()))
    );
  }

  /**
   * Delete debit mono.
   *
   * @param request the request
   * @return the mono
   */
  public Mono<ServerResponse> deleteDebit(ServerRequest request) {

    String id = request.pathVariable("id");

    Mono<Deposit> depositMono = service.getById(id);

    return depositMono
          .doOnNext(c -> LOGGER.info("delete Paymencard: PaymentCardId={}", c.getId()))
          .flatMap(c -> service.delete(id).then(ServerResponse.noContent().build()))
          .switchIfEmpty(ServerResponse.notFound().build());
  }
}
