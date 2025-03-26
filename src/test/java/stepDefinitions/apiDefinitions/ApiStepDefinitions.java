package stepDefinitions.apiDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ApiStepDefinitions {

    private RequestSpecification requestSpec;
    private ResponseSpecification responseSpec;
    private Response response;

    /**
     * Step to set up the base URI and authentication for API requests.
     */
    @Given("a user is authenticated and the API base URI is set")
    public void a_user_is_authenticated_and_the_API_base_URI_is_set() {
        requestSpec = new RequestSpecBuilder()
                .setBaseUri("http://localhost:9000/") // Replace with your API's base URI
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTc0MjYyNDI1MSwiYXV0aCI6IlJPTEVfQURNSU4gUk9MRV9VU0VSIiwiaWF0IjoxNzQyNTM3ODUxfQ.UGwRWU5QNCTz8Lhw4NUwqX89JBQiMNMttANoi1Y2y6szadLS1dJoFvILmcr9svbbXelUZdd9Jnk1wNWtgIHEDQ") // Replace with your auth token
                .addHeader("Content-Type", "application/json")
                .build();
    }

    /**
     * Step to send a GET request to a specific endpoint.
     *
     * @param endpoint The endpoint to send the GET request to.
     */
    @When("they send a GET request to {string}")
    public void sendGetRequest(String endpoint) {
        response = RestAssured.given()
                .spec(requestSpec)
                .when()
                .get(endpoint);
    }

    /**
     * Step to send a POST request to a specific endpoint with a payload.
     *
     * @param endpoint The endpoint to send the POST request to.
     * @param payload  The JSON payload to include in the request body.
     */
    @When("they send a POST request to {string} with payload:")
    public void sendPostRequest(String endpoint, String payload) {
        response = RestAssured.given()
                .spec(requestSpec)
                .body(payload)
                .when()
                .post(endpoint);
    }

    /**
     * Step to validate the response status code.
     *
     * @param statusCode The expected status code.
     */
    @Then("the response status code should be {int}")
    public void validateResponseCode(Integer statusCode) {
        responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(statusCode)
                .build();

        response.then()
                .spec(responseSpec);
    }

    /**
     * Step to validate that the response contains specific details.
     *
     * @param key The key to check for in the response body.
     */
    @Then("the response should contain {string}")
    public void validateResponseContainsKey(String key) {
        Assert.assertTrue(response.asString().contains(key), "Response does not contain the key: " + key);
    }

    /**
     * Step to validate the response body against a JSON schema.
     *
     * @param schemaPath The path to the JSON schema file.
     */
    @Then("the response should match the JSON schema {string}")
    public void validateResponseSchema(String schemaPath) {
        response.then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath(schemaPath));
    }
}