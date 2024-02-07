package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
class CreatePageFunctionalTest {
    @LocalServerPort
    private int serverPort;

    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;

    @BeforeEach
    void setupTest(){
        baseUrl = String.format("%s:%d",testBaseUrl,serverPort);
    }

    @Test
    void testCreateProduct(ChromeDriver driver) throws Exception {
        try {
            driver.get(baseUrl + "/product/create");
            WebElement nameInput = driver.findElement(By.id("nameInput"));
            WebElement quantityInput = driver.findElement(By.id("quantityInput"));
            WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));

            nameInput.sendKeys("Test");
            quantityInput.sendKeys("25");
            submitButton.click();

            Thread.sleep(1000);
            driver.get(baseUrl + "/product/list");
            WebElement table = driver.findElement(By.tagName("table"));
            List<WebElement> rows = table.findElements(By.tagName("tr"));

            boolean found = false;
            for (WebElement row : rows) {
                List<WebElement> cells = row.findElements(By.tagName("td"));
                if(cells.size()>2){
                    String productName = cells.get(0).getText();
                    String quantity = cells.get(1).getText();
                    if (productName.equals("Test") && quantity.equals("25")) {
                        found = true;
                        break;
                    }
                }
            }
            assertTrue(found, "The created product is not in the product list.");
        } finally {
            driver.quit();
        }
    }
}
