package serenitylabs.tutorials.trains;
import net.serenitybdd.annotations.Managed;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.questions.page.TheWebPage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.containsString;

@ExtendWith(SerenityJUnit5Extension.class)
public class WhenPlanningATrip {

    @Managed
    WebDriver browser;
    Actor carrie = Actor.named("Carrie");

    @Test
    public void the_TFL_page_title_should_be_visible(){
        carrie.can(BrowseTheWeb.with(browser));
        carrie.attemptsTo(Open.url("https://tfl.gov.uk"));
        carrie.should(seeThat(TheWebPage.title(),containsString("Transport for London")));
    }

}
