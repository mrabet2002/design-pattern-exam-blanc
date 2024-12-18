package ma.enset.entities;

import ma.enset.agent.AddTransaction;
import ma.enset.aspects.annotations.Cacheable;
import ma.enset.aspects.annotations.Log;
import ma.enset.observer.Observable;
import ma.enset.observer.Subscription;
import ma.enset.agent.strategy.AgentNotificationStrategy;
import ma.enset.agent.strategy.DefaultStrategy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Agent implements IAgent {
    private String name;
    private final List<Transaction> transactions;
    private final Observable<AddTransaction> onAddTransactionEvent;
    private Subscription<AddTransaction> subscription;
    private AgentNotificationStrategy notificationStrategy;

    public Agent(String name) {
        this.name = name;
        this.transactions = new ArrayList<>();
        this.onAddTransactionEvent = new Observable<>(new AddTransaction(name));
        this.notificationStrategy = new DefaultStrategy();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    @Override
    @Cacheable
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        onAddTransactionEvent.getState().setTransaction(transaction);
        onAddTransactionEvent.notifyObservers();
    }

    @Override
    public void showAgent() {
        System.out.println("Agent: " + name);
        System.out.println("Total amount: " + getTotalAmount());
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

    @Override
    public double getTotalAmount() {
        return transactions.stream().mapToDouble(Transaction::getAmount).sum();
    }

    @Override
    @Cacheable
    @Log
    public Transaction getTransactionWithMaxAmount() {
        return transactions.stream().max(Comparator.comparingDouble(Transaction::getAmount)).orElse(null);
    }

    @Override
    public int getTransactionCount() {
        return transactions.size();
    }

    @Override
    public void showTransactions() {
        transactions.forEach(System.out::println);
    }

    @Override
    public Observable<AddTransaction> onAddTransactionEvent() {
        return onAddTransactionEvent;
    }

    @Log
    @Override
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

    public String toString() {
        return "Agent: " + name + ", Total amount: " + getTotalAmount() + ", Transaction count: " + getTransactionCount();
    }
}
