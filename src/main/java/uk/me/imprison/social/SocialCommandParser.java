package uk.me.imprison.social;

import java.util.Scanner;

public class SocialCommandParser implements CommandParser {
    private static final String UPTO_SPACE = "\\s";
    private static final String POSTING = "->";
    private static final String FOLLOWING = "follows";
    private static final String WALL = "wall";

    private final Social social;
    private ApplicationClock clock;

    public SocialCommandParser(final Social social, final ApplicationClock clock) {
        this.social = social;
        this.clock = clock;
    }

    @Override public void parse(final String line) {
        Scanner scanner = new Scanner(line).useDelimiter(UPTO_SPACE);
        UserName userName = UserName.fromString(scanner.next());

        if (!scanner.hasNext()) {
            social.showTimelineFor(userName, clock.now());
            return;
        }

        String actionToken = scanner.next();
        if (actionToken.equals(POSTING)) {
            Message message = new Message(userName, scanner.nextLine().trim(), clock.now());
            social.post(message);
            return;
        }

        if (actionToken.equals(FOLLOWING)) {
            UserName followee = UserName.fromString(scanner.next());
            social.follow(userName, followee);
            return;
        }
        if (actionToken.equals(WALL)) {
            social.showWallFor(userName, clock.now());
        }
    }
}
