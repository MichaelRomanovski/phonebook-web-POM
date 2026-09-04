import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.WebDriverListener;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.logging.Logger;

@Slf4j
public class WDlistener implements WebDriverListener {
    public WDlistener() {

    }


    @Override
    public void beforeClick(WebElement element) {
   log.info("Clicking on: " + element);
    }

    @Override
    public void afterClick(WebElement element) {
      log.info("Clicked on: " + element);
    }


    @Override
    public void onError(Object target, Method method, Object[] args, InvocationTargetException e) {

        Throwable error = e.getTargetException();

        log.error("❌ ERROR occurred during method: {}", method.getName());
        log.error("➡️ Arguments: {}", Arrays.toString(args));
        log.error("📝 Exception message: {}", error.getMessage());

        if (target instanceof WebDriver) {
            WebDriver driver = (WebDriver) target;


            try {
                String fileName = "screenshots/error_" + System.currentTimeMillis() + ".png";
                File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

                Files.createDirectories(Paths.get("screenshots"));
                Files.copy(src.toPath(), Paths.get(fileName));

                log.error("📸 Screenshot saved: {}", fileName);

            } catch (IOException ex) {
                log.error("⚠️ Failed to save screenshot", ex);
            }
        } else {
            log.warn("⚠️ Target is not WebDriver, screenshot skipped");
        }
    }



}

