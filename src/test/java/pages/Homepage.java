package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageObjectFactory.BasePageObjectFactory;

import java.util.List;

public class Homepage extends BasePageObjectFactory {
    WebDriver driver;

    public Homepage(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(css="div div[class*=col-lg-4]")
    List<WebElement> list;
    @FindBy(css="div[aria-label='Product Added To Cart']")
    WebElement product_confirmation;
    @FindBy(css = "div[class='left mt-1'] h3")
    WebElement automationTitle;

    public void selectElementCart(String product){
        list.stream()
                .filter(element -> element.findElement(By.cssSelector("h5")).getText().equals(product))
                .forEach(element -> element.findElement(By.cssSelector("button[style*='float']")).click());
    }
    public String productAddedToCartChek(){
        waitForWebElementToAppear(product_confirmation);
        return product_confirmation.getText();

    }
    public String checkAccesstoHomepage(){
        waitForWebElementToAppear(automationTitle);
        System.out.println(automationTitle.getText());
        return automationTitle.getText();
    }


}
