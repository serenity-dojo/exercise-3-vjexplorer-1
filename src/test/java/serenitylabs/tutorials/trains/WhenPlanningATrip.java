package serenitylabs.tutorials.trains;
import net.serenitybdd.annotations.Managed;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import net.serenitybdd.screenplay.questions.page.TheWebPage;
import net.serenitybdd.screenplay.questions.targets.TheTarget;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import serenitylabs.tutorials.trains.ui.*;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.drawTheCurtain;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.*;

@ExtendWith(SerenityJUnit5Extension.class)
public class WhenPlanningATrip {

    @Managed
    WebDriver browser;
    Actor carrie = Actor.named("Carrie");

    @BeforeEach
    public void setTheStage() {
        carrie.can(BrowseTheWeb.with(browser));
    }

    @Test
    public void the_TFL_page_title_should_be_visible() {
        carrie.attemptsTo(Open.browserOn().the(TFLHomePage.class));
        carrie.should(seeThat(TheWebPage.title(), containsString("Transport for London")));
    }

    @Test
    public void status_update_page_should_be_visible() {
        carrie.attemptsTo((Open.url("https://tfl.gov.uk/tube-dlr-overground/status/")));
        carrie.should(seeThat(TheWebPage.title(), containsString("status update")));
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
    public void should_list_all_relevant_station_information() {
        carrie.attemptsTo(Open.browserOn().the(TFLHomePage.class),
                Click.on(CookiesDialog.ACCEPT_ALL_COOKIES),
                Enter.theValue("Jubilee").into(TFLHomePage.SEARCH).thenHit(Keys.ENTER));

        carrie.should(seeThat(TheTarget.textOf(TFLSearchResultsPage.FIRST_ARTICLE_HEADING), containsString("Jubilee")));
    }

    @Test
    public void should_see_status_updates() {
        carrie.attemptsTo(Open.browserOn().the(TFLHomePage.class),
                Click.on(CookiesDialog.ACCEPT_ALL_COOKIES),
                Click.on(MenuBar.STATUS_UPDATES.menuOption())
        );
        carrie.should(seeThat(TheTarget.textValuesOf(ServiceUpdatePage.SERVICE_LINES)
                , hasItems("Bakerloo", "Circle", "Central")));
    }

    @Test
    public void should_be_able_to_contact_tfl() {
        carrie.attemptsTo(Open.browserOn().the(TFLHomePage.class),
                Click.on(CookiesDialog.ACCEPT_ALL_COOKIES),
                Click.on(MenuBar.HELP_AND_CONTACTS.menuOption()),
                Click.on(ContactForm.CONTACT_US_ABOUT_OYSTER)
        );

        carrie.attemptsTo(SelectFromOptions.byVisibleText("Mrs").from(ContactForm.TITLE),
                Enter.theValue("Sarah-Jane").into(ContactForm.FIRST_NAME),
                Enter.theValue("Smith").into((ContactForm.LAST_NAME)));

        carrie.should(
                seeThat(TheTarget.selectedValueOf(ContactForm.TITLE), equalTo("Mrs")),
                seeThat(TheTarget.valueOf(ContactForm.FIRST_NAME), equalTo("Sarah-Jane")),
                seeThat(TheTarget.valueOf(ContactForm.LAST_NAME), equalTo("Smith"))
        );
    }

}
