package ohtu;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import ohtu.io.*;
import ohtu.data_access.*;
import ohtu.services.*;

public class Stepdefs {
    App app;
    StubIO io;
    UserDao userDao;
    AuthenticationService auth;
    List<String> inputLines;
    
    @Before
    public void setup(){
        userDao = new InMemoryUserDao();
        auth = new AuthenticationService(userDao);
        inputLines = new ArrayList<>();      
    }
    
    @Given("^command login is selected$")
    public void commandLoginSelected() throws Throwable {
        inputLines.add("login");
    }

    @When("username {string} and password {string} are entered")
    public void usernameAndPasswordAreEntered(String username, String password) {
       inputLines.add(username);
       inputLines.add(password);
       
       io = new StubIO(inputLines); 
       app = new App(io, auth);
       app.run();
    }    
    
    @Then("system will respond with {string}")
    public void systemWillRespondWith(String expectedOutput) {
        assertTrue(io.getPrints().contains(expectedOutput));
    }    

    @When("incorrect username and correct password are entered")
    public void incorrectUsernameAndCorrectPasswordAreEntered() {
        inputLines.add("new");
        inputLines.add("salainen");
        inputLines.add("tosi");

        inputLines.add("login");
        inputLines.add("wow");
        inputLines.add("tosi");

        io = new StubIO(inputLines); 
       app = new App(io, auth);
       app.run();
    }


    @When("correct username and incorrect password are entered")
    public void correctUsernameAndIncorrectPasswordAreEntered() {

        inputLines.add("new");
        inputLines.add("tosi");
        inputLines.add("salainen");

        inputLines.add("login");
        inputLines.add("tosi");
        inputLines.add("tosi");

        io = new StubIO(inputLines); 
       app = new App(io, auth);
       app.run();

    }

    @Then("system will respond with wrong username or password")
    public void usernameIsNonexistentRespond(String usernameIsNonexistentRespond) {
        assertTrue(io.getPrints().contains(usernameIsNonexistentRespond));
    } 
}
