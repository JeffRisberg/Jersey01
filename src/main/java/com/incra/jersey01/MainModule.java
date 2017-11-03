package com.incra.jersey01;

import com.google.inject.servlet.ServletModule;
import com.incra.jersey01.services.CharityService;
import com.incra.jersey01.services.DonorService;

public class MainModule extends ServletModule {

    @Override
    protected void configureServlets() {
        bind(CharityService.class);
        bind(DonorService.class);
    }
}