package Reqres.ReqresAPIStepDefs;

import java.io.File;
import Reqres.ReqresAPI.ReqresAPI;
import Reqres.ReqresResponses.ReqresResponses;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.module.jsv.JsonSchemaValidator;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import static org.hamcrest.Matchers.equalTo;

public class ReqresAPIStepDefs {

    @Steps //manggil method dari step pada ReqresAPI
    ReqresAPI reqresApi; //declare ReqresAPI menjadi method baru untuk dipanggil dipakai pada semua step

    @Given("Get list user with parameter {string}")
    public void getListUserWithParameter(String page) {
    reqresApi.getListUser(page); //manggil method getlistuser
    }

    @When("Send request get list user")
    public void sendRequestGetListUser() {
        SerenityRest.when().get(ReqresAPI.GET_LIST_USER); //manggil URL getlistuser
}

    @Then("status code should be {int} OK")
    public void statusCodedShouldBeOK(int OK) {
    SerenityRest.then().statusCode(200);
    }

    @And("response body should contain First name {string} and Last name {string}")
    public void responseBodyShouldContainFirstNameAndLastName(String firstName, String lastName) {
        SerenityRest.then().body(ReqresResponses.FIRST_NAME,equalTo(firstName));
        SerenityRest.then().body(ReqresResponses.LAST_NAME,equalTo(lastName));
    }

    @Given("Post create new user with valid json file")
    public void postCreateNewUserWithValidJsonFile() {
        File jsonFiles = new File(ReqresAPI.JSON_FILE+"/CreateUser.json");
        reqresApi.postCreateUser(jsonFiles);
    }
    @When("Send request post create user")
    public void sendRequestPostCreateUser() {
        SerenityRest.when().post(ReqresAPI.POST_CREATE_USER); //manggil link postcreateuser
    }

    @Then("status code should be {int} Created")
    public void statusCodeShouldBeCreated(int created) {
        SerenityRest.then().statusCode(201);
    }

    @And("response body should contain name {string} and job {string}")
    public void responseBodyShouldContainNameAndJob(String name, String job) {
        SerenityRest.then()
                .body(ReqresResponses.NAME,equalTo(name))
                .body(ReqresResponses.JOB,equalTo(job))
                ;
    }

    @Given("Put update user with id {int} and with valid json file")
    public void putUpdateUserWithIdAndWithValidJsonFile(int id) {
        File jsonFiles = new File(ReqresAPI.JSON_FILE+"/UpdateUser.json");
        reqresApi.putUpdateUser(jsonFiles, id);
    }

    @When("Send request put update user")
    public void sendRequestPutUpdateUser() {
        SerenityRest.when().put(ReqresAPI.PUT_UPDATE_USER);
    }

    @Given("Delete user with id {int}")
    public void deleteUserWithId(int id) {
        reqresApi.deleteUser(id);
    }

    @When("send request delete user")
    public void sendRequestDeleteUser() {
        SerenityRest.when().delete(ReqresAPI.DELETE_USER);
    }

    @Then("Status code should be {int} No Content")
    public void statusCodeShouldBeNoContent(int deleted) {
        SerenityRest.then().statusCode(204);
    }

    @And("Get list user assert json validation")
    public void getListUserAssertJsonValidation() {
        File jsonFiles = new File(ReqresAPI.JSON_FILE+"/GetListUserJSONValidation.json");
        //ini buat membaca letak file json

        SerenityRest.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(jsonFiles));
    }

    @And("post create user json validation")
    public void postCreateUserJsonValidation() {
        File jsonFiles = new File(ReqresAPI.JSON_FILE+"/PostCreateUserJSONValidation.json");
        SerenityRest.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(jsonFiles));
    }
}
