package com.example.storeServerState.kafka;

import com.example.storeServerState.configuration.PropertyLoader;
import com.example.storeServerState.exception.ErrorCodes;
import com.example.storeServerState.exception.KafkaConnectionException;
import com.example.storeServerState.exception.PropertyLoadException;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

public class ServerStateConsumer {

    private static ServerStateConsumer producerInstance;
    private static KafkaConsumer<String, String> consumer;
    private static Properties kafkaProperties;


    private ServerStateConsumer() throws KafkaConnectionException {
        if(producerInstance != null){
            throw new RuntimeException("Use getInstance() method to get the single instance of this class.");
        }
        init();
    }

    /**
     *
     * @return single instance or null
     */
    public static ServerStateConsumer getInstance()  throws KafkaConnectionException {
        //add check for null to avoid threads to wait for synchronized block
        if (producerInstance == null) {
            //THREAD1 is executing sync block and thread2 is waiting
            synchronized (ServerStateConsumer.class) {
                if (producerInstance == null) { //when thread 1 is leaving thread 2 is checking for null
                    producerInstance = new ServerStateConsumer();
                }
            }
        }
        return producerInstance;
    }

    public KafkaConsumer<String, String> consume(){
       consumer.subscribe(Arrays.asList(kafkaProperties.getProperty("read.topic")));
       return consumer;
    }

    private void init() throws KafkaConnectionException {
        try {
            kafkaProperties = PropertyLoader.loadKafkaProperties();
            consumer = new KafkaConsumer<String, String>(kafkaProperties);
        }catch (PropertyLoadException e){
            throw new KafkaConnectionException(ErrorCodes.NOT_CONNECTED, e.getMessage());
        }
    }

}
