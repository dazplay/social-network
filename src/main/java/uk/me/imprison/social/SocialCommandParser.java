package uk.me.imprison.social;

import java.util.Scanner;

public class SocialCommandParser implements CommandParser {
    private static final String UPTO_SPACE = "\\s";
    private static final String POSTING = "->";

    private final Social social;

    public SocialCommandParser(final Social social) {
        this.social = social;
    }

    @Override public void parse(final String line) {
        Scanner scanner = new Scanner(line).useDelimiter(UPTO_SPACE);
        UserName userName = UserName.fromString(scanner.next());

        if (!scanner.hasNext()) {
            social.showTimelineFor(userName);
            return;
        }

        String actionToken = scanner.next();
        if (actionToken.equals(POSTING)) {
            Message message = new Message(userName, scanner.nextLine().trim());
            social.post(message);
            return;
        }
    }
}
