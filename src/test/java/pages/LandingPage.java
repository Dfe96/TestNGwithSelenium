package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageObjectFactory.BasePageObjectFactory;

public class LandingPage extends BasePageObjectFactory {
    WebDriver driver;

    public LandingPage(WebDriver driver)
    {
        super(driver);
        //initialization
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(id="userEmail")
    WebElement userEmail;
    @FindBy(id="userPassword")
    WebElement userPassword;
    @FindBy(id="login")
    WebElement login;


    public Homepage loginuser(String mail,String passw){
        userEmail.sendKeys(mail);
        userPassword.sendKeys(passw);
        login.click();
        Homepage homepage = new Homepage(driver);
        return homepage;
    }
    public void goTo(){driver.get("https://rahulshettyacademy.com/client");}

}
