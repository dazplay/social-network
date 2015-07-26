package uk.me.imprison.social;

public interface Social {
    void showTimelineFor(UserName userName);

    void post(Message message);
}
