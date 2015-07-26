package uk.me.imprison.social;

import java.util.List;

public interface PostsStore {
    List<Message> getPostsBelongingTo(UserName userName);

    void add(Message message);
}
