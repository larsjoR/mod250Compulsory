package no.hib.mod250.coap.server.temp;

import no.hib.mod250.coap.server.sensor.DummyTemperature;
import no.hib.mod250.coap.server.sensor.TemperatureListener;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.server.resources.CoapExchange;

/**
 * Created by royne on 23.11.2016.
 */
public class TemperatureResource extends CoapResource implements TemperatureListener{
    private DummyTemperature temperature;

    public TemperatureResource(String name) {
        super(name);
        setObservable(true);

        temperature = new DummyTemperature();
        temperature.addListener(this);
    }

    @Override
    public void handleGET(CoapExchange exchange) {
        exchange.respond(String.valueOf(temperature.getValue()));
    }

    @Override
    public void TemperatureChanged() {
        changed();
    }
}
