package steps;

import io.cucumber.java.bs.A;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class TodoMvcSteps {

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

    @When("user clicks on the link having label {string}")
    public void user_clicks_on_the_link_having_label(String labelName) {
        // Write code here that turns the phrase above into concrete actions
        driver.findElement(By.xpath("//a[text()='"+labelName+"']")).click();
    }

    @When("user clicks on the clear completed button")
    public void user_clicks_on_the_clear_completed_button() {
        // Write code here that turns the phrase above into concrete actions
        driver.findElement(By.xpath("//a[text()='Completed']//ancestor::ul//following-sibling::button")).click();
    }


    @Then("there are no all,active and completed tabs")
    public void there_are_no_all_active_and_completed_tabs() {
        // Write code here that turns the phrase above into concrete actions
       List<WebElement> all= driver.findElements(By.xpath("//a[text()='All']"));
        List<WebElement> active=  driver.findElements(By.xpath("//a[text()='Active']"));
        List<WebElement> completed= driver.findElements(By.xpath("//a[text()='Completed']"));
        Assert.assertEquals(all.size()+active.size()+completed.size(),0);
    }


    @When("user types {string} consecutively for {string} times and clicks enter")
    public void user_types_consecutively_for_times_and_clicks_enter(String data, String noOfTimes) {
        // Write code here that turns the phrase above into concrete actions
        for(int i=0;i<Integer.parseInt(noOfTimes);i++) {
            driver.findElement(By.xpath("//h1[text()='todos']//following-sibling::input")).sendKeys(data);
        }
        driver.findElement(By.xpath("//h1[text()='todos']//following-sibling::input")).sendKeys(Keys.ENTER);
    }
    @Then("user checks that there is a list of items {string} that are printed consecutively for {string} times")
    public void user_checks_that_there_is_a_list_of_items_that_are_printed_consecutively_for_times(String data, String noOfTimes) {
        data=data.repeat(Integer.parseInt(noOfTimes));
        Assert.assertEquals(driver.findElement(By.xpath("//ul//label[text()='"+data+"']")).getText(),data);
    }

    @When("user double clicks on the list and edits it to {string}")
    public void user_double_clicks_on_the_list_and_edits_it_to(String data) {

        Actions act = new Actions(driver);
       WebElement div= driver.findElement(By.xpath("//ul//div"));
        act.doubleClick(div)
        .keyDown(Keys.CONTROL)
                .sendKeys("a")
                .keyUp(Keys.CONTROL)
                .sendKeys(Keys.BACK_SPACE)
                .sendKeys(data,Keys.ENTER)
                .build()
                .perform();
    }

    @When("user double clicks on item number {string} in the list and edits it to {string}")
    public void user_double_clicks_on_item_number_in_the_list_and_edits_it_to(String index, String data) {
        // Write code here that turns the phrase above into concrete actions
        Actions act = new Actions(driver);
        List<WebElement> div= driver.findElements(By.xpath("//ul//div"));
        WebElement divItem = div.get(Integer.parseInt(index)-1);
        act.doubleClick(divItem)
                .keyDown(Keys.CONTROL)
                .sendKeys("a")
                .keyUp(Keys.CONTROL)
                .sendKeys(Keys.BACK_SPACE)
                .sendKeys(data,Keys.ENTER)
                .build()
                .perform();
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
        driver.findElement(By.xpath("//label[text()='"+item+"']//preceding-sibling::input")).click();
    }

    @Then("user checks that there are {string} items left")
    public void user_checks_that_there_are_items_left(String expectedItemCount) {
        String itemCount = driver.findElement(By.xpath("//span//strong")).getText();
        assertEquals(itemCount,expectedItemCount);
    }

    @Then("user checks that there are no list of items shown")
    public void user_checks_that_there_are_no_list_of_items_shown() {
        // Write code here that turns the phrase above into concrete actions
        List<WebElement> webElements= driver.findElements(By.xpath("//ul//label"));
        assertEquals(webElements.size(),0);
    }

    @Then("user checks that there is a list of with items {string}")
    public void user_checks_that_there_is_a_list_of_with_items(String items) {
        // Write code here that turns the phrase above into concrete actions
       String[] itemArray = items.split(",");
       for(int i=0;i<itemArray.length;i++){
           Assert.assertEquals(driver.findElement(By.xpath("//ul//label[text()='"+itemArray[i]+"']")).getText(),itemArray[i]);
       }
    }
}
