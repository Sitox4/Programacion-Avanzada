package es.udc.paproject.e2etests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.Duration;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    /**
     * Rigorous Test :-)
     */
    WebDriver driver;

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupTest() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

    void login(String userName, String password){
        driver.get("http://localhost:3000");
        WebElement login = driver.findElement(By.id("loggingLink"));
        login.click();

        WebElement userNameBox = driver.findElement(By.id("userName"));
        WebElement passwordBox = driver.findElement(By.id("password"));
        WebElement submitButton = driver.findElement(By.id("submitButton"));

        userNameBox.sendKeys(userName);
        passwordBox.sendKeys(password);
        submitButton.click();

        WebElement userNameResult = driver.findElement(By.id("userNameHeader"));
        assertEquals(" viewer", userNameResult.getText());
    }

    void login2(String userName, String password){
        driver.get("http://localhost:3000");
        WebElement login = driver.findElement(By.id("loggingLink"));
        login.click();

        WebElement userNameBox = driver.findElement(By.id("userName"));
        WebElement passwordBox = driver.findElement(By.id("password"));
        WebElement submitButton = driver.findElement(By.id("submitButton"));

        userNameBox.sendKeys(userName);
        passwordBox.sendKeys(password);
        submitButton.click();

        WebElement userNameResult = driver.findElement(By.id("userNameHeader"));
        assertEquals(" ticketseller", userNameResult.getText());
    }

    void buyTickets(){
        login("viewer","pa2223");
        driver.get("http://localhost:3000/catalog/sesiones/3");

        WebElement movieTitle = driver.findElement(By.id("movieTitle"));
        String movieTitleText = movieTitle.getText();

        WebElement quantity = driver.findElement(By.id("quantity"));
        WebElement creditCard = driver.findElement(By.id("creditCard"));
        WebElement submitButton = driver.findElement(By.id("submitButton"));

        quantity.clear();
        quantity.sendKeys("2");
        creditCard.sendKeys("1234567891234567");
        submitButton.click();

        WebElement userNameHeader = driver.findElement(By.id("userNameHeader"));
        userNameHeader.click();
        WebElement salir = driver.findElement(By.id("salir"));
        salir.click();
    }



    @Test
    void testLogin() {
        // Verify
        login("viewer","pa2223");
    }

    @Test
    void testBuyTickets() {
        login("viewer","pa2223");
        driver.get("http://localhost:3000/catalog/sesiones/3");

        WebElement movieTitle = driver.findElement(By.id("movieTitle"));
        String movieTitleText = movieTitle.getText();

        WebElement quantity = driver.findElement(By.id("quantity"));
        WebElement creditCard = driver.findElement(By.id("creditCard"));
        WebElement submitButton = driver.findElement(By.id("submitButton"));

        quantity.clear();
        quantity.sendKeys("2");
        creditCard.sendKeys("1234567891234567");
        submitButton.click();

        WebElement compraId = driver.findElement(By.id("compraId"));
        String compraIdText = compraId.getText();

        WebElement userNameHeader = driver.findElement(By.id("userNameHeader"));
        userNameHeader.click();
        WebElement historial = driver.findElement(By.id("historial"));
        historial.click();

        WebElement idCompra = driver.findElement(By.id("idCompra"));
        WebElement tituloPelicula = driver.findElement(By.id("tituloPelicula"));
        assertEquals(compraIdText, idCompra.getText());
        assertEquals(movieTitleText, tituloPelicula.getText());
    }

    @Test
    void testGiveTickets(){
        buyTickets();
        login2("ticketseller", "pa2223");
        driver.get("http://localhost:3000");

        WebElement userNameHeader = driver.findElement(By.id("userNameHeader"));
        userNameHeader.click();
        WebElement giveTickets = driver.findElement(By.id("give-tickets"));
        giveTickets.click();

        WebElement idCompra = driver.findElement(By.id("compraId"));
        WebElement creditCard = driver.findElement(By.id("creditCard"));
        WebElement submitButton = driver.findElement(By.xpath("//button[@type='submit']"));

        idCompra.sendKeys("3");
        creditCard.sendKeys("1234567891234567");
        submitButton.click();

        WebElement successMessage = driver.findElement(By.xpath("//div[contains(@class, 'alert-succes')]"));
        assertNotNull(successMessage);


        idCompra.sendKeys("3");
        creditCard.sendKeys("1234567891234567");
        submitButton.click();

        WebElement errorMessage = driver.findElement(By.xpath("//div[contains(@class, 'alert-danger')]"));
        assertNotNull(errorMessage);

        driver.quit();

    }

}
