package apiobjects;

/**
 * Class to map map Put Status after Create Transaction request
 */
public class PutStatus {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PutStatus that = (PutStatus) o;

        return status.equals(that.status);
    }

    @Override
    public int hashCode() {
        return status.hashCode();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String status;
}
