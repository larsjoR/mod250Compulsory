package no.hib.mod250.coap.server.sensor;

import java.util.*;

/**
 * Created by royne on 23.11.2016.
 */
public class DummyMotionDetector {
    private Timer timer;
    private Timer countdown;
    private Random random;
    private List<MotionDetectorListener> listeners;
    boolean motionDetected;

    public DummyMotionDetector() {
        listeners = new ArrayList<>();
        random = new Random();
        countdown = new Timer();

        timer = new Timer();
        timer.scheduleAtFixedRate(new UpdateMotionDetected(), new Date(), 1000);
    }

    public void addListener(MotionDetectorListener listener) {
        listeners.add(listener);
    }

    private void AlertListeners() {
        listeners.forEach( l -> l.MotionDetectedChanged());
    }

    public boolean getMotionDetected() {
        return motionDetected;
    }

    private class UpdateMotionDetected extends TimerTask {
        @Override
        public void run() {
            if (random.nextInt(15) == 0) {
                motionDetected = true;
                AlertListeners();
                countdown.schedule(new SetMotionDetectedFalse(), 5000);
            }
        }
    }

    private class SetMotionDetectedFalse extends TimerTask {
        @Override
        public void run() {
            motionDetected = false;
            AlertListeners();
        }
    }
}
