package Reqres.ReqresAPIStepDefs;

import Reqres.ReqresAPI.ReqresAPI;
import Reqres.ReqresAPI.ReqresAPITesting;
import Reqres.ReqresResponses.ReqresAPITestingResponses;
import Reqres.ReqresResponses.ReqresResponses;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.io.File;
import io.restassured.module.jsv.JsonSchemaValidator;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import static org.hamcrest.Matchers.equalTo;


public class ReqresAPITestingStepDefs {

    @Steps
    ReqresAPITesting reqresAPITesting;

    @Given("Get single user with parameter {string}")
    public void getSingleUserWithParameter(String id) {
        reqresAPITesting.getSingleUser(id);
    }

    @When("Send request get single user")
    public void sendRequestGetSingleUser() {
        SerenityRest.when().get(ReqresAPITesting.GET_SINGLE_USER);
    }

    @Then("API response status code should be {int} OK")
    public void apiResponseStatusCodeShouldBeOK(int OK) {
        SerenityRest.then().statusCode(200);
    }

    @And("response body should contain Email {string} , First Name {string} and Last Name {string}")
    public void responseBodyShouldContainEmailFirstNameAndLastName(String email, String first_name, String last_name) {
        SerenityRest.then()
                .body(ReqresAPITestingResponses.EMAIL,equalTo(email))
                .body(ReqresAPITestingResponses.FIRSTNAME,equalTo(first_name))
                .body(ReqresAPITestingResponses.LASTNAME,equalTo(last_name))
                ;
    }

    @Given("Get single user with id {string}")
    public void getSingleUserWithId(String id) {
        reqresAPITesting.getSingleUser(id);
    }

    @Then("API response status code should be {int} not found")
    public void apiResponseStatusCodeShouldBeNotFound(int error) {
        SerenityRest.then().statusCode(404);
    }

    @Given("delete user with id {string}")
    public void deleteUserWithId(String id) {
        reqresAPITesting.deleteUser(id);
    }

    @When("Send request delete user")
    public void sendRequestDeleteUser() {
        SerenityRest.when().delete(ReqresAPITesting.DELETE_USER);
    }

    @Given("delete user with parameter {string}")
    public void deleteUserWithParameter(String parameter) {
        reqresAPITesting.deleteUser(parameter);
    }

    @When("Send request PUT for update user")
    public void sendRequestPutUpdateUser() {
        SerenityRest.when().put(ReqresAPITesting.PUT_UPDATE_USER);
    }

    @Given("Put update user with id {string} and with UpdateUserWithoutName.json file")
    public void putUpdateUserWithIdAndWithUpdateUserWithoutNameJsonFile(String id) {
        File jsonFiles = new File(ReqresAPITesting.FILE_JSON+"/UpdateUserWithoutName.json");
        reqresAPITesting.put_UpdateUser(jsonFiles, id);
    }

    @Given("Put update user with id {string} and with UpdateUserWithoutJob.json file")
    public void putUpdateUserWithIdAndWithUpdateUserWithoutJobJsonFile(String id) {
        File jsonFiles = new File(ReqresAPITesting.FILE_JSON+"/UpdateUserWithoutJob.json");
        reqresAPITesting.put_UpdateUser(jsonFiles, id);
    }

    @And("response body should contain job {string}")
    public void responseBodyShouldContainJob(String job) {
        SerenityRest.then().body(ReqresAPITestingResponses.JOB_USER,equalTo(job));
    }

    @And("response body should contain name {string}")
    public void responseBodyShouldContainName(String name) {
        SerenityRest.then().body(ReqresAPITestingResponses.NAME_USER,equalTo(name));
    }

    @When("Send request patch update user")
    public void sendRequestPatchUpdateUser() {
        SerenityRest.when().patch(ReqresAPITesting.PATCH_UPDATE_USER);

    }

    @Given("Patch update user with id {string} and with valid json file")
    public void patchUpdateUserWithIdIdAndWithValidJsonFile(String id) {
        File jsonFiles = new File(ReqresAPITesting.FILE_JSON+"/UpdateUserWithNameAndJob.json");
        reqresAPITesting.patchUpdateUser(jsonFiles, id);
    }

    @When("Send request get list resources")
    public void sendRequestGetListResources() {
        SerenityRest.when().get(ReqresAPITesting.GET_LIST_RESOURCES);
    }

    @Given("Get list resources with parameter {string}")
    public void getListResourcesWithParameterId(String page) {
        reqresAPITesting.getListResources(page);
    }

    @And("response body should contain name {string} , year {int} and color {string}")
    public void responseBodyShouldContainNameYearAndColor(String name, int year, String color) {
        SerenityRest.then()
                .body(ReqresAPITestingResponses.NAME_RESOURCES,equalTo(name))
                .body(ReqresAPITestingResponses.YEAR,equalTo(year))
                .body(ReqresAPITestingResponses.COLOR,equalTo(color))
        ;
    }

