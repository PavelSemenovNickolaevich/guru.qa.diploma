//package helpers;
//
//import org.aeonbits.owner.ConfigFactory;
//
//import static io.restassured.RestAssured.given;
//
//public class Browserstack {
//    public static String videoUrl(String sessionId) {
//
//        return given()
//                .auth().basic(ConfigFactory.create(BrowserstackConfigData.class).user(), ConfigFactory.create(BrowserstackConfigData.class).key())
//                .when()
//                .get("https://api-cloud.browserstack.com/app-automate/sessions/" + sessionId +".json")
//                .then()
//                .statusCode(200)
//                .log().body()
//                .extract()
//                .response()
//                .path("automation_session.video_url");
//
//    }
//}