package ma.enset.agent;

import ma.enset.entities.Transaction;

public class AddTransaction {
    private final String agentName;
    private Transaction transaction;

    public AddTransaction(String agentName) {
        this.agentName = agentName;
    }

    public String getAgentName() {
        return agentName;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}