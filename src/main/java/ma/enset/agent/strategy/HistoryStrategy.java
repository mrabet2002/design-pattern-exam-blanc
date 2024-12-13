package ma.enset.agent.strategy;

import ma.enset.agent.AddTransaction;
import ma.enset.entities.Agent;

import java.util.ArrayList;
import java.util.List;

public class HistoryStrategy implements AgentNotificationStrategy {

    private final List<AddTransaction> addedTransactions = new ArrayList<>();

    @Override
    public void notifyAgent(AddTransaction addTransaction) {
        addedTransactions.add(addTransaction);

        showHistory();
    }

    public List<AddTransaction> getAddedTransactions() {
        return addedTransactions;
    }

    public void showHistory() {
        System.out.println("History of transactions:");
        for (AddTransaction addTransaction : addedTransactions) {
            System.out.println(addTransaction.getAgentName() + ": " + addTransaction.getTransaction());
        }
    }
}
