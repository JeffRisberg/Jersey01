package com.incra.jersey01;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.incra.jersey01.services.CharityService;
import com.incra.jersey01.services.DonorService;

import java.util.Arrays;
import java.util.List;

/**
 * @author Jeff Risberg
 * @since 10/27/17
 */
public class MainModule implements Module {

    @Override
    public final void configure(Binder binder) {
        for (Class<?> resource : resources()) {
            System.out.println(resource);
            binder.bind(resource);
        }
    }

    public List<Class<?>> resources() {
        return Arrays.asList(
                //CharityController.class,
                //DonorController.class,
                CharityService.class,
                DonorService.class
        );
    }
}
