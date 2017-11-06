package com.incra.jersey01.controllers;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.incra.jersey01.MainApplication;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.servlet.ServletProperties;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * @author Jeff Risberg
 * @since 11/02/17
 */
public class DonorControllerTest {
    private Server server;
    private WebTarget target;

    @Before
    public void setUp() throws Exception {
        server = new Server(8080);

        ServletContextHandler sch = new ServletContextHandler(server, "/");
        ServletHolder jerseyServletHolder = new ServletHolder(new ServletContainer());
        jerseyServletHolder.setInitParameter(ServletProperties.JAXRS_APPLICATION_CLASS, MainApplication.class.getCanonicalName());
        sch.addServlet(jerseyServletHolder, "/*");

        server.start();

        Client c = ClientBuilder.newClient();

        c.register(JacksonJaxbJsonProvider.class);

        target = c.target("http://localhost:8080/");
    }

    @After
    public void tearDown() throws Exception {
        server.stop();
    }

    @Test
    public void testFetchValid() {
        Invocation.Builder invocationBuilder = target.path("donors/1").request().accept(MediaType.APPLICATION_JSON);

        try {
            String responseMsg = invocationBuilder.get(String.class);

            assertTrue(responseMsg.contains("John"));
            assertTrue(responseMsg.contains("Smith"));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testFetchMissing() {
        Invocation.Builder invocationBuilder = target.path("donors/999").request().accept(MediaType.APPLICATION_JSON);

        try {
            String responseMsg = invocationBuilder.get(String.class);

            fail();
        } catch (NotFoundException e) {
            // success
        }
    }

    @Test
    public void testFetchList() {
        Invocation.Builder invocationBuilder = target.path("donors").request().accept(MediaType.APPLICATION_JSON);

        String responseMsg = invocationBuilder.get(String.class);

        assertTrue(responseMsg.contains("totalCount"));
        assertTrue(responseMsg.contains("John"));
        assertTrue(responseMsg.contains("Smith"));
        assertTrue(responseMsg.contains("Tom"));
        assertTrue(responseMsg.contains("Kennedy"));
    }
}
