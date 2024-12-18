package ma.enset.entities;

import ma.enset.adapter.HDMI;
import ma.enset.aspects.annotations.Log;
import ma.enset.aspects.annotations.SecuredBy;
import ma.enset.config.AppConfig;

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

    @SecuredBy(roles = {"ADMIN"})
    public void addAgent(Agent agent) {
        if (agentExists(agent.getName())) {
            throw new IllegalArgumentException("Agent with name " + agent.getName() + " already exists");
        }
        agents.put(agent.getName(), agent);
    }

    @SecuredBy(roles = {"ADMIN"})
    public void addAgent(String name) {
        Agent agent = new Agent(name);
        addAgent(agent);
    }

    @SecuredBy(roles = {"ADMIN"})
    public void addAgents(Agent... agents) {
        for (Agent agent : agents) {
            addAgent(agent);
        }
    }

    @SecuredBy(roles = {"USER"})
    public Agent findAgent(String name) {
        return agents.get(name);
    }

    @SecuredBy(roles = {"ADMIN"})
    public void removeAgent(String name) {
        agents.remove(name);
    }

    public boolean agentExists(String name) {
        return agents.containsKey(name);
    }

    @Log
    @SecuredBy(roles = {"USER"})
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
