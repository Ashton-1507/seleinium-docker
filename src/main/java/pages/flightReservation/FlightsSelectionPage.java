package pages.flightReservation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.AbstractPage;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class FlightsSelectionPage extends AbstractPage
{
    @FindBy(name="departure-flight")
    private List<WebElement> departueFlights;

    @FindBy(name="arrival-flight")
    private List<WebElement> arrivalFlights;
    @FindBy(id="confirm-flights")
    private WebElement confirmFlightsBtn;
    public FlightsSelectionPage(WebDriver driver)
    {
        super(driver);
    }

    @Override
    public boolean isAt()
    {
        this.webDriverWait.until(ExpectedConditions.visibilityOf(this.confirmFlightsBtn));
        return this.confirmFlightsBtn.isDisplayed();
    }
    public void selectFlights()
    {
        int random = ThreadLocalRandom.current().nextInt(0,departueFlights.size());
        this.arrivalFlights.get(random).click();
        this.departueFlights.get(random).click();
    }
    public void confirmFlights()
    {
        this.confirmFlightsBtn.click();
    }
}
