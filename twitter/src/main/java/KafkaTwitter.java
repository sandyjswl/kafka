import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.state.KeyValueStore;

import java.time.Duration;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

public class KafkaTwitter {

    public static void main(final String[] args) throws Exception {
//        final Properties props = new Properties();
//        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "kafka-twitter");
//        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//        props.put(StreamsConfig.CACHE_MAX_BYTES_BUFFERING_CONFIG, 0);
//        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
//        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
//
//        // setting offset reset to earliest so that we can re-run the demo code with the same pre-loaded data
//        // Note: To re-run the demo, you need to use the offset reset tool:
//        // https://cwiki.apache.org/confluence/display/KAFKA/Kafka+Streams+Application+Reset+Tool
//        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//
//        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");



        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("acks", "all");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//        System.setProperty("java.net.useSystemProxies", "true");

        Producer<String, String> producer = new KafkaProducer<>(props);
        while(true){
            Map<Long, String> timeLine = Application.getTimeLine();
            timeLine.forEach((k,v)-> producer.send(new ProducerRecord<String, String>("twitter-input",k.toString(),v)));
        }

//        for (int i = 0; i < 100; i++)
//            producer.send(new ProducerRecord<String, String>("twitter-input", Integer.toString(i), Integer.toString(i)));

// consumer




//        final StreamsBuilder builder = new StreamsBuilder();
//
//        final KStream<String, String> source = builder.stream("twitter-input");
//
//        final KTable<String, Long> counts = source
//                .flatMapValues(value -> Arrays.asList(value.toLowerCase(Locale.getDefault()).split(" ")))
//                .groupBy((key, value) -> value)
//                .count();
//
//
//        // need to override value serde to Long type
//        counts.toStream().to("twitter-output", Produced.with(Serdes.String(), Serdes.Long()));




//        final KafkaStreams streams = new KafkaStreams(builder.build(), props);
//        final CountDownLatch latch = new CountDownLatch(1);
//
//        // attach shutdown handler to catch control-c
//        Runtime.getRuntime().addShutdownHook(new Thread("streams-wordcount-shutdown-hook") {
//            @Override
//            public void run() {
//                streams.close();
//                latch.countDown();
//            }
//        });
//
//        try {
//            streams.start();
//            latch.await();
//        } catch (final Throwable e) {
//            System.exit(1);
//        }
//        System.exit(0);
    }

}