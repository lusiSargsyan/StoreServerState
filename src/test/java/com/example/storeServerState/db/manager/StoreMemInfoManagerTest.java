package com.example.storeServerState.db.manager;

import com.example.storeServerState.db.entity.MemInfo;
import org.junit.Assert;
import org.junit.Test;

public class StoreMemInfoManagerTest {


    @Test
    public void test() throws Exception {

        StoreMemInfoManager manager = new StoreMemInfoManager();
        MemInfo info = new MemInfo.Builder()
                .setBuffers(2000)
                .setCached(222)
                .setMemFree(333)
                .setMemTotal(434523)
                .setSwapFree(3333)
                .setSwapTotal(888)
                .build();

       MemInfo storedInfo = manager.add(info);
       Assert.assertNotNull(storedInfo);

       manager.delete(storedInfo.getId());

       MemInfo deleted = manager.get(storedInfo.getId());
       Assert.assertNull(deleted);

    }


}
