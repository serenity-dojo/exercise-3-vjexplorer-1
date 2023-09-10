package serenitylabs.tutorials.trains.ui;

import net.serenitybdd.annotations.DefaultUrl;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.pages.PageObject;


@DefaultUrl("https://tfl.gov.uk/")
public class TFLHomePage extends PageObject {
    public static final Target SEARCH= Target.the("Search Field").locatedBy("#q");
}
