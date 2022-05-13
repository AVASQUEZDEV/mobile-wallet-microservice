package com.nttdata.mobilewallet.controller;

import com.nttdata.mobilewallet.dto.mapper.YankiMapper;
import com.nttdata.mobilewallet.dto.request.YankiRequest;
import com.nttdata.mobilewallet.dto.response.YankiResponse;
import com.nttdata.mobilewallet.service.IYankiService;
import org.springframework.cache.annotation.CachePut;
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
@RequestMapping("/api/v1/yanki")
public class YankiRestController {

    private final IYankiService yankiService;
    private final YankiMapper yankiMapper;

    public YankiRestController(IYankiService yankiService, YankiMapper yankiMapper) {
        this.yankiService = yankiService;
        this.yankiMapper = yankiMapper;
    }

    @GetMapping
    public Flux<YankiResponse> getAll() {
        return yankiMapper.toFluxResponse(yankiService.findAll());
    }

    @GetMapping("/{id}")
    public Mono<YankiResponse> getById(@PathVariable(name = "id") String id) {
        return yankiMapper.toMonoResponse(yankiService.findById(id));
    }

    @PostMapping
    public Mono<YankiResponse> create(@RequestBody YankiRequest request) {
        return yankiMapper.toMonoResponse(yankiService.create(request));
    }

    @PutMapping("/{id}")
    public Mono<YankiResponse> update(@PathVariable(name = "id") String id, @RequestBody YankiRequest request) {
        return yankiMapper.toMonoResponse(yankiService.update(id, request));
    }

    @DeleteMapping
    public Mono<Void> deleteById(@PathVariable(name = "id") String id) {
        return yankiService.deleteById(id);
    }

}
