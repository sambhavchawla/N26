package apiobjects;

/**
 * Transaction class to map Transaction JSON.
 */
public class Transaction {

    private Long transaction_id;
    private Double amount;
    private String type;
    private Long parent_id;

    public Long getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(Long transaction_id) {
        this.transaction_id = transaction_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transaction that = (Transaction) o;

        if (!transaction_id.equals(that.transaction_id)) return false;
        if (!amount.equals(that.amount)) return false;
        if (!type.equals(that.type)) return false;
        return parent_id != null ? parent_id.equals(that.parent_id) : that.parent_id == null;
    }

    @Override
    public int hashCode() {
        int result = transaction_id.hashCode();
        result = 31 * result + amount.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + (parent_id != null ? parent_id.hashCode() : 0);
        return result;
    }

    public Double getAmount() {

        return amount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transaction_id=" + transaction_id +
                ", amount=" + amount +
                ", type='" + type + '\'' +
                ", parent_id=" + parent_id +
                '}';
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getParent_id() {
        return parent_id;
    }

    public void setParent_id(Long parent_id) {
        this.parent_id = parent_id;
    }
}
