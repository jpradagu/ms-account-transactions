package com.bootcamp.accounttransactions.util;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICRUD<T,V> {

    Mono<T> save(T t);

    Mono<Void> deleteById(V id);

    Mono<T> findById(V id);

    Flux<T> findAll();
}
