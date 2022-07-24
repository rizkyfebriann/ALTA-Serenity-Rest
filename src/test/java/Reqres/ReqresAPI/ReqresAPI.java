package Reqres.ReqresAPI;

import java.io.File;

import io.restassured.http.ContentType;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class ReqresAPI {
    public static final String URL = "https://reqres.in";
    public static final String DIR = System.getProperty("user.dir");
    //untuk membaca directory

    public static final String JSON_FILE = DIR+"/src/test/resources/filejsonlatihan";
    public static String GET_LIST_USER = URL+"/api/users?page={page}";

    public static String POST_CREATE_USER = URL+"/api/users";
    public static String PUT_UPDATE_USER = URL+"/api/users/{id}";
    public static String DELETE_USER = URL+"/api/users/{id}";

    @Step("Get list user")
    public void getListUser(String page){
    SerenityRest.given()
        .pathParam("page",page);
        //page di link getlistuser dipakai sbg parameter disini (page yg depan)
        //page yg belakang diambil dari parameter di scenario pada feature nya
    }

    @Step("Post create user")
    public void postCreateUser(File json){
    SerenityRest.given()
        .contentType(ContentType.JSON)
        .body(json);
    }

    @Step("Put update user")
    public void putUpdateUser(File json, int idUser){
        SerenityRest.given()
                .pathParam("id", idUser)
                //id yg depan diambil dari link putupdateuser
                //id yg belakang diambil dari id pada scenarion di feature
                .contentType(ContentType.JSON)
                .body(json);
    }

    @Step("Delete user")
    public void deleteUser(int id){
        SerenityRest.given()
                .pathParam("id",id);
                //hampir sama seperti putupdateuser
    }

}
