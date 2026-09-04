import object_model.User_model;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
public class test_Registration_Negative extends TestBase {
    @BeforeMethod(alwaysRun = true)
    public void logout() {

        HelperUser user = new HelperUser(app.wd);
        if (user.user_logged()) {
            user.loguot();
            user.pause(10);
            Assert.assertTrue(user.isElementPresent(By.xpath("//a[@href='/login']")));
        }


    }

    @BeforeMethod
    public void refreshPage() {
        app.wd.navigate().refresh();
    }

    @Test(dataProvider = "data_registration", dataProviderClass = Data_Provider.class)
    public void testRegistration(String type, User_model user, String expectedMessage) {
        HomePageScreen home = new HomePageScreen(app.wd);
        if (!type.startsWith("negative"))
            return;
        home.go_to_login_page().login(user.getEmail(), user.getPassword());
        Assert.assertTrue(new LoginRegistrationPage(app.wd).is_allert_present(expectedMessage));
        Assert.assertTrue(new LoginRegistrationPage(app.wd).is_current_page());
    }


}