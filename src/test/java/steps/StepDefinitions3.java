package steps;

import com.registration.RegistrationGrab;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


import static org.junit.jupiter.api.Assertions.*;

public class StepDefinitions3 {

    Logger logger = Logger.getLogger(this.getClass().getName());
    List<String> inputSet;
    List<String[]> outputSet;

    @Given("grabRegistration returned {word}, {word} and {word}")
    public void given(String arg0, String arg1, String arg2) {
        inputSet.add(arg0);
        inputSet.add(arg1);
        inputSet.add(arg2);
    }

    @When("I run getData")
    public void when() {
        outputSet = RegistrationGrab.getData(inputSet);
    }

    @Then("should return list of data")
    public void then(String arg0, String arg1, String arg2) {
        List<String> errors = new ArrayList<>();
        String[] outputSet1 = outputSet.get(0);
        String[] outputSet2 = outputSet.get(1);
        String[] outputSet3 = outputSet.get(2);
        try {
            assertEquals(outputSet1[0], arg0);
        } catch (Throwable t) {
            errors.add("expected: " + arg0 + " but was: " + outputSet1[0]);
        }
        try {
            assertEquals(outputSet2[0], arg1);
        } catch (Throwable t) {
            errors.add("expected: " + arg1 + " but was: " + outputSet2[0]);
        }
        try {
            assertEquals(outputSet3[0], arg2);
        } catch (Throwable t) {
            errors.add("expected: " + arg2 + " but was: " + outputSet3[0]);
        }
        if (errors.size() > 0) {
            for (String s : errors) {
                logger.severe(s);
            }
            fail(errors.toString());
        }
    }
}