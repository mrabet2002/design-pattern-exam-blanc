package ma.enset.entities;

import ma.enset.adapter.HDMI;

import java.util.HashMap;

public class Container {
    private HashMap<String, Agent> agents;
    private static Container instance;

    private HDMI hdmi;

    public static Container getInstance() {
        if (instance == null) {
            instance = new Container();
        }
        return instance;
    }

    public Container() {
        agents = new HashMap<>();
    }

    public void addAgent(Agent agent) {
        if (agentExists(agent.getName())) {
            throw new IllegalArgumentException("Agent with name " + agent.getName() + " already exists");
        }
        agents.put(agent.getName(), agent);
    }

    public void addAgent(String name) {
        addAgent(new Agent(name));
    }

    public void addAgents(Agent... agents) {
        for (Agent agent : agents) {
            addAgent(agent);
        }
    }

    public Agent findAgent(String name) {
        return agents.get(name);
    }

    public void removeAgent(String name) {
        agents.remove(name);
    }

    public boolean agentExists(String name) {
        return agents.containsKey(name);
    }

    public void showAgents() {
        if (hdmi == null) {
            throw new IllegalStateException("HDMI not connected");
        }
        agents.forEach((name, agent) -> {
            this.hdmi.streamData(agent);
        });
    }

    public void connect(HDMI hdmi) {
        this.hdmi = hdmi;
    }
}
