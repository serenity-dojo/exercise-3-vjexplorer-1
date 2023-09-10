package serenitylabs.tutorials.trains;
import net.serenitybdd.annotations.Managed;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.questions.page.TheWebPage;
import net.serenitybdd.screenplay.questions.targets.TheTarget;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import serenitylabs.tutorials.trains.ui.CookiesDialog;
import serenitylabs.tutorials.trains.ui.TFLHomePage;
import serenitylabs.tutorials.trains.ui.TFLSearchResultsPage;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.*;

@ExtendWith(SerenityJUnit5Extension.class)
public class WhenPlanningATrip {

    @Managed
    WebDriver browser;
    Actor carrie = Actor.named("Carrie");

    @BeforeEach
    public void setTheStage(){
        carrie.can(BrowseTheWeb.with(browser));
    }

    @Test
    public void the_TFL_page_title_should_be_visible(){
        carrie.attemptsTo(Open.browserOn().the(TFLHomePage.class));
        carrie.should(seeThat(TheWebPage.title(),containsString("Transport for London")));
    }

    @Test
    public void status_update_page_should_be_visible(){
        carrie.attemptsTo((Open.url("https://tfl.gov.uk/tube-dlr-overground/status/")));
        carrie.should(seeThat(TheWebPage.title(),containsString("status update")));
    }

    @Test
    public void should_be_able_to_search_for_station_details() {
        carrie.attemptsTo(Open.browserOn().the(TFLHomePage.class),
                Click.on(CookiesDialog.ACCEPT_ALL_COOKIES),
                Enter.theValue("Waterloo").into(TFLHomePage.SEARCH).thenHit(Keys.ENTER));

        carrie.should(seeThat
                        (TheTarget.textOf(TFLSearchResultsPage.SEARCH_RESULTS_HEADING),
                        equalTo("Search: Waterloo"))
                    );
    }

    @Test
    public void should_list_all_relevant_station_information(){
        carrie.attemptsTo(Open.browserOn().the(TFLHomePage.class),
            Click.on(CookiesDialog.ACCEPT_ALL_COOKIES),
            Enter.theValue("Jubilee").into(TFLHomePage.SEARCH).thenHit(Keys.ENTER));

        carrie.should(seeThat(TheTarget.textOf(TFLSearchResultsPage.FIRST_ARTICLE_HEADING),containsString("Jubilee")));
    }


}
