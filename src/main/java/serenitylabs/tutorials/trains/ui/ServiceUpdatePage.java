package serenitylabs.tutorials.trains.ui;

import net.serenitybdd.screenplay.targets.Target;

public class ServiceUpdatePage {
    public static final Target SERVICE_LINES =
            Target.the("Service Lines").locatedBy(".service-name span");
}
