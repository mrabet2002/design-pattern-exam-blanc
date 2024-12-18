package ma.enset;

import ma.enset.adapter.HDMI_VGA_Adapter;
import ma.enset.config.AppConfig;
import ma.enset.entities.Agent;
import ma.enset.entities.Container;
import ma.enset.entities.IAgent;
import ma.enset.entities.Transaction;
import ma.enset.enums.TransactionType;
import ma.enset.monitors.Projector;
import ma.enset.monitors.TV;
import ma.enset.observer.Observable;
import ma.enset.security.SecurityContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.UUID;

public class Main extends AppInitializer {
    private final static SecurityContext securityContext = SecurityContext.getInstance();

    public static void main(String[] args) {
        AppInitializer.run();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your username: ");
        String username = scanner.nextLine();
        System.out.println("Enter your password: ");
        String password = scanner.nextLine();
        securityContext.authenticate(username, password);
        System.out.println("You are authenticated as " + securityContext.getUser().getUsername());

        testTransactionClass();
        testAgentClass();
        testContainerClass();
        testCaching();
    }

    public static void testTransactionClass() {
        System.out.println("*******************************************************************");
        System.out.println("****************** Testing class Transaction **********************");
        System.out.println("*******************************************************************");

        Transaction transaction = Transaction.builder()
                .id(UUID.randomUUID().toString())
                .date(LocalDateTime.now())
                .amount(1000)
                .type(TransactionType.VENTE)
                .build();

        System.out.println(transaction);

        System.out.println("*******************************************************************");
        System.out.println("*************** End of testing class Transaction ******************");
        System.out.println("*******************************************************************");
    }

    public static void testAgentClass() {
        System.out.println("*******************************************************************");
        System.out.println("******************* Testing class Agent ***************************");
        System.out.println("*******************************************************************");

        Agent agent1 = new Agent("Agent 1");
        Agent agent2 = new Agent("Agent 2");

        agent2.subscribe(agent1.onAddTransactionEvent());
        agent1.subscribe(agent2.onAddTransactionEvent());

        agent1.addTransaction(generateTransaction());
        agent2.addTransaction(generateTransaction());

        agent2.showAgent();

        System.out.println("*******************************************************************");
        System.out.println("**************** End of testing class Agent ***********************");
        System.out.println("*******************************************************************");
    }

    public static void testContainerClass() {
        System.out.println("*******************************************************************");
        System.out.println("***************** Testing class Container *************************");
        System.out.println("*******************************************************************");

        Container container = Container.getInstance();

        container.addAgent("Agent 1");
        container.addAgent("Agent 2");

        container.findAgent("Agent 1").addTransaction(generateTransaction());
        container.findAgent("Agent 2").addTransaction(generateTransaction());

        container.connect(new Projector());
        container.showAgents();

        container.connect(new HDMI_VGA_Adapter(new TV()));
        container.showAgents();

        System.out.println("*******************************************************************");
        System.out.println("************** End of testing class Container *********************");
        System.out.println("*******************************************************************");
    }

    public static void test() {
        System.out.println("Testing class Transaction");

        System.out.println(generateTransaction());

        System.out.println("Testing class Agent");
//        AppConfig.getBean(Agent.class);
        Agent agent1 = new Agent("Agent 1");
        Agent agent2 = new Agent("Agent 2");
        Agent agent3 = new Agent("Agent 3");


        agent2.subscribe(agent1.onAddTransactionEvent());
        agent3.subscribe(agent1.onAddTransactionEvent());

        agent1.addTransaction(generateTransaction());

        agent1.addTransaction(generateTransaction());

        System.out.println("Testing Container");
        Container container = Container.getInstance();

        container.addAgent(agent1);
        container.addAgent(agent2);

        container.connect(new Projector());
        container.showAgents();
        container.connect(new HDMI_VGA_Adapter(new TV()));

        container.showAgents();
    }

    public static void testCaching() {
        System.out.println("*******************************************************************");
        System.out.println("******************** Testing of caching aspect ********************");
        System.out.println("*******************************************************************");

        Agent agent = new Agent("Agent 1");
        agent.addTransaction(generateTransaction());

        System.out.println("First call to getTransactionWithMaxAmount: " + agent.getTransactionWithMaxAmount());

        for (int i = 0; i < 10; i++) {
            agent.addTransaction(generateTransaction());
        }

        System.out.println("Second call to getTransactionWithMaxAmount: " + agent.getTransactionWithMaxAmount());
        System.out.println("Third call to getTransactionWithMaxAmount: " + agent.getTransactionWithMaxAmount());

        System.out.println("*******************************************************************");
        System.out.println("**************** End of testing of caching aspect *****************");
        System.out.println("*******************************************************************");

    }

    private static Transaction generateTransaction() {
        return Transaction.builder()
                .id(UUID.randomUUID().toString())
                .date(LocalDateTime.now())
                .amount(Math.random() * 1000)
                .type(TransactionType.values()[(int) (Math.random() * TransactionType.values().length)])
                .build();
    }

}