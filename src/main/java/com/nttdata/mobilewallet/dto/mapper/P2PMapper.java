package com.nttdata.mobilewallet.dto.mapper;

import com.nttdata.mobilewallet.dto.request.P2PRequest;
import com.nttdata.mobilewallet.dto.response.P2PResponse;
import com.nttdata.mobilewallet.enums.BuySaleStatus;
import com.nttdata.mobilewallet.generics.ICustomMapper;
import com.nttdata.mobilewallet.model.P2P;
import com.nttdata.mobilewallet.util.AppUtil;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

/**
 * This class convert request and response
 *
 * @author Alcibar Vasquez
 * @version 1.0
 */
@Service
public class P2PMapper implements ICustomMapper<P2PResponse, P2P, P2PRequest> {

    /**
     * @param request request
     * @return P2P converted
     */
    @Override
    public Mono<P2P> toPostModel(P2PRequest request) {
        return Mono.just(
                new P2P(request.getDocumentType(),
                        request.getDocumentNumber(),
                        request.getPhone(),
                        request.getEmail(),
                        BuySaleStatus.NONE,
                        BuySaleStatus.NONE,
                        AppUtil.dateFormat(new Date()),
                        AppUtil.dateFormat(new Date()))
        );
    }

    /**
     * @param model   entity
     * @param request request
     * @return P2P converted
     */
    @Override
    public Mono<P2P> toPutModel(P2P model, P2PRequest request) {
        model.setCreatedAt(AppUtil.dateFormat(new Date()));
        return Mono.just(model);
    }

    /**
     * @param model model
     * @return P2PResponse
     */
    @Override
    public Mono<P2PResponse> toMonoResponse(Mono<P2P> model) {
        return model.flatMap(p2p -> Mono.just(
                new P2PResponse(
                        p2p.getId(),
                        p2p.getDocumentType(),
                        p2p.getDocumentNumber(),
                        p2p.getPhone(),
                        p2p.getEmail(),
                        p2p.getBuyStatus(),
                        p2p.getSaleStatus(),
                        p2p.getCreatedAt(),
                        p2p.getUpdatedAt()
                )
        ));
    }

    /**
     * @param models models
     * @return P2PResponse list
     */
    @Override
    public Flux<P2PResponse> toFluxResponse(Flux<P2P> models) {
        return models.flatMap(p2p -> toMonoResponse(Mono.just(p2p)));
    }

}
