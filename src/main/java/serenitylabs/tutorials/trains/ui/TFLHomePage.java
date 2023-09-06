package serenitylabs.tutorials.trains.ui;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.annotations.DefaultUrl;

@DefaultUrl("https://tfl.gov.uk")
public class TFLHomePage extends PageObject {
    public static final Target SEARCH = Target.the("Search field").locatedBy("#q");
}
