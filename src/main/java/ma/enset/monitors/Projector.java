package ma.enset.monitors;

import ma.enset.adapter.HDMI;
import ma.enset.entities.Agent;
import ma.enset.entities.IAgent;

public class Projector implements HDMI {
    @Override
    public void streamData(IAgent agent) {
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++");
        agent.showAgent();
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++");
    }
}
