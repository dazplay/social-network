package uk.me.imprison.social;

public interface Console {
    void println(String content);

    boolean hasCommand();

    String readCommand();
}
