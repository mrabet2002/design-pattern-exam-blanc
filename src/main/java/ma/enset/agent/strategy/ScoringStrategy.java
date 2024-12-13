package ma.enset.agent.strategy;

import ma.enset.agent.AddTransaction;
import ma.enset.entities.Agent;

public class ScoringStrategy implements AgentNotificationStrategy {

    private double score;

    @Override
    public void notifyAgent(AddTransaction addTransaction) {
        updateScore(addTransaction.getTransaction().getAmount());
    }

    private void updateScore(double amount) {
        this.score += amount;
    }
}
