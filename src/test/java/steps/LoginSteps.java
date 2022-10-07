package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.testng.Assert.assertEquals;

public class LoginSteps {

    WebDriver driver;
    @Given("user is on todomvc page")
    public void user_is_on_todomvc_page() {
        // Write code here that turns the phrase above into concrete actions
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://todomvc.com/examples/vue/");
    }
    @When("user types {string} on the 'What's needs to be done' text box and clicks enter")
    public void user_types_on_the_what_s_needs_to_be_done_text_box_and_clicks_enter(String data) {
        // Write code here that turns the phrase above into concrete actions
        driver.findElement(By.xpath("//h1[text()='todos']//following-sibling::input")).sendKeys(data, Keys.ENTER);
    }

    @Then("a new todo item named {string} is created")
    public void a_new_todo_item_named_is_created(String data) {
        // Write code here that turns the phrase above into concrete actions
        driver.findElement(By.xpath("//label[text()='"+data+"']"));

    }

    @Then("user is able to clear the item called {string} that is created by clicking on the X icon")
    public void user_is_able_to_clear_the_item_called_that_is_created_by_clicking_on_the_x_icon(String item) {
        driver.findElement(By.xpath("//label[text()='"+item+"']//following-sibling::button")).click();
    }

    @Then("user is able to click the checkbox to clear the item called {string}")
    public void user_is_able_to_click_the_checkbox_to_clear_the_item_called(String item) {
        // Write code here that turns the phrase above into concrete actions
        driver.findElement(By.xpath("//label[text()='"+item+"']//preceding-sibling::input")).click();
        ////span//strong[text()='0']
        String itemCount = driver.findElement(By.xpath("//span//strong")).getText();
        assertEquals(itemCount,"0");
    }

}
