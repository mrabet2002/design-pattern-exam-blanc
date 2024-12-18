package ma.enset.entities;

import ma.enset.agent.AddTransaction;
import ma.enset.aspects.annotations.Log;
import ma.enset.observer.Observable;

public interface IAgent {
    String getName();

    void setName(String name);

    void addTransaction(Transaction transaction);

    void showAgent();

    double getTotalAmount();

    Transaction getTransactionWithMaxAmount();

    int getTransactionCount();

    void showTransactions();

    Observable<AddTransaction> onAddTransactionEvent();

    @Log
    void subscribe(Observable<AddTransaction> observable);
}
