package selenium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import selenium.steam.*;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SeleniumTest {

    private WebDriver driver;
    private Properties config;

    @Before
    public void setup() throws IOException {
        System.setProperty("webdriver.edge.driver", "C:\\WebDriver\\msedgedriver.exe");

        this.config = new Properties();
        config.load(new FileReader("app.config"));

        EdgeOptions options = new EdgeOptions();
        options.setPageLoadStrategy("eager");
        this.driver = new EdgeDriver(options);
        this.driver.manage().window().maximize();
    }

    @Test
    public void testSearch() {
        MainPage mainPage = this.getMainPage();

        assertEquals("Steam Community", mainPage.getPageTitle());
        assertTrue(mainPage.getFooterText().contains("Valve Corporation"));

        SearchResultPage searchResultPage = mainPage.search("Conan -iwnl-");
        String firstResult = searchResultPage.getFirstResult();

        assertTrue(firstResult.contains("VENI. VIDI. VICI."));
    }

    @Test
    public void testCookies() throws InterruptedException {
        MainPage mainPage = this.getMainPage();

        mainPage.acceptCookies();

        Set<Cookie> cookies = this.driver.manage().getCookies();

        this.driver.quit();

        EdgeOptions options = new EdgeOptions();
        options.setPageLoadStrategy("eager");
        this.driver = new EdgeDriver(options);
        this.driver.manage().window().maximize();

        this.getMainPage();

        this.driver.manage().deleteAllCookies();

        for (Cookie cookie : cookies) {
            this.driver.manage().addCookie(cookie);
        }

        Assert.assertArrayEquals(cookies.toArray(), this.driver.manage().getCookies().toArray());
    }

    @Test
    public void testLogin() {
        ProfilePage profilePage = this.login();

        assertEquals(config.getProperty("STEAM_PERSONA_NAME"), profilePage.getPersonaName());
    }

    @Test
    public void testLogout() {
        ProfilePage profilePage = this.login();

        assertEquals(config.getProperty("STEAM_PERSONA_NAME"), profilePage.getPersonaName());

        MainPage mainPage = profilePage.logout();
        assertTrue(mainPage.getFooterText().contains("Valve Corporation"));
    }

    @Test
    public void testEditProfile() {
        ProfilePage profilePage = this.login();
        ProfileEditorPage profileEditorPage = profilePage.editProfile();

        String summary = Base64.getEncoder().encodeToString((System.currentTimeMillis() + "").getBytes(StandardCharsets.UTF_8));

        profileEditorPage.editSummary(summary);

        this.driver.navigate().back();
        this.driver.navigate().refresh();

        profilePage = new ProfilePage(this.driver);

        assertEquals(summary, profilePage.getSummaryText());
    }

    @Test
    public void extendableStaticTest() {
        for (String entity : config.getProperty("EXTENDABLE_TEST").split(";")) {
            String[] parts = entity.split(",");

            ProfilePage profilePage = new ProfilePage(this.driver, "https://steamcommunity.com/id/" + parts[0]);

            assertEquals(parts[1], profilePage.getPersonaName());
        }
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
        return loginPage.login(config.getProperty("STEAM_USERNAME"), config.getProperty("STEAM_PASSWORD"));
    }

}
