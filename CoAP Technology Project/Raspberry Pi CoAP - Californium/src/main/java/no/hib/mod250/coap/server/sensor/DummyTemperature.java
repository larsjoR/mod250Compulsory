package no.hib.mod250.coap.server.sensor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * Created by royne on 23.11.2016.
 */
public class DummyTemperature {
    private Timer timer;
    private Random random;
    private double value;
    private List<TemperatureListener> listeners;

    public DummyTemperature() {
        listeners = new ArrayList<>();
        random = new Random();

        timer = new Timer();
        timer.scheduleAtFixedRate(new UpdateDummyTemperature(), new Date(), 1000);

        value = random.nextDouble() % 20;
    }

    public double getValue() {
        return value;
    }

    public void addListener(TemperatureListener listener) {
        listeners.add(listener);
    }

    private void AlertListeners() {
        listeners.forEach( l -> l.TemperatureChanged());
    }

    private class UpdateDummyTemperature extends TimerTask {
        @Override
        public void run() {
            if (random.nextInt(2) == 0) {
                if (random.nextBoolean()) {
                    value += 0.1;

                } else {
                    value -= 0.1;
                }

                AlertListeners();
            }
        }
    }
}
