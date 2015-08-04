package uk.me.imprison.social;

import org.junit.Test;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static uk.me.imprison.social.Message.message;

public class InMemoryPostsStoreTest {
    private final InMemoryPostsStore store = new InMemoryPostsStore();

    private final UserName bob = UserName.fromString("Bob");
    private final UserName sophie = UserName.fromString("Sophie");
    private final Message bobsMessage = message().by(bob).build();
    private final Message anotherMessageOfBobs = message().by(bob).build();
    private final Message sophiesMessage = message().by(sophie).build();

    @Test public void returnsBothBobAndSophieesPosts() {
        store.add(bobsMessage);
        store.add(anotherMessageOfBobs);
        store.add(sophiesMessage);

        assertThat(store.getPostsBelongingTo(asList(bob, sophie)), contains(bobsMessage, anotherMessageOfBobs, sophiesMessage));
    }

    @Test public void onlyReturnsBobsPosts() {
        store.add(bobsMessage);
        store.add(anotherMessageOfBobs);
        store.add(sophiesMessage);

        assertThat(store.getPostsBelongingTo(bob), containsInAnyOrder(bobsMessage, anotherMessageOfBobs));
    }

}