package uk.me.imprison.social;

import java.util.regex.Matcher;

import static java.util.regex.Pattern.compile;
import static java.util.regex.Pattern.matches;

public class CommandDispatcher {

    private static final String POST_PATTERN = "(.+?) -> (.+)";
    private static final String READ_PATTERN = "(.+?)";
    private static final String FOLLOW_PATTERN = "(.+?) follows (\\w+)";
    private static final String WALL_PATTERN = "(.+?) wall";

    private final Social social;
    private ApplicationClock clock;

    public CommandDispatcher(final Social social, final ApplicationClock clock) {
        this.social = social;
        this.clock = clock;
    }

    public void execute(final String command) {

        if (matches(POST_PATTERN, command)) {
            Matcher matcher = compile(POST_PATTERN).matcher(command);
            matcher.matches();
            UserName userName = UserName.fromString(matcher.group(1));
            social.post(new Message(userName, matcher.group(2), clock.now()));
            return;
        }

        if (matches(FOLLOW_PATTERN, command)) {
            Matcher matcher = compile(FOLLOW_PATTERN).matcher(command);
            matcher.matches();
            UserName follower = UserName.fromString(matcher.group(1));
            UserName followee = UserName.fromString(matcher.group(2));
            social.follow(follower, followee);
            return;
        }

        if (matches(WALL_PATTERN, command)) {
            Matcher matcher = compile(WALL_PATTERN).matcher(command);
            matcher.matches();
            UserName userName = UserName.fromString(matcher.group(1));
            social.showWallFor(userName, clock.now());
            return;
        }

        if (matches(READ_PATTERN, command)) {
            Matcher matcher = compile(READ_PATTERN).matcher(command);
            matcher.matches();
            UserName userName = UserName.fromString(matcher.group(1));
            social.showTimelineFor(userName, clock.now());
            return;
        }

    }
}
