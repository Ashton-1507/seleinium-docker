package pages.flightReservation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.AbstractPage;

public class FlightConfirmationPage extends AbstractPage
{
    private static final Logger log = LoggerFactory.getLogger(FlightConfirmationPage.class);
    @FindBy(css="#flights-confirmation-section .card-body .row:nth-child(1) .col:nth-child(2)")
    private WebElement flightConfirmationEle;
    @FindBy(xpath = "//*[@id=\"registration-confirmation-section\"]/div/div/div/p/b")
    private WebElement name;
    @FindBy(css="#flights-confirmation-section .card-body .row:nth-child(3) .col:nth-child(2)")
    private WebElement totalPrice;
    public FlightConfirmationPage(WebDriver driver)
    {
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.webDriverWait.until(ExpectedConditions.visibilityOf(this.flightConfirmationEle));
        return this.flightConfirmationEle.isDisplayed();
    }
    public String getPrice()
    {
        String comfirmation = this.flightConfirmationEle.getText();
        String price = this.totalPrice.getText();
        log.info("Flight Confirmation: {}",comfirmation);
        log.info("Total Price: {}",price);
        return price;
    }
    public String getName()
    {
        return this.name.getText();
    }
}
