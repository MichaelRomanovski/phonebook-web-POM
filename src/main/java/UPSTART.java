import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.EventListener;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

@Slf4j
public class UPSTART {

    WebDriver wd;
    Properties properties;

    public void startapp() throws IOException {

        System.setProperty("webdriver.chrome.driver",
                "C:\\my projects\\chromedriver-win64\\chromedriver.exe");

        properties = new Properties();

        InputStream input = getClass()
                .getClassLoader()
                .getResourceAsStream("prod.properties");

        if (input == null) {
            throw new RuntimeException("config.properties not found in resources");
        }

        properties.load(new InputStreamReader(input, StandardCharsets.UTF_8));

        wd = new ChromeDriver();

        WDlistener listener = new WDlistener();
        wd = new EventFiringDecorator<>(listener).decorate(wd);

        wd.navigate().to("https://telranedu.web.app/home");
        wd.manage().window().maximize();
        wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    public void close_browser() {
        wd.quit();
    }

    protected String get_email() {
        return properties.getProperty("web.email");
    }

    protected String get_password() {
        return properties.getProperty("web.password");
    }
}




