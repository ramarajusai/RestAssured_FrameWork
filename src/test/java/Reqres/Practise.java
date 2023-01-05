package Reqres;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

public class Practise {
	
	int id;
	@Test
	public void getUsers() {
		
		given()
		.when().get("https://reqres.in/api/users?page=2")
		.then().statusCode(200).log().all();
		
		
	}
	
	
	@Test
	public void createUser() {
	
		HashMap data =new HashMap();
		data.put("name", "rama");
		data.put("job", "QA");
		
		id=given().contentType("application/json").body(data)
	
		.when().post("https://reqres.in/api/users").jsonPath().getInt("id");
		//.then().statusCode(201);
		System.out.println("------------------------------------------id="+id);
	}
	
	@Test
	public void updateUser() {
		HashMap data =new HashMap();
		data.put("name", "rama1");
		data.put("job", "dev");

		given().contentType("application/json").body(data)
		.when().put("https://reqres.in/api/users/"+id)
		.then().statusCode(200).log().all();
		
	}
	
	
	@Test
	public void deleteUser() {
		
		given()
		.when().delete("https://reqres.in/api/users/"+id)
		.then().statusCode(204).log().all();
		
	}
}
