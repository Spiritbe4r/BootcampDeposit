package com.bootcamp.bankdeposit.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CrudService<T, ID> {

  Flux<T> getAll();

  Mono<T> getById(ID id);

  Mono<T> save(T obj);

  Mono<T> update(Mono<T> obj, ID id);

  Mono<Void> delete(ID id);


}