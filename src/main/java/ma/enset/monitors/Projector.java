package ma.enset.monitors;

import ma.enset.adapter.HDMI;
import ma.enset.entities.Agent;

public class Projector implements HDMI {
    @Override
    public void streamData(Agent agent) {
        agent.showAgent();
    }
}
