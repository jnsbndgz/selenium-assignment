import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends PageBase {

    private final By usernameBy = By.xpath("//*[@id=\"responsive_page_template_content\"]/div[1]/div[1]/div/div/div/div[2]/div/form/div[1]/input");
    private final By passwordBy = By.xpath("//*[@id=\"responsive_page_template_content\"]/div[1]/div[1]/div/div/div/div[2]/div/form/div[2]/input");
    private final By loginButtonBy = By.xpath("//*[@id=\"responsive_page_template_content\"]/div[1]/div[1]/div/div/div/div[2]/div/form/div[4]/button");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public ProfilePage login(String username, String password) {
        this.waitAndReturnElement(usernameBy).sendKeys(username);
        this.waitAndReturnElement(passwordBy).sendKeys(password);
        this.waitAndReturnElement(loginButtonBy).click();

        return new ProfilePage(this.driver);
    }

}
