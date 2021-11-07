package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Тогда;
import org.junit.Assert;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

public class Case1 {

    public static ChromeDriver driver;

    @Before
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "C://Program Files//Google//Chrome//chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }
    @After
    public static void tearDown() {
        driver.quit();
        //close();
    }

    @Дано("Вход в БСЛ через (.*) с паролем (.*)")
    public void вход_в_БСЛ_через_с_паролем(String login, String password) {
        driver.get("http://bsl.kz00c1.kz.infra/bsl/home?page-crypt=EDQECfEn2Ko&tabId=qPqGVQ9XCCLsUUfhLIUU");
        driver.findElementById("IDToken1").sendKeys(login);
        driver.findElementById("IDToken2").sendKeys(password);
        driver.findElementById("kc-login").click();
    }

    @Тогда("Нажать на создать банк")
    public void нажать_на_создать_банк() {
        driver.findElementByXPath("/html/body/div[2]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/a/div").click();
    }

    @Дано("Создать банк")
    public void создать_банк() {
        driver.findElementById("id30").sendKeys("IsItBank");
        driver.findElementById("id31").sendKeys("143");
        driver.findElementById("id32").sendKeys("AO 'Is It Bank'");
        final Select selectBox = new Select(driver.findElementById("id33"));
        selectBox.selectByIndex(1);
        driver.findElementById("id35").sendKeys("12");
        driver.findElementById("id21").click();
    }

    @Тогда("Проверка страницы банка")
    public void проверка_страницы_банка() {
        String bank_code = driver.findElementByXPath("/html/body/div[2]/div[1]/div[1]/div/div[1]/ul[1]/li[2]/span[2]/span").getText();
        Assert.assertNotNull(bank_code);
    }

}
