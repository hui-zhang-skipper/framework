package com.hui.zhang.network;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;

import org.restlet.resource.ServerResource;

public class TestResource extends ServerResource {

    @Get
    public Representation log(Representation entity) {
        try {

            Thread.sleep(10000l);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new JsonRepresentation("my test");
    }
}

