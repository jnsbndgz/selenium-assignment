package selenium.steam;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import selenium.PageBase;


public class SearchResultPage extends PageBase {

    private final By firstResultBy = By.xpath("//*[@id=\"search_results\"]/div[2]");

    public SearchResultPage(WebDriver driver) {
        super(driver);
    }

    public String getFirstResult() {
        return this.waitAndReturnElement(firstResultBy).getText();
    }
}
