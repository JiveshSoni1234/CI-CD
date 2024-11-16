package rahulshettyacademy.defination;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshey.projects.CartPage;
import rahulshey.projects.CheckOutPage;
import rahulshey.projects.ConfirmationPage;
import rahulshey.projects.LandingPage;
import rahulshey.projects.ProductCatalouge;

public class StepDefinationImplementation extends BaseTest{
     public LandingPage landingpage;
     public ProductCatalouge productCatelogue;
     public ConfirmationPage  confirmationPage;
     
	@Given ("I landed on Ecommerce page")
	public void I_landed_on_Ecommerce_page() throws IOException {
		landingpage= launchApplication();
	}  
	
	@Given ("^Logged in with username(.+) and password(.+)$")
	public void logged_in_usename(String username, String password) {
		
	productCatelogue=landingPage.loginApplication(username,password);	

	}
	
	
	@When("^I add the product (.+)to cart$")
	public void i_add_the_product(String productName)
	{
		List<WebElement> products=productCatelogue.getproductList();
		productCatelogue.addProductToCart(productName);
	}
	
	
	@When("^checkout (.+) and submit the order$")
	public void checkout_productname_and_submit_the_order(String productName) {
		CartPage cartPage=productCatelogue.goToCartPage();
		boolean match=cartPage.VerifyProductDisplay(productName);
        
        //Assert.assertTrue(match);
	
        
        //click on checkout Button
		CheckOutPage checkOutPage=cartPage.gotoCheckOut();
		checkOutPage.SelectCountry("india");
		confirmationPage =checkOutPage.submitOrder();
	}
	
	
	@Then("\"([^\"]*)\" message is displayed on the confirmationPage")
	public void message_is_displayed_on_the_confirmation_page(String string) {
		String messageText= confirmationPage.verifyConfirmationMessage();
		System.out.println(messageText.equalsIgnoreCase("Thankyou for the order."));
    	
	}
	
	
	@Then("\"([^\"]*)\" message is displayed$")
	public void message_is_displayed(String string) {
		Assert.assertEquals(string, landingpage.getErrorMessage());
		driver.close();;
    	
	}
	
}
