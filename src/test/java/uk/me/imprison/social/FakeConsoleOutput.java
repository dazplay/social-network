package uk.me.imprison.social;

import java.util.List;

import com.google.common.collect.Lists;

public class FakeConsoleOutput implements ConsoleOutput {
    private final List<String> lines = Lists.newArrayList();

    public List<String> lines() {
        return lines;
    }

    @Override public void println(final String content) {
        lines.add(content);
    }
}
