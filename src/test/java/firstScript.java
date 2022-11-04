import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.Assertion;

import java.time.Duration;
import java.util.List;

public class firstScript {
    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\diego\\Downloads\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        Assertion assertion = new Assertion();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        driver.get("https://rahulshettyacademy.com/client/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        //login proccess----------------------------------------------------------------------
        driver.findElement(By.id("userEmail")).sendKeys("diego.fraile@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("Fakekey1234");
        driver.findElement(By.id("login")).click();

        //select product from Cart----------------------------------------------------------------------
        List<WebElement> list = driver.findElements(By.cssSelector("div div[class*=col-lg-4]"));
        list.stream()
                .filter(element -> element.findElement(By.cssSelector("h5")).getText().equals("IPHONE 13 PRO"))
                .forEach(element -> element.findElement(By.cssSelector("button[style*='float']")).click());
        //div[aria-label='Product Added To Cart']
        Thread.sleep(1000);
        String confirm = wait.until(ExpectedConditions.visibilityOfElementLocated((By.cssSelector("div[aria-label='Product Added To Cart']")))).getText();
        assertion.assertEquals("Product Added To Cart",confirm);
        //go to next Window
        wait.until(ExpectedConditions.visibilityOfElementLocated((By.cssSelector(".btn.btn-custom[routerlink='/dashboard/cart']")))).click();

        //check if our product exist
        List<WebElement> myCart = driver.findElements(By.cssSelector("ul.cartWrap.ng-star-inserted"));
        assertion.assertTrue(myCart.stream()
                .anyMatch(element -> element.findElement(By.cssSelector("h3")).getText().equals("IPHONE 13 PRO")));
        //we access to cart payment through this for loop( not avalible Stream loop)
        for (WebElement e:myCart
             ) {
            if(e.findElement(By.cssSelector("h3")).getText().equals("IPHONE 13 PRO")){
                e.findElement(By.cssSelector("ul.cartWrap.ng-star-inserted button.btn.btn-primary")).click();
            }
        }
        driver.findElement(By.cssSelector("input[placeholder$='Select Country']")).sendKeys("fr");
        driver.findElement(By.xpath("(//span[contains(text(),'France')])[1]")).click();
        driver.findElement(By.cssSelector(".btnn.action__submit.ng-star-inserted")).click();
        assertion.assertEquals("THANKYOU FOR THE ORDER.",driver.findElement(By.cssSelector("h1")).getText());
    }
}
