package pages.vendorPortal;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.AbstractPage;

public class LoginPage extends AbstractPage
{
    @FindBy(id="username")
    private WebElement userName;
    @FindBy(id="password")
    private WebElement password;
    @FindBy(id="login")
    private WebElement logintbn;
    public LoginPage(WebDriver driver)
    {
        super(driver);
    }

    @Override
    public boolean isAt()
    {
        this.webDriverWait.until(ExpectedConditions.visibilityOf(this.userName));
        return this.userName.isDisplayed();
    }
    public void goTo(String url)
    {
        this.webDriver.get(url);
    }
    public void login(String userName,String Password)
    {
        this.userName.sendKeys(userName);
        this.password.sendKeys(Password);
        this.logintbn.click();
    }
}
