package no.hib.mod250.anthrax.services.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("rest")
class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        HashSet a = new HashSet<Class<?>>();
        a.add(RestfulAnthrax.class);
        a.add(AuctionService.class);
        return a;
    }

}