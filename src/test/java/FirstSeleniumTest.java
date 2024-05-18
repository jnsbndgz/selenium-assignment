import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;


public class FirstSeleniumTest {

    private final static String STEAM_USERNAME = "sqatassignment";
    private final static String STEAM_PASSWORD = "w6n@c%#!zDhE4p";

    private WebDriver driver;

    @Before
    public void setup() {
        System.setProperty("webdriver.edge.driver", "C:\\WebDriver\\msedgedriver.exe");
        EdgeOptions options = new EdgeOptions();
        options.setPageLoadStrategy("eager");
        this.driver = new EdgeDriver(options);
        this.driver.manage().window().maximize();
    }

    @Test
    public void testSearch() {
        MainPage mainPage = this.getMainPage();

        Assert.assertEquals("Steam Community", mainPage.getPageTitle());
        Assert.assertTrue(mainPage.getFooterText().contains("Valve Corporation"));

        SearchResultPage searchResultPage = mainPage.search("Conan -iwnl-");
        String firstResult = searchResultPage.getFirstResult();

        Assert.assertTrue(firstResult.contains("VENI. VIDI. VICI."));
    }

    @Test
    public void testLogin() {
        ProfilePage profilePage = this.login();

        Assert.assertEquals("apv45009", profilePage.getPersonaName());
    }

    @Test
    public void testLogout() {
        ProfilePage profilePage = this.login();

        Assert.assertEquals("apv45009", profilePage.getPersonaName());

        MainPage mainPage = profilePage.logout();
        Assert.assertTrue(mainPage.getFooterText().contains("Valve Corporation"));
    }

    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }

    private MainPage getMainPage() {
        return new MainPage(this.driver);
    }

    private ProfilePage login() {
        MainPage mainPage = getMainPage();

        LoginPage loginPage = mainPage.clickLogin();
        return loginPage.login(STEAM_USERNAME, STEAM_PASSWORD);
    }
}
