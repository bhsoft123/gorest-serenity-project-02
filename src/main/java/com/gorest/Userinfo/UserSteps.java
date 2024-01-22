package com.gorest.Userinfo;

import com.gorest.constants.EndPoints;
import com.gorest.model.UserPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.rest.SerenityRest;

import java.util.HashMap;

public class UserSteps {

    @Step
    public ValidatableResponse createUser(String name, String email,
                                          String gender, String status) {
        UserPojo userPojo = new UserPojo();
        userPojo.setName(name);
        userPojo.setEmail(email);
        userPojo.setGender(gender);
        userPojo.setStatus(status);

        return SerenityRest.given()
                .header("Authorization", "Bearer 8b34b9f18414d977f526f447951f03e48d2d8675cf9552d770b9ed590bd7620e")
                .header("Connection", "keep-alive")
                .contentType(ContentType.JSON)
                .when()
                .body(userPojo)
                .post(EndPoints.GET_ALL_USERS)
                .then().log().all();
    }

    @Step("Getting the user information with firstname :{0}")
    public HashMap<String, Object> getUserInfoById(String name) {
        String s1 = "findAll{it.name == '";
        String s2 = "'}.get(0)";

        return SerenityRest.given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer 8b34b9f18414d977f526f447951f03e48d2d8675cf9552d770b9ed590bd7620e")
                .header("Connection", "keep-alive")
                .header("Accept", "*/*")
                .header("Content-Type", "application/json")
                .when()
                .get(EndPoints.GET_ALL_USERS)
                .then().statusCode(200)
                .extract()
                .path(s1 + name + s2);
    }




    @Step
    public ValidatableResponse updateUser(int id,String name, String email,
                                          String gender,String status) {
        UserPojo userPojo = new UserPojo();
        userPojo.setName("updatedname");
        userPojo.setEmail(email);
        userPojo.setGender(gender);
        userPojo.setStatus(status);

        return SerenityRest.given().log().all()
                .header("Authorization", "Bearer 8b34b9f18414d977f526f447951f03e48d2d8675cf9552d770b9ed590bd7620e")
                .header("Connection", "keep-alive")
                .header("Accept", "*/*")
                .header("Content-Type", "application/json")
                .pathParam("user_id",id)
                .when()
                .body(userPojo)
                .put(EndPoints.UPDATE_USER_BY_ID )
                .then().log().all();
    }
    @Step("Deleting User information with userId : {0}")
    public ValidatableResponse deleteUser(int id) {
        return SerenityRest.given().log().all()
                .header("Authorization", "Bearer 8b34b9f18414d977f526f447951f03e48d2d8675cf9552d770b9ed590bd7620e")
                .header("Connection", "keep-alive")
                .header("Accept", "*/*")
                .header("Content-Type", "application/json")
                .pathParam("user_id", id)
                .when()
                .delete(EndPoints.DELETE_USER_BY_ID)
                .then()
                .statusCode(204);

    }

    @Step("Getting user information with userId : {0}")
    public ValidatableResponse getUserById(int id) {

        return SerenityRest.given().log().all()
                .pathParam("user_id", id)
                .when()
                .get(EndPoints.GET_SINGLE_USER_BY_ID)
                .then()
                .statusCode(404);

    }
}