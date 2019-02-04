package com.example.storeServerState.db.manager;

import com.example.storeServerState.db.entity.MemInfo;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class StoreMemInfoManager {

    public EntityManager em = Persistence
            .createEntityManagerFactory("memoryInfo").createEntityManager();

    public MemInfo add(MemInfo info){
        em.getTransaction().begin();
        MemInfo storedInfo = em.merge(info);
        em.getTransaction().commit();
        return storedInfo;
    }

    public void delete(long id){
        em.getTransaction().begin();
        em.remove(get(id));
        em.getTransaction().commit();
    }

    public MemInfo get(long id){
       return  em.find(MemInfo.class,id);
    }
}
