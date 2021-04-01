package com.magneto.controllers;

import com.magneto.Entities.Stats;
import com.magneto.services.StatsService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import javax.inject.Inject;

@Controller
public class StatsController {

    @Inject
    private StatsService statsService;

    @Get("/stats/")
    public HttpResponse<Stats> stats(){
        return HttpResponse.ok(statsService.getStats());
    }
}
