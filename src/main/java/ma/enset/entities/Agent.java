package ma.enset.entities;

import ma.enset.agent.AddTransaction;
import ma.enset.observer.Observable;
import ma.enset.observer.Subscription;
import ma.enset.agent.strategy.AgentNotificationStrategy;
import ma.enset.agent.strategy.DefaultStrategy;

import java.util.ArrayList;
import java.util.List;

public class Agent {
    private String name;
    private List<Transaction> transactions;
    private Observable<AddTransaction> onAddTransactionEvent;
    private Subscription<AddTransaction> subscription;
    private AgentNotificationStrategy notificationStrategy;

    public Agent(String name) {
        this.name = name;
        this.transactions = new ArrayList<>();
        this.onAddTransactionEvent = new Observable<>(new AddTransaction(name));
        this.notificationStrategy = new DefaultStrategy();
    }

    public String getName() {
        return name;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        onAddTransactionEvent.getState().setTransaction(transaction);
        onAddTransactionEvent.notifyObservers();
    }

    public void showAgent() {
        System.out.println("Agent: " + name);
        System.out.println("Total amount: " + getTotalAmount());
        System.out.println("Maximum amount: " + getMaximumAmount());
        System.out.println("Transaction count: " + getTransactionCount());
        System.out.println("Transactions:");
        showTransactions();
    }

    public void removeTransaction(Transaction transaction) {
        transactions.remove(transaction);
    }

    public void clearTransactions() {
        transactions.clear();
    }

    public double getTotalAmount() {
        return transactions.stream().mapToDouble(Transaction::getAmount).sum();
    }

    public double getMaximumAmount() {
        return transactions.stream().mapToDouble(Transaction::getAmount).max().orElse(0);
    }

    public int getTransactionCount() {
        return transactions.size();
    }

    public void showTransactions() {
        transactions.forEach(System.out::println);
    }

    public Observable<AddTransaction> onAddTransactionEvent() {
        return onAddTransactionEvent;
    }

    public void subscribe(Observable<AddTransaction> observable) {
        subscription = observable.subscribe(addTransaction -> {
            System.out.println("Current agent: " + this.getName());
            notificationStrategy.notifyAgent(addTransaction);
        });
    }

    public void setNotificationStrategy(AgentNotificationStrategy notificationStrategy) {
        this.notificationStrategy = notificationStrategy;
    }

    public void unsubscribe() {
        subscription.unsubscribe();
    }
}
