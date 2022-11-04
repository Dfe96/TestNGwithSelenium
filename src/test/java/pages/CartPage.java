package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageObjectFactory.BasePageObjectFactory;

import java.util.List;

public class CartPage extends BasePageObjectFactory {
    WebDriver driver;


    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    @FindBy(css = "ul.cartWrap.ng-star-inserted")
    List<WebElement> listCart;
    @FindBy(css = "input[placeholder$='Select Country']")
    WebElement countryField;
    @FindBy(xpath = "(//span[contains(text(),'France')])[1]")
    WebElement franceBtn;
    @FindBy(css = ".btnn.action__submit.ng-star-inserted")
    WebElement placeOrderBtn;


    public boolean checkifproductExist(String product) {

        return listCart.stream()
                .anyMatch(element -> element.findElement(By.cssSelector("h3")).getText().equals(product));
    }

    public void clickOnBuyNow(String product) {

        for (WebElement e:listCart
        ) {
            if(e.findElement(By.cssSelector("h3")).getText().equals(product)){
                e.findElement(By.cssSelector("ul.cartWrap.ng-star-inserted button.btn.btn-primary")).click();
            }
        }
    }
    public void autofillShippingInfo() {

        countryField.sendKeys("fr");
        franceBtn.click();
        placeOrderBtn.click();
    }


}
