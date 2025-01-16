package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.devtools.v85.page.Page;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class AbstractPage
{
    protected final WebDriver webDriver;
    protected final WebDriverWait webDriverWait;
    public AbstractPage(WebDriver driver)
    {
        this.webDriver = driver;
        this.webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver,this);
    }
    public abstract boolean isAt();
}
