package com.example.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.uri.UriBuilder;

import java.net.URI;

@Controller("/")
public class HomeController {

    @Get
    public HttpResponse<?> redirectToSwagger() {
        URI swaggerUri = UriBuilder.of("/swagger-ui.html").build();
        return HttpResponse.redirect(swaggerUri);
    }
}
