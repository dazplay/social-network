package uk.me.imprison.social;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertThat;

public class InMemorySocialNetworkTest {

    private final UserName bob = UserName.fromString("Bob");
    private final UserName sophie = UserName.fromString("Sophie");

    private final SocialNetwork network = new InMemorySocialNetwork();

    @Test public void followeesForReturnsAllUsersWhoHaveBeenFollowedByGivenUser() {
        network.follows(bob, sophie);
        assertThat(network.followeesFor(bob), hasItem(sophie));
    }

    @Test public void followingIsNotImplicitelyReciprocal() {
        network.follows(bob, sophie);
        assertThat(network.followeesFor(sophie), not(hasItem(bob)));
    }
}