package ma.enset.monitors;

import ma.enset.adapter.HDMI;
import ma.enset.adapter.VGA;

public class TV implements VGA {

    @Override
    public void streamData(String data) {
        System.out.println("Streaming data from VGA: " + data);
    }
}
