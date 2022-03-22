package com.bootcamp.bankdeposit.routers;

import com.bootcamp.bankdeposit.handler.DepositHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class DepositRouter {

  /**
   * Routes router function.
   *
   * @param handler the account handler
   * @return the router function
   */
  @Bean
  public RouterFunction<ServerResponse> routes(DepositHandler handler) {
    return route(GET("/api/deposit"), handler::getAll)
          .andRoute(GET("/api/deposit/{id}"), handler::findDebit);
  }
}
