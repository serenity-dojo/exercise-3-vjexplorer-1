package serenitylabs.tutorials.trains.ui;

import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.pages.PageObject;


import java.util.List;

import static net.serenitybdd.screenplay.targets.Target.*;

public class TFLSearchResultsPage extends PageObject {
    public static final Target SEARCH_RESULTS_HEADING =
            the("Search Results Heading").locatedBy(".hero-headline");

    public static final Target FIRST_ARTICLE_HEADING = Target.the("First Article Heading").locatedBy("(//h2[@class='h3'])[1]");


}
