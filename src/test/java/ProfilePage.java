import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage extends PageBase {

    final private By personaNameBy = By.xpath("//*[@id=\"responsive_page_template_content\"]/div[1]/div[1]/div/div/div/div[1]/div[1]/span[1]");
    final private By logoutButtonBy = By.xpath("//*[@id=\"account_dropdown\"]/div/a[4]");
    final private By menuBy = By.xpath("//*[@id=\"account_pulldown\"]");


    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    public String getPersonaName() {
        return this.waitAndReturnElement(personaNameBy).getText();
    }

    public MainPage logout() {
        this.waitAndReturnElement(menuBy).click();
        this.waitAndReturnElement(logoutButtonBy).click();
        return new MainPage(this.driver);
    }
}
