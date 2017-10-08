package apiobjects;

/**
 * Class to map Sum of similar types
 * Class is used as it will also validate that sum amount is returned under "sum" tag
 */
public class TypeSum {

    private Double sum;

    @Override
    public String toString() {
        return "TypeSum{" +
                "sum=" + sum +
                '}';
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TypeSum typeSum = (TypeSum) o;

        return sum.equals(typeSum.sum);
    }

    @Override
    public int hashCode() {
        return sum.hashCode();
    }

}
