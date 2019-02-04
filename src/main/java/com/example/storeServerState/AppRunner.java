package com.example.storeServerState;

import com.example.storeServerState.db.entity.MemInfo;
import com.example.storeServerState.db.manager.StoreMemInfoManager;
import com.example.storeServerState.kafka.ServerStateConsumer;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;

public class AppRunner {
    public static Integer MAX_NO_MESSAGE_FOUND_COUNT=100;
    public static Integer MAX_MILISEC_FOR_WAIT_RECORD=1000;

    public static void main(String[] args) {
          runConsumer();
    }

    private static void runConsumer(){
        try{
            Consumer<String,String> consumer = ServerStateConsumer.getInstance().consume();
            StoreMemInfoManager manager = new StoreMemInfoManager();
            while (true){
                ConsumerRecords<String, String> consumerRecords = consumer.poll(MAX_MILISEC_FOR_WAIT_RECORD);//will wait 1000milisec for record
                consumerRecords.forEach((x)->{
                    MemInfo currentRecord = MemInfo.fromJson(x.value());
                    if(isError(currentRecord))
                    manager.add(currentRecord);
                    System.out.println(x.value());
                    consumer.commitAsync();
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static boolean isError(MemInfo info){
        return (info.getMemFree() * 100)/info.getMemTotal() >=50;
    }
}
