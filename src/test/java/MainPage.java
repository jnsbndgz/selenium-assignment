import org.junit.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;


class MainPage extends PageBase {

    private final By footerBy = By.xpath("//*[@id=\"footerText\"]/span");
    private final By searchBarBy = By.xpath("//*[@id=\"SearchPlayers\"]");
    private final By loginButtonBy = By.xpath("//*[@id=\"global_action_menu\"]/a[2]");

    public MainPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://steamcommunity.com/");
    }    
    
    public String getFooterText() {
        return this.waitAndReturnElement(footerBy).getText();
    }
    
    public SearchResultPage search(String searchQuery) {
        this.waitAndReturnElement(searchBarBy).sendKeys(searchQuery + "\n");
        return new SearchResultPage(this.driver);
    }

    public LoginPage clickLogin() {
        this.waitAndReturnElement(loginButtonBy).click();
        return new LoginPage(this.driver);
    }
}
