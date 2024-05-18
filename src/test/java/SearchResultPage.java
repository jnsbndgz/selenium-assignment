import org.openqa.selenium.WebDriver;

import org.openqa.selenium.By;


class SearchResultPage extends PageBase {

    private final By firstResultBy = By.xpath("//*[@id=\"search_results\"]/div[2]");

    public SearchResultPage(WebDriver driver) {
        super(driver);
    }    

    public String getFirstResult() {
        return this.waitAndReturnElement(firstResultBy).getText();
    }
}
