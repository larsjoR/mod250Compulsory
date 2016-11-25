package no.hib.mod250.coap.server.temp;

import org.eclipse.californium.core.CoapServer;

/**
 * Created by royne on 23.11.2016.
 */
public class TempServer extends CoapServer {
    public TempServer() {
        add(new TemperatureResource("temperature"));
    }

    public static void main(String[] args) {
        TempServer server = new TempServer();
        server.start();
    }
}
