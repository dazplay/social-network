package uk.me.imprison.social;

import java.util.Collection;
import java.util.List;

public interface PostsStore {
    List<Message> getPostsBelongingTo(UserName userName);

    List<Message> getPostsBelongingTo(Collection<UserName> userNames);

    void add(Message message);
}
