package tests;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RestTests {

    @Test
    public void getUser() {
        JsonPath expectedJsonGet = new JsonPath(new File("src/test/resources/testData/test.json"));

        given()
                .baseUri("https://reqres.in")
                .contentType(ContentType.JSON)
                .when().get("/api/users?page=2")
                .then().statusCode(200)
                .body("", equalTo(expectedJsonGet.getMap("")))
                .body("data[1].email", equalTo("lindsay.ferguson@reqres.in"));
    }


    @Test
    public void postUser() {
        JsonPath expectedJsonPost = new JsonPath(new File("src/test/resources/testData/post.json"));

        given()
                .baseUri("https://reqres.in/api")
                .contentType(ContentType.JSON)
                .body(expectedJsonPost)
                .when().post("/api/users")
                .then().statusCode(201);
    }


}
