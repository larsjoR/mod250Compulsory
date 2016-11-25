package no.hib.mod250.coap.server.motion;

import no.hib.mod250.coap.server.sensor.DummyMotionDetector;
import no.hib.mod250.coap.server.sensor.DummyTemperature;
import no.hib.mod250.coap.server.sensor.MotionDetectorListener;
import no.hib.mod250.coap.server.sensor.TemperatureListener;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.server.resources.CoapExchange;

/**
 * Created by royne on 23.11.2016.
 */
public class MotionDetectorResource extends CoapResource implements MotionDetectorListener{
    private DummyMotionDetector motionDetector;

    public MotionDetectorResource(String name) {
        super(name);
        setObservable(true);

        motionDetector = new DummyMotionDetector();
        motionDetector.addListener(this);
    }

    @Override
    public void handleGET(CoapExchange exchange) {
        exchange.respond(String.valueOf(motionDetector.getMotionDetected()));
    }

    @Override
    public void MotionDetectedChanged() {
        changed();
    }
}
