package steps;

import com.registration.RegistrationGrab;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;


import static org.junit.jupiter.api.Assertions.*;

public class StepDefinitions2 {

    Logger logger = Logger.getLogger(this.getClass().getName());
    List<String> inputSet;


    @Given("car_input.txt has {word}, {word} and {word}")
    public void given(String arg0, String arg1, String arg2) {
        try {
            FileHandler fileHandler = new FileHandler("error.log");
            logger.addHandler(fileHandler);
        } catch (Exception e6) {
            e6.printStackTrace();
        }
        try {
            File outputFile = new File("car_input.txt");
            if (outputFile.createNewFile()) {
                System.out.println("car_input.txt created");
            }
        } catch (IOException e2) {
            System.out.println("createNewFile Error occurred");
            e2.printStackTrace();
        }
        try {
            FileWriter outputWrite = new FileWriter("car_input.txt");
            outputWrite.write(arg0 + " banana " + arg1 + " apple grape, " + arg2 + " pear.");
            outputWrite.close();
        } catch (IOException e3) {
            System.out.println("writeFile Error occurred");
            e3.printStackTrace();
        }
    }

    @When("I run grabRegistration")
    public void when() {
        File file = new File("car_input.txt");
        inputSet = RegistrationGrab.grabRegistration(file);
    }

    @Then("should return {word}, {word} and {word}")
    public void then(String arg0, String arg1, String arg2) {
        List<String> errors = new ArrayList<>();
        try {
            assertEquals(inputSet.get(0), arg0);
            assertEquals(inputSet.get(1), arg1);
            assertEquals(inputSet.get(2), arg2);
        } catch (Throwable t) {
            errors.add("expected: " + arg0 + " but was: " + inputSet.get(0));
        }
        try {
            assertEquals(inputSet.get(1), arg1);
        } catch (Throwable t) {
            errors.add("expected: " + arg1 + " but was: " + inputSet.get(1));
        }
        try {
            assertEquals(inputSet.get(2), arg2);
        } catch (Throwable t) {
            errors.add("expected: " + arg2 + " but was: " + inputSet.get(2));
        }
        if (errors.size() > 0) {
            for (String s : errors) {
                logger.severe(s);
            }
            fail(errors.toString());
        }
    }
}