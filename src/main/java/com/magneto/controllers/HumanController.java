package com.magneto.controllers;
import com.magneto.Entities.Human;
import com.magneto.services.HumanService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;

import javax.inject.Inject;
import javax.validation.Valid;

@Controller
public class HumanController {

    @Inject
    private HumanService humanService;


    @Post("/mutant/")
    public HttpResponse<Boolean> isMutant(@Valid @Body Human human) {
        if(humanService.isMutant(human)){
            return HttpResponse.ok(Boolean.TRUE);
        }else{
            return HttpResponse.badRequest(Boolean.FALSE).status(403);
        }
    }
}
