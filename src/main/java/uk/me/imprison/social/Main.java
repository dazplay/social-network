package uk.me.imprison.social;


import static java.time.Clock.systemUTC;
import static java.time.LocalDateTime.now;
import static uk.me.imprison.social.ConsoleSocialApplication.createConsoleSocialApplication;

public class Main {
    public static void main(String[] args) {
        ConsoleSocialApplication app =
                createConsoleSocialApplication(new StreamingConsole(System.in, System.out), utcClock());
        app.start();
    }

    private static ApplicationClock utcClock() {
        return () -> now(systemUTC());
    }
}
