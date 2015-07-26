package uk.me.imprison.social;

import java.io.InputStream;
import java.util.Iterator;
import java.util.Scanner;

public class StreamingConsoleInput implements ConsoleInput {

    private final InputStream inputStream;

    public StreamingConsoleInput(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override public Iterator<String> iterator() {
        Scanner scanner = new Scanner(inputStream);

        return new Iterator<String>() {
            @Override public boolean hasNext() {
                return scanner.hasNextLine();
            }

            @Override public String next() {
                return scanner.nextLine();
            }
        };
    }
}


