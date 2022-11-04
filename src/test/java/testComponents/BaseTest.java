package testComponents;

import com.aventstack.extentreports.ExtentReports;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.Assertion;
import pages.LandingPage;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;


public class BaseTest {

    public WebDriver driver;
    public LandingPage landingPage;
    public Assertion assertion = new Assertion();
    public ExtentReports extent;

    @BeforeMethod(alwaysRun = true)
    public LandingPage launchApplication() throws IOException {

        driver=initializeDriver();
        landingPage = new LandingPage(driver);
        landingPage.goTo();
        return landingPage;

    }
//    @BeforeMethod
//    public void extendReport(){
//        //extentReports , extentSparkReporter
//        String path=System.getProperty("user.dir")+"\\reports\\index.html";
//
//        ExtentSparkReporter reporter =new ExtentSparkReporter(path);
//        reporter.config().setReportName("Webautomation Results");
//        reporter.config().setDocumentTitle("Test results");
//
//        extent=new ExtentReports();
//        extent.attachReporter(reporter);
//        extent.setSystemInfo("tester","Dfe");
//
//    }
    @AfterMethod(alwaysRun = true)
    public void endtestaction(){
        driver.quit();
    }

    //------------------------------------------------------------------------------------------------------------------
    public WebDriver initializeDriver() throws IOException {
        // properties class

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments(
                "--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors"
                ,"--disable-extensions","--no-sandbox","--disable-dev-shm-usage");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        return driver;

    }
    public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
        //read json to string
        String jsonContent = FileUtils.readFileToString(new File(filePath),
                StandardCharsets.UTF_8);

        //String to HashMap- Jackson Databind

        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
        });

        return data;

        //{map, map}

    }

    public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {




        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String base64Screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
        File file = new File(System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png");
        FileUtils.copyFile(source, file);
        return base64Screenshot;


    }
}
