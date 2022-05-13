package com.nttdata.mobilewallet.service.impl;

import com.nttdata.mobilewallet.dto.mapper.YankiMapper;
import com.nttdata.mobilewallet.dto.request.YankiRequest;
import com.nttdata.mobilewallet.enums.EventType;
import com.nttdata.mobilewallet.exceptions.CustomException;
import com.nttdata.mobilewallet.model.Yanki;
import com.nttdata.mobilewallet.producer.KafkaProducer;
import com.nttdata.mobilewallet.repository.IYankiRepository;
import com.nttdata.mobilewallet.service.IYankiService;
import com.nttdata.mobilewallet.util.Topic;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * This class defines to service
 *
 * @author Alcibar Vasquez
 * @version 1.0
 */
@RequiredArgsConstructor
@Service
public class YankiServiceImpl implements IYankiService {

    private static final Logger LOGGER = LoggerFactory.getLogger(YankiServiceImpl.class);

    private final IYankiRepository yankiRepository;

    private final YankiMapper yankiMapper;

    private final KafkaProducer<Yanki> kafkaProducer;

    /**
     * @return mobile wallet
     */
    @Override
    public Flux<Yanki> findAll() {
        return yankiRepository.findAll().onErrorResume(e -> {
            LOGGER.error("[" + getClass().getName() + "][findAll]" + e);
            return Mono.error(CustomException.internalServerError("Internal Server Error:" + e));
        });
    }

    /**
     * @param id request
     * @return mobile wallet
     */
    @Override
    public Mono<Yanki> findById(String id) {
        return yankiRepository.findById(id)
                .onErrorResume(e -> {
                    LOGGER.error("[" + getClass().getName() + "][findById]" + e.getMessage());
                    return Mono.error(CustomException.badRequest("The request is invalid:" + e));
                }).switchIfEmpty(Mono.error(CustomException.notFound("Yanki not found")));
    }

    /**
     * @param request request
     * @return mobile wallet
     */
    @Override
    public Mono<Yanki> create(YankiRequest request) {
        return yankiMapper.toPostModel(request)
                .flatMap(yankiRepository::save)
                .doOnNext(m -> {
                    kafkaProducer.publish(Topic.WALLET_CREATED, m, EventType.CREATED);
                    LOGGER.info("[YankiServiceImpl][save][Producer]" + m);
                })
                .onErrorResume(e -> {
                    LOGGER.error("[" + getClass().getName() + "][create]" + e);
                    return Mono.error(CustomException.badRequest("The request is invalid:" + e));
                }).switchIfEmpty(Mono.error(CustomException.notFound("Yanki not created")));
    }

    /**
     * @param id      request id
     * @param request request
     * @return mobile wallet
     */
    @Override
    public Mono<Yanki> update(String id, YankiRequest request) {
        return findById(id)
                .flatMap(y -> yankiMapper.toPutModel(y, request)
                        .flatMap(yankiRepository::save)
                        .onErrorResume(e -> {
                            LOGGER.error("[" + getClass().getName() + "][create]" + e);
                            return Mono.error(CustomException.badRequest("The request is invalid:" + e));
                        }).switchIfEmpty(Mono.error(CustomException.notFound("Yanki not updated")))
                );
    }

    /**
     * @param id request
     * @return mobile wallet
     */
    @Override
    public Mono<Void> deleteById(String id) {
        return yankiRepository.deleteById(id).onErrorResume(e -> {
            LOGGER.error("[" + getClass().getName() + "][deleteById]" + e);
            return Mono.error(CustomException.badRequest("The request is invalid:" + e));
        });
    }

}
