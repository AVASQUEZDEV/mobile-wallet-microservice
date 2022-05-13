package com.nttdata.mobilewallet.controller;

import com.nttdata.mobilewallet.dto.mapper.P2PMapper;
import com.nttdata.mobilewallet.dto.request.P2PRequest;
import com.nttdata.mobilewallet.dto.response.P2PResponse;
import com.nttdata.mobilewallet.service.IP2PService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * This class defines to endpoints
 *
 * @author Alcibar Vasquez
 * @version 1.0
 */
@RestController
@RequestMapping("/api/v1/peer-to-peer")
public class P2PRestController {

    private final IP2PService p2pService;
    private final P2PMapper p2pMapper;

    public P2PRestController(IP2PService p2pService, P2PMapper p2pMapper) {
        this.p2pService = p2pService;
        this.p2pMapper = p2pMapper;
    }

    @GetMapping
    public Flux<P2PResponse> getAll() {
        return p2pMapper.toFluxResponse(p2pService.findAll());
    }

    @GetMapping("/{id}")
    public Mono<P2PResponse> getById(@PathVariable(name = "id") String id) {
        return p2pMapper.toMonoResponse(p2pService.findById(id));
    }

    @PostMapping
    public Mono<P2PResponse> create(@RequestBody P2PRequest request) {
        return p2pMapper.toMonoResponse(p2pService.create(request));
    }

    @PutMapping("/{id}")
    public Mono<P2PResponse> update(@PathVariable(name = "id") String id, @RequestBody P2PRequest request) {
        return p2pMapper.toMonoResponse(p2pService.update(id, request));
    }

    @DeleteMapping
    public Mono<Void> deleteById(@PathVariable(name = "id") String id) {
        return p2pService.deleteById(id);
    }

}
