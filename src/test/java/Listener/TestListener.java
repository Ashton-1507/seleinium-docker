package Listener;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class TestListener implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) result.getTestContext().getAttribute("driver");
        String screenshot = takesScreenshot.getScreenshotAs(OutputType.BASE64);
        String htmlImageFormat = "<img width=700px src='data:image/png;base64,%s' />";
        String html = String.format(htmlImageFormat,screenshot);
        Reporter.log(html);
    }
}
