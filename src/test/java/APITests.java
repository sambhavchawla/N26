import apiobjects.PutStatus;
import apiobjects.Transaction;
import apiobjects.TypeSum;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class APITests extends APIRequests {

    @Test
    public void createValidTransactionTest() {
        Transaction expectedTransaction = new Transaction();
        expectedTransaction.setAmount(1000.00);
        expectedTransaction.setTransaction_id(1L);
        expectedTransaction.setType("car");
        PutStatus actualPutResponse = putTransactionRequest(expectedTransaction, 200);
        validatePutStatus("ok", actualPutResponse);
        Transaction actualTransaction = getTransactionRequest(expectedTransaction.getTransaction_id(), 200);
        assertEquals(actualTransaction, expectedTransaction);
    }

    @Test
    public void createInValidTransactionTest() {
        Transaction expectedTransaction = new Transaction();
        expectedTransaction.setAmount(1000.00);
        expectedTransaction.setTransaction_id(-1L);
        expectedTransaction.setType("car");
        PutStatus actualPutResponse = putTransactionRequest(expectedTransaction, 400);
        validatePutStatus("invalid", actualPutResponse);
        Transaction actualTransaction = getTransactionRequest(expectedTransaction.getTransaction_id(), 200);
        assertEquals(actualTransaction, new Transaction());// Even if test reaches here,Should be like an empty transaction Object
    }

    @Test
    public void getExistingTransactionTest() {
        Transaction expectedTransaction = new Transaction();
        expectedTransaction.setAmount(1000.00);
        expectedTransaction.setTransaction_id(1L);
        expectedTransaction.setType("car");
        Transaction actualTransaction = getTransactionRequest(expectedTransaction.getTransaction_id(), 200);
        assertEquals(actualTransaction, expectedTransaction);
    }

    @Test
    public void getSameTypesTest() {
        List<Long> sameTypeList = getSameTypeRequest("car", 200);
        assertThat(sameTypeList, hasItems(1L, 2L));
    }

    @Test
    public void getSumTest() {
        TypeSum actualTypeSum = getTypeSumRequest(1L, 200);
        validateTypeSum(1000.00, actualTypeSum);
    }
}
