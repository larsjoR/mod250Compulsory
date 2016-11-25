package no.hib.mod250.coap.server.motion;

import no.hib.mod250.coap.server.sensor.DummyMotionDetector;
import org.eclipse.californium.core.CoapServer;

/**
 * Created by royne on 23.11.2016.
 */
public class MotionServer extends CoapServer {
    public MotionServer() {
        add(new MotionDetectorResource("motion"));
    }

    public static void main(String[] args) {
        MotionServer server = new MotionServer();
        server.start();
    }
}
