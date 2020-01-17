package tsum.cases;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Narrative;
import net.thucydides.core.annotations.Steps;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import tsum.Browser;
import tsum.steps.LoginPage;
import static tsum.Credentials.host;
import java.util.concurrent.TimeUnit;

@RunWith(SerenityRunner.class)
@Narrative(text={"Maths is important."})
public class login {

    Browser browserSetup = new Browser();
    private WebDriver driver = browserSetup.Firefox();

    @Steps
    LoginPage loginPage;

    {
        loginPage = new LoginPage(driver);
    }

    public login() throws Exception {
    }

    @Test
    public void LogInWrongCredentials() throws Exception {
        //going to main page

        driver.get(host + "/login/");
        loginPage.acceptRegion();

        //inserting credentials
        loginPage.typeUsername("moscow2009@gmail.com");
        loginPage.typePassword("Неверный");
        loginPage.submitLogin();
        TimeUnit.SECONDS.sleep(2);

        //Checking notification
        Assert.assertTrue(loginPage.checkNotificationText().contains("Неверный логин или пароль"));
    }

    @Test
    public void LogInNoPassword() throws Exception {
        //going to main page
        driver.get(host + "/login/");
        loginPage.acceptRegion();

        //inserting credentials
        loginPage.typeUsername("moscow2009@gmail.com");
        loginPage.submitLogin();
        TimeUnit.SECONDS.sleep(2);

        //Checking button
        Assert.assertFalse(loginPage.checkButton().isEnabled());
    }

    @Test
    public void ForgotPassword() throws Exception {
        //going to main page
        driver.get(host + "/login/");
        loginPage.acceptRegion();

        //forgot password
        loginPage.forgotPassword();

        //check if button worked
        Assert.assertEquals("https://www.tsum.ru/password/forgot/", driver.getCurrentUrl());

        //submit email
        loginPage.provideEmailForPasswordRestoration("moscow2009@gmail.com");
    }

    @Test
    public void ChooseRegistration() throws Exception {
        //going to main page
        driver.get(host + "/login/");
        loginPage.acceptRegion();

        //forgot password
        loginPage.chooseRegistration();

        //check if button worked
        Assert.assertEquals("https://www.tsum.ru/registration/", driver.getCurrentUrl());
    }

    @After
    public void TearDown() throws Exception{
        driver.close();
    };
}