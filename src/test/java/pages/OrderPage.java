package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pageObjectFactory.BasePageObjectFactory;

public class OrderPage extends BasePageObjectFactory {
    WebDriver driver;



    public OrderPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }
}
