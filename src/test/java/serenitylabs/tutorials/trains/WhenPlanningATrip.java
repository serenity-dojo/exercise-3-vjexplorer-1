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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import serenitylabs.tutorials.trains.questions.TheSearchResults;
import serenitylabs.tutorials.trains.tasks.EnterContactDetails;
import serenitylabs.tutorials.trains.tasks.Search;
import serenitylabs.tutorials.trains.ui.*;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.*;

@ExtendWith(SerenityJUnit5Extension.class)
public class WhenPlanningATrip {

    @Managed
    WebDriver browser;
    Actor carrie = Actor.named("Carrie");

    @BeforeEach
    public void setTheStage() {
        carrie.can(BrowseTheWeb.with(browser));
        carrie.attemptsTo(Open.browserOn().the(TFLHomePage.class));
    }

    @Test
    public void the_TFL_page_title_should_be_visible() {
        carrie.should(seeThat(TheWebPage.title(), containsString("Transport for London")));
    }

    @Test
    public void status_update_page_should_be_visible() {
        carrie.attemptsTo(Click.on(CookiesDialog.ACCEPT_ALL_COOKIES),
                Click.on(MenuBar.STATUS_UPDATES.menuOption())
        );
        carrie.should(seeThat(TheWebPage.title(), containsString("status update")));
    }

    @Test
    public void should_be_able_to_search_for_station_details() {
        carrie.attemptsTo(Click.on(CookiesDialog.ACCEPT_ALL_COOKIES),
                Search.forStation("Waterloo"));

        carrie.should(seeThat("The search results heading",
                TheSearchResults.heading(),equalTo("Search: Waterloo")
        ));
    }

    @Test
    public void should_list_all_relevant_station_information() {
        carrie.attemptsTo(Click.on(CookiesDialog.ACCEPT_ALL_COOKIES),
                Enter.theValue("Jubilee").into(TFLHomePage.SEARCH).thenHit(Keys.ENTER));

        carrie.should(seeThat(TheSearchResults.firstArticleTitle(), containsString("Jubilee")));
    }

    @Test
    public void should_see_status_updates() {
        carrie.attemptsTo(Click.on(CookiesDialog.ACCEPT_ALL_COOKIES),
                Click.on(MenuBar.STATUS_UPDATES.menuOption())
        );
        carrie.should(seeThat(TheTarget.textValuesOf(ServiceUpdatePage.SERVICE_LINES)
                , hasItems("Bakerloo", "Circle", "Central")));
    }

    @Test
    public void should_be_able_to_contact_tfl() {
        carrie.attemptsTo(
                Click.on(CookiesDialog.ACCEPT_ALL_COOKIES),
                Click.on(MenuBar.HELP_AND_CONTACTS.menuOption()),
                Click.on(ContactForm.CONTACT_US_ABOUT_OYSTER)
        );

        carrie.attemptsTo(EnterContactDetails.forCustomer("Mrs", "Sarah-Jane","Smith"));
        carrie.should(
                seeThat(TheTarget.selectedValueOf(ContactForm.TITLE), equalTo("Mrs")),
                seeThat(TheTarget.valueOf(ContactForm.FIRST_NAME), equalTo("Sarah-Jane")),
                seeThat(TheTarget.valueOf(ContactForm.LAST_NAME), equalTo("Smith"))
        );
    }

}
