package common

import io.restassured.RestAssured
import io.restassured.http.ContentType
import io.restassured.response.Response

class RestAssuredHelper {

    companion object {

        var body: String? = null;
        lateinit var response: Response;

        fun init(baseUrl: String) {
            RestAssured.port = 80
            RestAssured.basePath = baseUrl
        }

        fun doPost(body: String?, url: String): Response {
            return RestAssured
                .given()
                    .body(body)
                    .contentType(ContentType.JSON)
                .`when`()
                    .post(url)
        }

        fun doGet(url: String): Response {
            return RestAssured
                .given()
                     .contentType(ContentType.JSON)
                .`when`()
                    .get(url)
        }

    }
}