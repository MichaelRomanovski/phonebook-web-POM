import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginRegistrationPage extends HelperUser {




    public LoginRegistrationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//button[.='Registration']")
    private WebElement registration_button;

    @FindBy(xpath = "//button[.='Login']")
    private WebElement login_button;


    @FindBy(xpath = "//input[@name='email']")
    private WebElement email_field;
    @FindBy(xpath = "//input[@name='password']")
    private WebElement password_field;

    public LoginRegistrationPage type_field_email_and_password(String email, String password) {
        email_field.click();
        email_field.clear();
        email_field.sendKeys(email);
        password_field.click();
        password_field.clear();
        password_field.sendKeys(password);
        return this;
    }

    public void click_on_registration_btn() {
        registration_button.click();
        pause(4000);

    }

    public void click_on_LOGIN_btn() {
        login_button.click();
        pause(4000);
    }
    public AddContact_page click_on_LOGIN_btn_going_to_addcontact_page() {
        login_button.click();
        pause(4000);
        return new AddContact_page(driver);
    }


    public boolean is_current_page() {

        return
                driver.getCurrentUrl().contains("/login");


    }



}


