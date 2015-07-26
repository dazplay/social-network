Social-Network exercise for Codurance.

A maven app, so can be run using:
```shell
mvn compile exec:java
```

##Commands syntax:

I/O is via console.

- `posting: <user name> -> <message>`
- `reading: <user name>`
- `following: <user name> follows <another user>`
- `wall: <user name> wall`

##Examples: 

Posting: Alice can publish messages to a personal timeline:
```bash
> Alice -> I love the weather today
> Bob -> Damn! We lost!
> Bob -> Good game though.
Reading: I can view Alice and Bob’s timelines
> Alice
I love the weather today (5 minutes ago)
> Bob
Good game though. (1 minute ago)
Damn! We lost! (2 minutes ago)
```

Following: Charlie can subscribe to Alice’s and Bob’s timelines, and view an aggregated list of all subscriptions
```bash
> Charlie -> I'm in New York today! Anyone want to have a coffee?
> Charlie follows Alice
> Charlie wall
Charlie - I'm in New York today! Anyone want to have a coffee? (2 seconds ago)
Alice - I love the weather today (5 minutes ago)
> Charlie follows Bob
> Charlie wall
Charlie - I'm in New York today! Anyone wants to have a coffee? (15 seconds ago)
Bob - Good game though. (1 minute ago)
Bob - Damn! We lost! (2 minutes ago)
Alice - I love the weather today (5 minutes ago)
```