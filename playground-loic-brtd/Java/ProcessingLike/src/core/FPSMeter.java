package core;

public class FPSMeter {

    private long previousMillis;
    private boolean noMesurementYet;

    public FPSMeter() {
        noMesurementYet = true;
    }

    public float ellapsedMillis() {
        return System.currentTimeMillis() - previousMillis;
    }

    public float tick() {
        if (noMesurementYet) {
            previousMillis = System.currentTimeMillis();
            noMesurementYet = false;
            return 0;
        } else {
            final long currentMillis = System.currentTimeMillis();
            final float seconds = 1000f / (currentMillis - previousMillis);
            previousMillis = currentMillis;
            return seconds;
        }
    }

}
