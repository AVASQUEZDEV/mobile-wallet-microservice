package com.nttdata.mobilewallet.dto.mapper;

import com.nttdata.mobilewallet.dto.request.YankiRequest;
import com.nttdata.mobilewallet.dto.response.YankiResponse;
import com.nttdata.mobilewallet.generics.ICustomMapper;
import com.nttdata.mobilewallet.model.Yanki;
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
public class YankiMapper implements ICustomMapper<YankiResponse, Yanki, YankiRequest> {

    /**
     * This method convert request to model
     *
     * @param request request of yanki
     * @return yanki model
     */
    @Override
    public Mono<Yanki> toPostModel(YankiRequest request) {
        return Mono.just(
                new Yanki(
                        request.getAssociatedTo(),
                        request.getDocumentType(),
                        request.getDocumentNumber(),
                        request.getPhone(),
                        request.getImei(),
                        request.getEmail(),
                        AppUtil.dateFormat(new Date()),
                        AppUtil.dateFormat(new Date())
                )
        );
    }

    /**
     * This method convert request to model
     *
     * @param model   entity
     * @param request yanki request
     * @return yanki model
     */
    @Override
    public Mono<Yanki> toPutModel(Yanki model, YankiRequest request) {
        model.setUpdatedAt(AppUtil.dateFormat(new Date()));
        return Mono.just(model);
    }

    /**
     * This method convert model to response
     *
     * @param model entity
     * @return converted response
     */
    @Override
    public Mono<YankiResponse> toMonoResponse(Mono<Yanki> model) {
        return model.flatMap(bac -> Mono.just(
                new YankiResponse(
                        bac.getId(),
                        bac.getAssociatedTo(),
                        bac.getDocumentType(),
                        bac.getDocumentNumber(),
                        bac.getPhone(),
                        bac.getImei(),
                        bac.getEmail(),
                        bac.getBalance(),
                        bac.getCreatedAt(),
                        bac.getCreatedAt()))
        );
    }

    /**
     * This method convert a list the yanki to response
     *
     * @param models model list
     * @return converted response
     */
    @Override
    public Flux<YankiResponse> toFluxResponse(Flux<Yanki> models) {
        return models.flatMap(bac -> toMonoResponse(Mono.just(bac)));
    }
}
