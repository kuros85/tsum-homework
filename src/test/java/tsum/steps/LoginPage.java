package tsum.steps;

import net.thucydides.core.annotations.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

    //elements

    By acceptRegionLocator = By.cssSelector(".ui-button_theme_quite-black");
    By usernameLocator = By.cssSelector(".login-input");
    By passwordLocator = By.cssSelector(".pwd-input");
    By loginButtonLocator = By.cssSelector(".ui-button_large");
    By wrongPasswordNotification = By.cssSelector("div.notice");

    By forgotPasswordLink = By.cssSelector(".forget-pwd");
    By forgotPasswordForm = By.xpath("//password-forgot/form/div[2]/input");
    By forgotPasswordFormSubmitButton = By.cssSelector(".ui-button");
    By chooseRegistration = By.cssSelector("p.auth-layout__control-button:nth-child(2)");


    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Нажать кнопку Подтвердить")
    public void acceptRegion() {
        driver.findElement(acceptRegionLocator).click();
    }

    @Step("Ввести логин пользователя")
    public void typeUsername(String username) {
        driver.findElement(usernameLocator).sendKeys(username);
    }

    @Step("Ввести пароль пользователя")
    public void typePassword(String password) {
        driver.findElement(passwordLocator).sendKeys(password);
    }

    @Step("Нажать кнопку Войти")
    public void submitLogin() {
        driver.findElement(loginButtonLocator).submit();
    }

    @Step("Почитать нотификацию")
    public String checkNotificationText() {
        return driver.findElement(wrongPasswordNotification).getText();
    }

    @Step("Проконтролировать доступность кнопки Войти")
    public WebElement checkButton() {
        return driver.findElement(loginButtonLocator);
    }

    @Step("Нажать на ссылку Забыть пароль")
    public void forgotPassword() {
        driver.findElement(forgotPasswordLink).click();
    }

    @Step("Ввести почту и попросить выслать пароль")
    public void provideEmailForPasswordRestoration(String mail) {
        driver.findElement(forgotPasswordForm).sendKeys(mail);
        driver.findElement(forgotPasswordFormSubmitButton).click();
    }

    @Step("Перейти на вкладку Регистрация")
    public void chooseRegistration() {
        driver.findElement(chooseRegistration).click();
    }
}
