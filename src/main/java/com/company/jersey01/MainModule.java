package com.company.jersey01;

import com.company.jersey01.services.CharityService;
import com.company.jersey01.services.DonorService;
import com.google.inject.servlet.ServletModule;

public class MainModule extends ServletModule {

    @Override
    protected void configureServlets() {
        bind(CharityService.class);
        bind(DonorService.class);
    }
}