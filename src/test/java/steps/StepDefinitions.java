package steps;

import com.registration.RegistrationGrab;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StepDefinitions {

    @Given("car_input.txt contains {word}, {word} and {word}")
    public void given(String arg0, String arg1, String arg2) {
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
            outputWrite.write(arg0 + " banana "+arg1+" apple grape, "+arg2+" pear.");
            outputWrite.close();
        } catch (IOException e3) {
            System.out.println("writeFile Error occurred");
            e3.printStackTrace();
        }
    }

    @When("I run RegistrationGrab.java")
    public void when() {
        File file = new File("car_input.txt");
        List<String> inputSet = RegistrationGrab.grabRegistration(file);
        List<String[]> outputSet = RegistrationGrab.getData(inputSet);
        RegistrationGrab.generateOutput(outputSet);
    }

    @Then("car_output.txt should contain reg plates and details")
    public void then() {
        File expectedFile = new File("car_sample.txt");
        try {
            Scanner readExpected = new Scanner(expectedFile);

            File actualFile = new File("car_output.txt");
            Scanner readActual = new Scanner(actualFile);
            while (readExpected.hasNextLine()) {
                String readExpectedIn = readExpected.nextLine();
                String readActualIn = readActual.nextLine();
                assertEquals(readExpectedIn, readActualIn, "failed, expected: " + readExpectedIn + "actual: " + readActualIn);
            }
        }
        catch (FileNotFoundException e4) {
            System.out.println("File not found in location");
            e4.printStackTrace();
        }
    }
}
