package com.example.fortlomisw.StepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class ChangePasswordSteps {
    WebDriver driver = null;
    @Given("that the user enters the platform")
    public void that_the_user_enters_the_platform() {
        System.out.println("Inside Step - browser is open");
        String projectPath = System.getProperty("user.dir");
        System.out.println("Project path is : "+projectPath);
        System.out.println("Project path is : "+projectPath+"/src/test/resources/Drivers/chromedriver.exe ");
        System.setProperty("webdriver.chrome.driver",projectPath+"/src/test/resources/Drivers/chromedriver.exe ");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        driver.navigate().to("http://localhost:4200/");
    }

    @Given("press {string}")
    public void press(String string) {
        driver.findElement(By.partialLinkText("You Forgot your Password")).click();
    }

    @When("you write your data")
    public void you_write_your_data() {
        driver.findElement(By.id("mailTo")).sendKeys("selenium");
    }

    @When("click the “Send Mail” button")
    public void click_the_Send_Mail_button() throws InterruptedException {
        driver.findElement(By.id("enter")).sendKeys(Keys.ENTER);
        Thread.sleep(2000);

    }

    @When("the mail has arrived")
    public void the_mail_has_arrived() throws InterruptedException {
        driver.navigate().to("https://mail.google.com");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement email_phone = driver.findElement(By.xpath("//input[@id='identifierId']"));
        email_phone.sendKeys("pruebaseleniumfortlom@gmail.com");
        driver.findElement(By.id("identifierNext")).click();
        WebElement password = driver.findElement(By.xpath("//input[@name='password']"));
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(password));
        password.sendKeys("kamenriderkuuga");
        driver.findElement(By.id("passwordNext")).click();
    }

    @When("select it")
    public void select_it() {

    }

    @When("press the button “Change password”")
    public void press_the_button_Change_password() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("complete the information indicated")
    public void complete_the_information_indicated() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("click on the {string} button")
    public void click_on_the_button(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("your new password was created successfully")
    public void your_new_password_was_created_successfully() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("YOU WRONGLY WRITE YOUR DATA")
    public void you_WRONGLY_WRITE_YOUR_DATA() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the mail has not arrived")
    public void the_mail_has_not_arrived() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("it won't change the password correctly")
    public void it_won_t_change_the_password_correctly() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("already has the password changed on the platform")
    public void already_has_the_password_changed_on_the_platform() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("you complete your corresponding  new data in the form")
    public void you_complete_your_corresponding_new_data_in_the_form() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("hit the “Enter” button for check")
    public void hit_the_Enter_button_for_check() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("will enter the platform with the changed password")
    public void will_enter_the_platform_with_the_changed_password() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
