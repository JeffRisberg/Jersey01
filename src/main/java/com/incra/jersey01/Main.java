package com.incra.jersey01;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.servlet.ServletProperties;


public class Main {
    public static void main(String[] args) throws Exception {

        // Define the server
        Server server = new Server(8080);

        // the handler is a wrapper of some sort
        ServletContextHandler sch = new ServletContextHandler(server, "/");
        // we add to service, which is a default one
        sch.addServlet(DefaultServlet.class, "/").setInitParameter("resourceBase", "webapps");

        // another wrapper
        ServletHolder jerseyServletHolder = new ServletHolder(new ServletContainer());
        // not sure
        jerseyServletHolder.setInitParameter(ServletProperties.JAXRS_APPLICATION_CLASS, MainApplication.class.getCanonicalName());
        // not sure
        sch.addServlet(jerseyServletHolder, "/controllers/*");

        server.start();
        server.join();
    }
}
