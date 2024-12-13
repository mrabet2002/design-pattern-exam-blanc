package ma.enset;

import ma.enset.adapter.HDMI_VGA_Adapter;
import ma.enset.entities.Agent;
import ma.enset.entities.Container;
import ma.enset.entities.Transaction;
import ma.enset.enums.TransactionType;
import ma.enset.monitors.Projector;
import ma.enset.monitors.TV;
import ma.enset.observer.Observable;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        System.out.println("Testing class Transaction");
        Transaction transaction = Transaction.builder()
                .id("T001")
                .date(LocalDateTime.now())
                .amount(1000)
                .type(TransactionType.VENTE)
                .build();

        System.out.println(transaction);

        System.out.println("Testing class Agent");

        Agent agent1 = new Agent("Agent 1");
        Agent agent2 = new Agent("Agent 2");
        Agent agent3 = new Agent("Agent 3");

        agent2.subscribe(agent1.onAddTransactionEvent());
        agent3.subscribe(agent1.onAddTransactionEvent());

        agent1.addTransaction(Transaction.builder()
                .id("T002")
                .date(LocalDateTime.now())
                .amount(2000)
                .type(TransactionType.ACHAT)
                .build());

        agent1.addTransaction(Transaction.builder()
                .id("T003")
                .date(LocalDateTime.now())
                .amount(3000)
                .type(TransactionType.VENTE)
                .build());

        System.out.println("Testing Container");
        Container container = Container.getInstance();

        container.addAgent(agent1);
        container.addAgent(agent2);
        container.addAgent(agent3);

        container.connect(new Projector());
        container.showAgents();
        container.connect(new HDMI_VGA_Adapter(new TV()));

        container.showAgents();

    }
}