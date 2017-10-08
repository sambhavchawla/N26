package api;

import apiobjects.PutStatus;
import apiobjects.Transaction;
import apiobjects.TypeSum;
import com.google.gson.Gson;
import com.jayway.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import static org.junit.Assert.assertTrue;


/**
 * Provides methods to parse Json into API objects Data and also verifies if JSON is valid or not
 */
public class TransactionJsonParser {

    /**
     * Parses Transaction object from Response
     *
     * @param jsonResponse get JSON Response
     * @return Transaction Object
     */
    public static Transaction getTransaction(Response jsonResponse) {
        String json = jsonResponse.asString();
        validateJson(json);
        return jsonStringToJsonObject(json, Transaction.class);
    }

    /**
     * Parses Put Response Object from Response
     *
     * @param jsonResponse
     * @return
     */
    public static PutStatus getPutResponse(Response jsonResponse) {
        String json = jsonResponse.asString();
        validateJson(json);
        return jsonStringToJsonObject(json, PutStatus.class);
    }

    /**
     * Validates if JSON passed
     *
     * @param json
     */
    private static void validateJson(String json) {
        assertTrue("Invalid JSON", isJSONValid(json));
    }

    /**
     * Parses Response to a list of Long to display pkeys
     *
     * @param jsonResponse
     * @return
     */
    public static List<Long> getAllSameTypes(Response jsonResponse) {
        String json = jsonResponse.asString();
        validateJson(json);
        List<Long> pkeys = (List<Long>) jsonStringToList(json);
        return pkeys;
    }

    public static TypeSum getSumType(Response jsonResponse) {
        String json = jsonResponse.asString();
        validateJson(json);
        return jsonStringToJsonObject(json, TypeSum.class);
    }

    /**
     * Converts JSON response to List of smaller objects
     *
     * @param jsonString
     * @return
     */
    private static List<?> jsonStringToList(String jsonString) {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, List.class);
    }

    /**
     * Converts JSON response to a single Object
     *
     * @param jsonString
     * @param expectedType expected Class to which Json Object should be mapped
     * @return
     */
    private static <T> T jsonStringToJsonObject(String jsonString, Class<T> expectedType) {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, expectedType);
    }

    /**
     * Returns true if JSON is in valid format, returns false otherwise
     *
     * @param test
     * @return
     */
    private static boolean isJSONValid(String test) {
        try {
            new JSONObject(test);
        } catch (JSONException ex) {
            try {
                new JSONArray(test);
            } catch (JSONException ex1) {
                return false;
            }
        }
        return true;
    }
}
