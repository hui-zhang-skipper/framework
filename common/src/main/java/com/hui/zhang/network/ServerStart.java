package com.hui.zhang.network;

import org.restlet.Component;
import org.restlet.Context;
import org.restlet.Server;
import org.restlet.data.Protocol;

public class ServerStart {

    private static void startRest() throws Exception{
        Component component = new Component();
        // Add a new HTTP server listening on port 8111.
        Context context = new Context();
        context.getParameters().add("maxInactiveInterval","3");
        context.getParameters().add("maxThreads", "2");
        context.getParameters().add("maxConnectionsPerHost", "1");
        context.getParameters().add("maxTotalConnections", "1");

        Server server = new Server(context,Protocol.HTTP, 8011);
        component.getServers().add(server);
//        component.getServers().add(Protocol.HTTP);
        component.getClients().add(Protocol.HTTP);
//        component.getContext().getParameters().add("maxThreads", "200");
//        component.getClients().add(Protocol.CLAP);
//        component.getClients().add(Protocol.FILE);
        component.getDefaultHost().attach("/rest", new MainApplication());
        // Start the component.
        component.start();
    }

    public static void main(String[] args){
        try {

            ServerStart.startRest();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


}