    @And("Get list resources assert json validation")
    public void getListResourcesAssertJsonValidation() {
        File jsonFiles = new File(ReqresAPITesting.FILE_JSON+"/GetListResourcesJSONValidation.json");
        SerenityRest.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(jsonFiles));
    }

    @Given("request post login user with valid data on json file")
    public void requestPostLoginUserWithValidJsonFile() {
        File jsonFiles = new File(ReqresAPITesting.FILE_JSON+"/LoginWithValidEmailAndPassword.json");
        reqresAPITesting.postLoginUser(jsonFiles);
    }

    @When("Send request post login user")
    public void sendRequestPostLoginUser() {
        SerenityRest.when().post(ReqresAPITesting.POST_LOGIN);
    }

    @Given("request post login user with invalid password on json file")
    public void requestPostLoginUserWithInvalidPasswordOnJsonFile() {
        File jsonFiles = new File(ReqresAPITesting.FILE_JSON+"/LoginWithValidEmailAndInvalidPassword.json");
        reqresAPITesting.postLoginUser(jsonFiles);
    }

    @Given("request post login user with invalid email on json file")
    public void requestPostLoginUserWithInvalidEmailOnJsonFile() {
        File jsonFiles = new File(ReqresAPITesting.FILE_JSON+"/LoginWithInvalidEmailAndValidPassword.json");
        reqresAPITesting.postLoginUser(jsonFiles);
    }

    @Then("API response status code should be {int} bad request")
    public void apiResponseStatusCodeShouldBeBadRequest(int error) {
        SerenityRest.then().statusCode(400);
    }

    @Given("request post login user with invalid data on json file")
    public void requestPostLoginUserWithInvalidDataOnJsonFile() {
        File jsonFiles = new File(ReqresAPITesting.FILE_JSON+"/LoginWithInvalidEmailAndInvalidPassword.json");
        reqresAPITesting.postLoginUser(jsonFiles);
    }

    @Given("request post login user with empty email on json file")
    public void requestPostLoginUserWithEmptyEmailOnJsonFile() {
        File jsonFiles = new File(ReqresAPITesting.FILE_JSON+"/LoginWithEmptyEmail.json");
        reqresAPITesting.postLoginUser(jsonFiles);
    }

    @Given("request post login user with empty password on json file")
    public void requestPostLoginUserWithEmptyPasswordOnJsonFile() {
        File jsonFiles = new File(ReqresAPITesting.FILE_JSON+"/LoginWithEmptyPassword.json");
        reqresAPITesting.postLoginUser(jsonFiles);
    }

    @Given("request post register user with valid data on json file")
    public void requestPostRegisterUserWithValidDataOnJsonFile() {
        File jsonFiles = new File(ReqresAPITesting.FILE_JSON+"/RegisterWithValidEmailAndValidPassword.json");
        reqresAPITesting.postRegisterUser(jsonFiles);
    }

    @When("Send request post register user")
    public void sendRequestPostRegisterUser() {
        SerenityRest.when().post(ReqresAPITesting.POST_REGISTER);
    }

    @And("response body should contain id {int} and token {string}")
    public void responseBodyShouldContainIdAndToken(int id, String token) {
        SerenityRest.then()
                .body(ReqresAPITestingResponses.ID,equalTo(id))
                .body(ReqresAPITestingResponses.TOKEN,equalTo(token))
        ;
    }

    @Given("request post register user with invalid email on json file")
    public void requestPostRegisterUserWithInvalidEmailOnJsonFile() {
        File jsonFiles = new File(ReqresAPITesting.FILE_JSON+"/RegisterWithInvalidEmailAndValidPassword.json");
        reqresAPITesting.postRegisterUser(jsonFiles);
    }

    @Given("request post register user with empty email on json file")
    public void requestPostRegisterUserWithEmptyEmailOnJsonFile() {
        File jsonFiles = new File(ReqresAPITesting.FILE_JSON+"/RegisterWithEmptyEmail.json");
        reqresAPITesting.postRegisterUser(jsonFiles);
    }

    @Given("request post register user with empty password on json file")
    public void requestPostRegisterUserWithEmptyPasswordOnJsonFile() {
        File jsonFiles = new File(ReqresAPITesting.FILE_JSON+"/RegisterWithEmptyPassword.json");
        reqresAPITesting.postRegisterUser(jsonFiles);
    }

    @And("post login user assert json validation")
    public void postLoginUserAssertJsonValidation() {
        File jsonFiles = new File(ReqresAPITesting.FILE_JSON+"/LoginJSONValidation.json");
        SerenityRest.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(jsonFiles));
    }

    @Given("Get single resources with parameter {string}")
    public void getSingleResourcesWithParameter(String id) {
        reqresAPITesting.getSingleResources(id);
    }

    @When("Send request get single resources")
    public void sendRequestGetSingleResources() {
        SerenityRest.when().get(ReqresAPITesting.GET_SINGLE_RESOURCES);
    }

    @And("response body should contain name {string} , year {int} , color {string} and pantone value {string}")
    public void responseBodyShouldContainNameYearYearColorAndPantoneValue(String name, int year, String color, String pantone_value) {
        SerenityRest.then()
                .body(ReqresAPITestingResponses.NAME_LIST,equalTo(name))
                .body(ReqresAPITestingResponses.YEAR_LIST,equalTo(year))
                .body(ReqresAPITestingResponses.COLOR_LIST,equalTo(color))
                .body(ReqresAPITestingResponses.PANTONE_VALUE,equalTo(pantone_value))
        ;
    }
}
