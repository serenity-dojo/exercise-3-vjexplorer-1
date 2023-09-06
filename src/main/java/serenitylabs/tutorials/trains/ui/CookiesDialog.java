package serenitylabs.tutorials.trains.ui;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;

public class CookiesDialog extends PageObject {
    public static final By ACCEPT_ALL_COOKIES = By.xpath("//button[.='Accept all cookies']");
    public static final By DONE =By.xpath("//button[.='Done']");
}
