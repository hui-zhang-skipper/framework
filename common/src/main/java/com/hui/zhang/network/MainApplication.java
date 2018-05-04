package com.hui.zhang.network;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

public class MainApplication extends Application {

    @Override
    public Restlet createInboundRoot() {
        Router router = new Router(getContext());
        router.attach("/test", TestResource.class);
        return router;
    }

}
