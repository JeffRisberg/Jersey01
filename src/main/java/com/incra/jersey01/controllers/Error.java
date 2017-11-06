package com.incra.jersey01.controllers;

import org.eclipse.jetty.http.HttpStatus;

/**
 * Provides the most important or commonly-used 
 * @author Jeff Risberg
 * @since 10/26/17
 */
public class Error {
    protected Integer id;
    protected String title;
    protected String detail;
    protected int status;


    public Error(Integer id, String title, String detail, int status) {
        this.id = id;
        this.title = title;
        this.detail = detail;
        this.status = status;
    }

    public Error(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDetail() {
        return detail;
    }

    public int getStatus() {
        return status;
    }
}
