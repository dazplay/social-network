package uk.me.imprison.social;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;

import java.time.LocalDateTime;

public class Message {
    private final UserName userName;
    private final String content;
    private LocalDateTime timestamp;

    public Message(UserName userName, String content, LocalDateTime timestamp) {
        this.userName = userName;
        this.content = content;
        this.timestamp = timestamp;
    }

    public String content() {
        return content;
    }

    public LocalDateTime timestamp() {
        return timestamp;
    }

    @Override public String toString() {
        return userName + "->" + content;
    }

    @Override public boolean equals(Object that) {
        return EqualsBuilder.reflectionEquals(this, that);
    }

    @Override public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    public static MessageBuilder message() {
        return new MessageBuilder();
    }

    public static class MessageBuilder {

        private String content;
        private UserName userName;
        private LocalDateTime timestamp;

        public MessageBuilder by(String name) {
            return by(UserName.fromString(name));
        }

        public MessageBuilder by(UserName userName) {
            this.userName = userName;
            return this;
        }

        public MessageBuilder withContent(String content) {
            this.content = content;
            return this;
        }

        public MessageBuilder timestamped(LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Message build() {
            return new Message(userName, content, timestamp);
        }
    }

    public static Matcher<Message> hasUserName(Matcher<UserName> userNameMatcher) {
        return new FeatureMatcher<Message, UserName>(userNameMatcher, "userName", "userName") {
            @Override protected UserName featureValueOf(Message message) {
                return message.userName;
            }
        };
    }
}
