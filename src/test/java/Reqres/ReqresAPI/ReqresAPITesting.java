package Reqres.ReqresAPI;

import java.io.File;

import io.restassured.http.ContentType;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class ReqresAPITesting {

    public static final String URL_LINK = "https://reqres.in";

    public static final String DIR = System.getProperty("user.dir"); //untuk membaca directory

    public static final String FILE_JSON = DIR+"/src/test/resources/fileJSON";

    public static String GET_SINGLE_USER = URL_LINK+"/api/users/{id}";

    public static String GET_LIST_RESOURCES = URL_LINK+"/api/unknown?page={page}";

    public static String GET_SINGLE_RESOURCES = URL_LINK+"/api/unknown/{id}";

    public static String POST_LOGIN = URL_LINK+"/api/login";

    public static String POST_REGISTER = URL_LINK+"/api/register";

    public static String PUT_UPDATE_USER = URL_LINK+"/api/users/{id}";
    public static String PATCH_UPDATE_USER = URL_LINK+"/api/users/{id}";
    public static String DELETE_USER = URL_LINK+"/api/users/{id}";




    @Step("Get single user")
    public void getSingleUser(String id) {
        SerenityRest.given()
                .pathParam("id", id);
    }

    @Step("Get list resources")
    public void getListResources(String page){
        SerenityRest.given()
                .pathParam("page",page);
    }

    @Step("Get single resources")
    public void getSingleResources(String id){
        SerenityRest.given()
                .pathParam("id",id);
    }

    @Step("Put update user")
    public void put_UpdateUser(File json, String id){
        SerenityRest.given()
                .pathParam("id", id)
                .contentType(ContentType.JSON)
                .body(json);
    }

    @Step("Patch update user")
    public void patchUpdateUser(File json, String id){
        SerenityRest.given()
                .pathParam("id", id)
                .contentType(ContentType.JSON)
                .body(json);
    }

    @Step("Post login user")
    public void postLoginUser(File json){
        SerenityRest.given()
                .contentType(ContentType.JSON)
                .body(json);
    }

    @Step("Post register user")
    public void postRegisterUser(File json){
        SerenityRest.given()
                .contentType(ContentType.JSON)
                .body(json);
    }

    @Step("Delete user")
    public void deleteUser(String id) {
        SerenityRest.given()
                .pathParam("id", id);
    }
}
