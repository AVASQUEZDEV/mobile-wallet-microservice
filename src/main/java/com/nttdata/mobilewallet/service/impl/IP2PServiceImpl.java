package com.nttdata.mobilewallet.service.impl;

import com.nttdata.mobilewallet.dto.mapper.P2PMapper;
import com.nttdata.mobilewallet.dto.request.P2PRequest;
import com.nttdata.mobilewallet.enums.EventType;
import com.nttdata.mobilewallet.exceptions.CustomException;
import com.nttdata.mobilewallet.model.P2P;
import com.nttdata.mobilewallet.producer.KafkaProducer;
import com.nttdata.mobilewallet.repository.IP2PRepository;
import com.nttdata.mobilewallet.service.IP2PService;
import com.nttdata.mobilewallet.util.Topic;
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
@Service
public class IP2PServiceImpl implements IP2PService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IP2PServiceImpl.class);

    private final IP2PRepository p2pRepository;
    private final P2PMapper p2PMapper;

    private final KafkaProducer<P2P> kafkaProducer;

    public IP2PServiceImpl(IP2PRepository p2pRepository, P2PMapper p2PMapper, KafkaProducer<P2P> kafkaProducer) {
        this.p2pRepository = p2pRepository;
        this.p2PMapper = p2PMapper;
        this.kafkaProducer = kafkaProducer;
    }

    /**
     * @return P2P list
     */
    @Override
    public Flux<P2P> findAll() {
        return p2pRepository.findAll().onErrorResume(e -> {
            LOGGER.error("[" + getClass().getName() + "][findAll]" + e);
            return Mono.error(CustomException.internalServerError("Internal Server Error:" + e));
        });
    }

    /**
     * @param id request
     * @return P2P
     */
    @Override
    public Mono<P2P> findById(String id) {
        return p2pRepository.findById(id)
                .onErrorResume(e -> {
                    LOGGER.error("[" + getClass().getName() + "][findById]" + e.getMessage());
                    return Mono.error(CustomException.badRequest("The request is invalid:" + e));
                }).switchIfEmpty(Mono.error(CustomException.notFound("P2P not found")));
    }

    /**
     * @param request request
     * @return created P2P
     */
    @Override
    public Mono<P2P> create(P2PRequest request) {
        return p2PMapper.toPostModel(request)
                .flatMap(p2pRepository::save)
                .onErrorResume(e -> {
                    LOGGER.error("[" + getClass().getName() + "][create]" + e);
                    return Mono.error(CustomException.badRequest("The request is invalid:" + e));
                }).switchIfEmpty(Mono.error(CustomException.notFound("P2P not created")));
    }

    /**
     * @param id      request id
     * @param request request body
     * @return updated P2P
     */
    @Override
    public Mono<P2P> update(String id, P2PRequest request) {
        return findById(id)
                .flatMap(p -> p2PMapper.toPutModel(p, request)
                        .flatMap(p2pRepository::save)
                        .onErrorResume(e -> {
                            LOGGER.error("[" + getClass().getName() + "][create]" + e);
                            return Mono.error(CustomException.badRequest("The request is invalid:" + e));
                        }).switchIfEmpty(Mono.error(CustomException.notFound("P2P not updated")))
                );
    }

    /**
     * @param id request id
     * @return void
     */
    @Override
    public Mono<Void> deleteById(String id) {
        return p2pRepository.deleteById(id).onErrorResume(e -> {
            LOGGER.error("[" + getClass().getName() + "][deleteById]" + e);
            return Mono.error(CustomException.badRequest("The request is invalid:" + e));
        });
    }

}
