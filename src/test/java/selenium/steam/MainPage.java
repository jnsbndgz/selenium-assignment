package selenium.steam;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import selenium.PageBase;


public class MainPage extends PageBase {

    private final By footerBy = By.xpath("//*[@id=\"footerText\"]/span");
    private final By searchBarBy = By.xpath("//*[@id=\"SearchPlayers\"]");
    private final By loginButtonBy = By.xpath("//*[@id=\"global_action_menu\"]/a[2]");
    private final By acceptCookiesBy = By.xpath("//*[@id=\"acceptAllButton\"]");

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

    public void acceptCookies() {
        this.waitAndReturnElement(acceptCookiesBy).click();
    }
}
