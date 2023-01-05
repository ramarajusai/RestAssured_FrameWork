package Reqres;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

public class HttpRequests {

	/*
	 * given() - content type, set cookies, set headers, add auth, add params
	 * 
	 * 
	 * when() - get/post/put/delete
	 * 
	 * then()- validate status code,extract response, extract headers, cookies,&
	 * response body
	 */

	int id;

	@Test(priority=1)
	public void getAllUsers() {

		given() // NO NEED OF GIVEN BECAUSE AS IT IS GET WE DONT INPUT ANY DATA
				// FOR FIRST METHOD NO NEED OF "."

				.when().get("https://reqres.in/api/users?page=2")

				.then().statusCode(200).body("page", equalTo(2)).log().all();

	}

	@Test(priority = 2)
	public void createUser() {

		// IN HASHMAP DATA STORED IN KEY-VALUE PAIR
		HashMap data = new HashMap();
		data.put("name", "pavan");
		data.put("job", "trainer");

		id = given().contentType("application/json").body(data)

				.when().post("https://reqres.in/api/users").jsonPath().getInt("id");

		// .then().statusCode(201).log().all();
	}

	@Test(priority = 3, dependsOnMethods = "createUser")
	public void updateUser() {
		HashMap data = new HashMap();
		data.put("name", "pavan1");
		data.put("job", "trainer1");

		given().contentType("application/json").body(data)
		.when().put("https://reqres.in/api/users/"+id) // concatination// with id generated in POST REQUEST
																											
				.then().statusCode(200).log().all();

	}
	
	
	@Test(priority=4)
	
public void deleteUser() {
		
		given()
		.when().delete("https://reqres.in/api/users/"+id)
		
		.then().statusCode(204).log().all();
		
		
	}
}
