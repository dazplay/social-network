package uk.me.imprison.social;

import java.util.Collection;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;

public class InMemorySocialNetwork implements SocialNetwork {
    private final SetMultimap<UserName, UserName> followees = HashMultimap.create();

    @Override public Collection<UserName> followeesFor(final UserName userName) {
        return followees.get(userName);
    }

    @Override public void follows(final UserName follower, final UserName followee) {
        followees.put(follower, followee);
    }
}
