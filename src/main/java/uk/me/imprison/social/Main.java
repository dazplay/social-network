package uk.me.imprison.social;


import static uk.me.imprison.social.ConsoleSocialApplication.createConsoleSocialApplication;

public class Main {
    public static void main(String[] args) {
        ConsoleSocialApplication app =
                createConsoleSocialApplication(
                        new StreamingConsoleInput(System.in),
                        new StreamingConsoleOutput(System.out));
        app.start();
    }
}
