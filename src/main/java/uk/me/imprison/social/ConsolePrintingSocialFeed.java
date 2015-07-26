package uk.me.imprison.social;

import java.util.List;

public class ConsolePrintingSocialFeed implements SocialFeed {
    private ConsoleOutput out;

    public ConsolePrintingSocialFeed(ConsoleOutput out) {
        this.out = out;
    }

    @Override public void showTimeLineWith(List<Message> posts) {
        for (Message post : posts) {
            out.println(post.content());
        }
    }
}
