package ma.enset.entities;

import ma.enset.enums.TransactionType;

import java.time.LocalDateTime;

public class Transaction {
    private final String id;
    private final LocalDateTime date;
    private final double amount;
    private final TransactionType type;

    private Transaction(String id, LocalDateTime date, double amount, TransactionType type) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public TransactionType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id='" + id + '\'' +
                ", amount=" + amount +
                ", type='" + type + '\'' +
                '}';
    }

    public LocalDateTime getDate() {
        return date;
    }

    public static TransactionBuilder builder() {
        return new TransactionBuilder();
    }

    public static class TransactionBuilder {
        private String id;
        private LocalDateTime date;
        private double amount;
        private TransactionType type;

        public TransactionBuilder id(String id) {
            this.id = id;
            return this;
        }

        public TransactionBuilder date(LocalDateTime date) {
            this.date = date;
            return this;
        }

        public TransactionBuilder amount(double amount) {
            this.amount = amount;
            return this;
        }

        public TransactionBuilder type(TransactionType type) {
            this.type = type;
            return this;
        }

        public Transaction build() {
            return new Transaction(id, date, amount, type);
        }
    }
}
