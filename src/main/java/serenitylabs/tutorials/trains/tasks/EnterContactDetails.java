package serenitylabs.tutorials.trains.tasks;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import serenitylabs.tutorials.trains.ui.ContactForm;

public class EnterContactDetails {
    public static Performable forCustomer(String title, String firstname, String lastname) {
        return Task.where("{0} enters details  for " + title + " " +lastname ,
                SelectFromOptions.byVisibleText(title).from(ContactForm.TITLE),
                Enter.theValue(firstname).into(ContactForm.FIRST_NAME),
                Enter.theValue(lastname).into(ContactForm.LAST_NAME)
        );
    }
}
