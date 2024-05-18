package selenium.steam;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.PageBase;

public class ProfileEditorPage extends PageBase {

    private final By summaryTextAreaBy = By.xpath("//*[@id=\"react_root\"]/div[3]/div[2]/form/div[5]/div[2]/textarea");
    private final By submitFormBy = By.xpath("//*[@id=\"react_root\"]/div[3]/div[2]/form/div[7]/button[1]");
    private final By avatarEditBy = By.xpath("//*[@id=\"react_root\"]/div[3]/div[1]/a[2]");
    private final By avatarUploadBy = By.xpath("/html/body/div[1]/div[7]/div[4]/div/div[2]/div/div/div[3]/div[3]/div[2]/div/div[1]/div[3]/div[2]/input");
    private final By saveAvatarBy = By.xpath("//*[@id=\"react_root\"]/div[3]/div[2]/div/div[2]/button[1]");
    private final By profileLinkBy = By.xpath("//*[@id=\"profile_edit_main_content\"]/div[1]/div/div[1]/span[1]/a");

    public ProfileEditorPage(WebDriver driver) {
        super(driver);
    }

    public void editSummary(String summary) {
        WebElement textArea = this.waitAndReturnElement(summaryTextAreaBy);

        textArea.clear();
        textArea.sendKeys(summary);

        this.waitAndReturnElement(submitFormBy).submit();
    }

}
