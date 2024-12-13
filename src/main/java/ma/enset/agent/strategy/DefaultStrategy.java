package ma.enset.agent.strategy;

import ma.enset.agent.AddTransaction;
import ma.enset.entities.Agent;

public class DefaultStrategy implements AgentNotificationStrategy{
    @Override
    public void notifyAgent(AddTransaction addTransaction) {
        System.out.println("New transaction added to " + addTransaction.getAgentName() + ": " + addTransaction.getTransaction());
    }
}
