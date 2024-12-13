package ma.enset.agent.strategy;

import ma.enset.agent.AddTransaction;
import ma.enset.entities.Agent;

public interface AgentNotificationStrategy {
    void notifyAgent(AddTransaction addTransaction);
}
