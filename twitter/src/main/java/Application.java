import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Application {

    public static Twitter getTwitterinstance() {
        /**
         * if not using properties file, we can set access token by following way
         */
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("MjQPhsZCqEHd1n5jVI7RwH8kk")
                .setOAuthConsumerSecret("OwiPsYBCidGhcnTpIiEBMu0YjqQdiyfpRJ5TDOdvh3m4Vn25tG")
                .setOAuthAccessToken("202195387-P3KkGgx81nBeQZTZy9acCfmYJXHkSQqb3wjZvIWf")
                .setOAuthAccessTokenSecret("LwPGuRYAE2ZEnRj6OFjW0bV3vWSsZM219IBcZppZ86MBb");
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();

//        Twitter twitter = TwitterFactory.getSingleton();
        return twitter;

    }

    public static String createTweet(String tweet) throws TwitterException {
        Twitter twitter = getTwitterinstance();
        Status status = twitter.updateStatus("creating baeldung API");
        return status.getText();
    }

    public static Map<Long, String> getTimeLine() throws TwitterException {
        Twitter twitter = getTwitterinstance();
        List<Status> statuses = twitter.getHomeTimeline();

        return statuses.stream().collect(Collectors.toMap(status -> status.getId(), Status::getText));

    }

    public static String sendDirectMessage(String recipientName, String msg) throws TwitterException {
        Twitter twitter = getTwitterinstance();
        DirectMessage message = twitter.sendDirectMessage(recipientName, msg);
        return message.getText();
    }

    public static List<String> searchtweets() throws TwitterException {
        Twitter twitter = getTwitterinstance();
        Query query = new Query("source:twitter4j baeldung");
        QueryResult result = twitter.search(query);
        List<Status> statuses = result.getTweets();
        return statuses.stream().map(
                item -> item.getText()).collect(
                Collectors.toList());
    }

    public static void main(String[] args) throws TwitterException {
        Map<Long, String> timeLine = getTimeLine();
        timeLine.forEach((username, tweet) -> {
                    System.out.println(username + " : " + tweet);
                    System.out.println("---------------------------------");

                }
        );
    }
}