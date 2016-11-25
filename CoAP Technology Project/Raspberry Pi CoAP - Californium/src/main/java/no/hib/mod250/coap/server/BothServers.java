package no.hib.mod250.coap.server;

import no.hib.mod250.coap.server.motion.MotionDetectorResource;
import no.hib.mod250.coap.server.temp.TemperatureResource;
import org.eclipse.californium.core.CoapServer;

/**
 * Created by royne on 25.11.2016.
 */
public class BothServers extends CoapServer {
    public BothServers() {
        add(new MotionDetectorResource("motion"));
        add(new TemperatureResource("temperature"));
    }

    public static void main(String[] args) {
        CoapServer server = new BothServers();
        server.start();
    }
}
