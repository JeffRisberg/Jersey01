package com.incra.jersey01;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.incra.jersey01.controllers.CharityController;
import com.incra.jersey01.controllers.DonorController;
import com.incra.jersey01.services.CharityService;
import com.incra.jersey01.services.DonorService;

import java.util.Arrays;
import java.util.List;

/**
 * @author Jeff Risberg
 * @since 10/27/17
 */
public class MainModule extends AbstractModule {

    @Override
    protected final void configure() {
        for (Class<?> resource : resources()) {
            bind(resource);
        }
    }

    public List<Class<?>> resources() {
        return Arrays.asList(
                CharityController.class,
                DonorController.class
        );
    }
}
