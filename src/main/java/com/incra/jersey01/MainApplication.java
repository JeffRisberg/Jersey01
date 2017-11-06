package com.incra.jersey01;

import com.google.inject.Guice;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.message.GZipEncoder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.EncodingFilter;
import org.jvnet.hk2.guice.bridge.api.GuiceBridge;
import org.jvnet.hk2.guice.bridge.api.GuiceIntoHK2Bridge;

import javax.inject.Inject;

public class MainApplication extends ResourceConfig {
    @Inject
    public MainApplication(ServiceLocator serviceLocator) {

        packages(MainApplication.class.getPackage().getName());

        GuiceBridge.getGuiceBridge().initializeGuiceBridge(serviceLocator);
        GuiceIntoHK2Bridge guiceBridge = serviceLocator.getService(GuiceIntoHK2Bridge.class);
        guiceBridge.bridgeGuiceInjector(Guice.createInjector(new MainModule()));

        EncodingFilter.enableFor(this, GZipEncoder.class);
    }
}