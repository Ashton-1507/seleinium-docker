package pages.flightReservation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import pages.AbstractPage;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.Keys;
public class FlightsSearch extends AbstractPage
{
    @FindBy(id="passengers")
    private WebElement passengers;
    @FindBy(id="search-flights")
    private WebElement searchFlights;
    public FlightsSearch(WebDriver driver)
    {
        super(driver);
    }
    @Override
    public boolean isAt() {
        this.webDriverWait.until(ExpectedConditions.visibilityOf(this.searchFlights));
        return this.passengers.isDisplayed();
    }
    public void selectPassengers(String numOfPassengers)
    {
        Select passenger = new Select(this.passengers);
        passenger.selectByValue(numOfPassengers);
    }
    public void flightSearchBTn()
    {
        Actions actions = new Actions(this.webDriver);
        actions.sendKeys(Keys.ESCAPE).perform();
        this.searchFlights.click();
    }
}
