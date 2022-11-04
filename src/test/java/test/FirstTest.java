package test;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.Homepage;
import testComponents.BaseTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class FirstTest extends BaseTest {


    @Test(dataProvider = "getDataOneShot", groups = {"Purchase"})
    public void login(HashMap<String, String> input) throws IOException {

        Homepage homepage = landingPage.loginuser(input.get("email"), input.get("password"));
        assertion.assertEquals(homepage.checkAccesstoHomepage(),"AUTOMATION");
        driver.quit();
    }

    @Test()
    public void submitOrder() throws IOException {
        Homepage homepage = landingPage.loginuser("diego.fraile@gmail.com", "Fakekey1234");
        assertion.assertEquals(homepage.checkAccesstoHomepage(),"AUTOMATION");
        homepage.selectElementCart("IPHONE 13 PRO");
        Assert.assertEquals("Product Added To Cart",homepage.productAddedToCartChek());
        CartPage cartPage = homepage.goToCartPage();
        Assert.assertTrue(cartPage.checkifproductExist("IPHONE 13 PRO"));
        cartPage.clickOnBuyNow("IPHONE 13 PRO");
        cartPage.autofillShippingInfo();
        assertion.assertEquals( driver.findElement(By.cssSelector("h1")).getText(),"THANKYOU FOR THE ORDER.");
    }

    @Test(dataProvider = "getData", groups = {"Purchase"})
    public void submitOrderWithJson(HashMap<String, String> input) throws IOException, InterruptedException {
        Homepage homepage = landingPage.loginuser(input.get("email"), input.get("password"));
        assertion.assertEquals(homepage.checkAccesstoHomepage(),"AUTOMATION");
        homepage.selectElementCart(input.get("product"));
        Assert.assertEquals("Product Added To Cart",homepage.productAddedToCartChek());
        CartPage cartPage = homepage.goToCartPage();
        Assert.assertTrue(cartPage.checkifproductExist(input.get("product")));
        cartPage.clickOnBuyNow(input.get("product"));
        cartPage.autofillShippingInfo();
        assertion.assertEquals( driver.findElement(By.cssSelector("h1")).getText(),"THANKYOU FOR THE ORDER.");

    }
    @DataProvider(parallel=true)
    public Iterator<Object>  getDataOneShot() throws IOException {
        List<HashMap<String, String>> data = getJsonDataToMap("C:\\Users\\diego\\IdeaProjects\\TestNGwithSelenium\\src\\test\\java\\Data\\OneShot.json");
        Iterator datastream= data.stream().iterator();
        return datastream;

    }

    @DataProvider
    public Iterator<Object>  getData() throws IOException {
        List<HashMap<String, String>> data = getJsonDataToMap("C:\\Users\\diego\\IdeaProjects\\TestNGwithSelenium\\src\\test\\java\\Data\\firstDataImput.json");
        Iterator datastream= data.stream().iterator();
            return datastream;

    }



}
