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

    @Given("user {string} with password {string} is created")
    public void userWithPasswordIsCreated(String string, String string2) {
    inputLines.add("new");
    inputLines.add(string);
    inputLines.add(string2);
       
    io = new StubIO(inputLines); 
    app = new App(io, auth);
    app.run();
    }

    @When("username {string} with password {string} are entered")
    public void usernameWithPasswordAreEntered(String string, String string2) {
        inputLines.add(string);
        inputLines.add(string2);

        io = new StubIO(inputLines); 
       app = new App(io, auth);
       app.run();

    }

    @Given("command new is selected")
    public void commandNewIsSelected() {
    inputLines.add("new");
    }

    @When("creation fails with already taken username and valid password")
    public void creatingWithTakenUsernameValidPassword() {
        inputLines.add("pekka");
        inputLines.add("salainen1");

        io = new StubIO(inputLines); 
       app = new App(io, auth);
       app.run();

    }

    @When("creation fails with too short username and valid password")
    public void creationFailShortUsernameValidPassword() {
        inputLines.add("sa");
        inputLines.add("salainen1");

        io = new StubIO(inputLines); 
       app = new App(io, auth);
       app.run();

    }

    @When("creation fails with valid username and too short password")
    public void creationFailValidUsernameShortPassword() {
        inputLines.add("eero");
        inputLines.add("salai");

        io = new StubIO(inputLines); 
       app = new App(io, auth);
       app.run();

    }

    @When("creation fails with valid username and password long enough but consisting of only letters")
    public void creationFailsValidUsernamePasswordLongNotValid() {
        inputLines.add("validNimi");
        inputLines.add("pitkasalainensalasana");

        io = new StubIO(inputLines); 
       app = new App(io, auth);
       app.run();

    }

}
