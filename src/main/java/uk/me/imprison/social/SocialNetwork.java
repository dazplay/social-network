package uk.me.imprison.social;

import java.util.Collection;

public interface SocialNetwork {
    Collection<UserName> followeesFor(UserName userName);

    void follows(UserName follower, UserName followee);
}
