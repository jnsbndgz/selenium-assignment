package selenium.steam;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import selenium.PageBase;

public class ProfilePage extends PageBase {

    final private By personaNameBy = By.xpath("//span[@class=\"actual_persona_name\"]");
    final private By logoutButtonBy = By.xpath("//*[@id=\"account_dropdown\"]/div/a[4]");
    final private By menuBy = By.xpath("//*[@id=\"account_pulldown\"]");
    final private By editProfileButtonBy = By.xpath("//*[@id=\"responsive_page_template_content\"]/div[1]/div[1]/div/div/div/div[3]/div[2]/a");
    final private By summaryBy = By.xpath("//*[@id=\"responsive_page_template_content\"]/div[1]/div[1]/div/div/div/div[4]/div[3]");

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    public ProfilePage(WebDriver driver, String url) {
        super(driver);
        this.driver.get(url);
    }

    public String getPersonaName() {
        return this.waitAndReturnElement(personaNameBy).getText();
    }

    public MainPage logout() {
        this.waitAndReturnElement(menuBy).click();
        this.waitAndReturnElement(logoutButtonBy).click();
        return new MainPage(this.driver);
    }

    public ProfileEditorPage editProfile() {
        this.waitAndReturnElement(editProfileButtonBy).click();
        return new ProfileEditorPage(this.driver);
    }

    public String getSummaryText() {
        return this.waitAndReturnElement(summaryBy).getText();
    }
}
