import Property.PropertyReader;
import api.TransactionJsonParser;
import apiobjects.PutStatus;
import apiobjects.Transaction;
import apiobjects.TypeSum;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import org.junit.BeforeClass;

import java.util.List;

import static com.jayway.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

/**
 * Provides Methods to call PUT & GET HTTP Requests to test Transaction API
 */
public class APIRequests {

    protected String INCORRECTSTATUSCODEMSG = "Incorrect Status Code";

    /**
     * Validates Put Status
     * @param expectedStatus
     * @param actualPutResponse
     */
    protected void validatePutStatus(String expectedStatus, PutStatus actualPutResponse) {
        PutStatus expectedPutResponse = new PutStatus();
        expectedPutResponse.setStatus("status");
        expectedPutResponse.setStatus(expectedStatus);
        assertEquals(expectedPutResponse, actualPutResponse);
    }

    /**
     * Validates Type Sum
     * @param sum
     * @param actualtypeSum
     */
    protected void validateTypeSum(Double sum, TypeSum actualtypeSum) {
        TypeSum expectedTypeSum = new TypeSum();
        expectedTypeSum.setSum(sum);
        assertEquals(expectedTypeSum, actualtypeSum);
    }

    /**Requests a PUT to create Transaction based on parameter passed
     *
     * @param transaction
     * @return
     */
    private Response putTransactionRequestReponse(Transaction transaction) {
        Response response = given().body(transaction)
                .when()
                .contentType(ContentType.JSON)
                .put("/transaction/" + transaction.getTransaction_id());
        return response;
    }

    /**Calls a PUT to create Transaction and return PUT Status Received.
     *
     * @param transaction
     * @param expectedStatusCode
     * @return
     */
    protected PutStatus putTransactionRequest(Transaction transaction, int expectedStatusCode) {
        Response transactionCreateResponse = putTransactionRequestReponse(transaction);
        assertEquals(INCORRECTSTATUSCODEMSG, expectedStatusCode, transactionCreateResponse.getStatusCode());
        return TransactionJsonParser.getPutResponse(transactionCreateResponse);
    }

    /**
     *
     * @param transactionId
     * @param expectedStatusCode
     * @return
     */
    protected Transaction getTransactionRequest(Long transactionId, int expectedStatusCode) {
        Response transactionResponse = getTransactionReponse(transactionId);
        assertEquals(INCORRECTSTATUSCODEMSG, expectedStatusCode, transactionResponse.getStatusCode());
        return TransactionJsonParser.getTransaction(transactionResponse);
    }

    /**Gets List of Long with same type transactions
     *
     * @param type
     * @param expectedStatusCode
     * @return
     */
    protected List<Long> getSameTypeRequest(String type, int expectedStatusCode) {
        Response sameTypeReponse = getSameTypeReponse(type);
        assertEquals(INCORRECTSTATUSCODEMSG, expectedStatusCode, sameTypeReponse.getStatusCode());
        return TransactionJsonParser.getAllSameTypes(sameTypeReponse);
    }

    /**Gets Sum of similar Transactions
     *
     * @param parentId
     * @param expectedStatusCode
     * @return
     */
    protected TypeSum getTypeSumRequest(Long parentId, int expectedStatusCode) {
        Response sumTypeReponse = getTypeSumReponse(parentId);
        assertEquals(INCORRECTSTATUSCODEMSG, expectedStatusCode, sumTypeReponse.getStatusCode());
        return TransactionJsonParser.getSumType(sumTypeReponse);
    }

    /**Gets Transaction response based on transaction ID passed
     *
     * @param transactionId
     * @return
     */
    private Response getTransactionReponse(Long transactionId) {
        Response response = given()
                .when()
                .contentType(ContentType.JSON)
                .get("/transaction/" + transactionId);
        return response;
    }

    /**
     * Sets Base URI , Base path and Authenticates user using properties file
     */
    @BeforeClass
    public void setBaseUriAndAuthentication() {
        baseURI = PropertyReader.getProperty("host.name");
        basePath = PropertyReader.getProperty("base.path");
        authenticateUser(PropertyReader.getProperty("username"),PropertyReader.getProperty("password"));
    }

    /**Method to authenticate user.Can be overridden to provide other credentials
     *
     * @param username
     * @param password
     */
    protected void authenticateUser(String username, String password){
        authentication = basic(username, password);
    }

    /**Gets Sum Type Response
     *
     * @param parentId
     * @return
     */
    private Response getTypeSumReponse(Long parentId) {
        Response response = given()
                .when()
                .contentType(ContentType.JSON)
                .get("/sum/" + parentId);
        return response;
    }


    /**
     * Gets response from same type of transactions
     * @param type
     * @return
     */
    private Response getSameTypeReponse(String type) {
        Response response = given()
                .when()
                .contentType(ContentType.JSON)
                .get("/types/" + type);
        return response;
    }
}
