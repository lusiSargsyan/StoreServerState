package com.example.storeServerState.db.entity;

import com.google.gson.Gson;

import javax.persistence.*;

@Entity
@Table(name="memoryInfo")
public class MemInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="total")
    private int memTotal;

    @Column(name="free")
    private int memFree;

    private int buffers;
    private int cached;

    @Column(name="virtualTotal")
    private int swapTotal;

    @Column(name="virtualFree")
    private int swapFree;

    public MemInfo() {
    }

    public long getId() {
        return id;
    }

    private MemInfo(Builder builder){
        this.memTotal=builder.memTotal;
        this.memFree=builder.memFree;
        this.buffers = builder.buffers;
        this.cached = builder.cached;
        this.swapTotal = builder.swapTotal;
        this.swapFree = builder.swapFree;
    }

    public int getMemTotal() {
        return memTotal;
    }

    public int getMemFree() {
        return memFree;
    }

    public int getBuffers() {
        return buffers;
    }

    public int getCached() {
        return cached;
    }

    public int getSwapTotal() {
        return swapTotal;
    }

    public int getSwapFree() {
        return swapFree;
    }

    public static class Builder{
        private int memTotal;
        private int memFree;
        private int buffers;
        private int cached;
        private int swapTotal;
        private int swapFree;

        public Builder setMemTotal(int memTotal) {
            this.memTotal = memTotal;
            return this;
        }

        public Builder setMemFree(int memFree) {
            this.memFree = memFree;
            return this;
        }

        public Builder setBuffers(int buffers) {
            this.buffers = buffers;
            return this;
        }

        public Builder setCached(int cached) {
            this.cached = cached;
            return this;
        }

        public Builder setSwapTotal(int swapTotal) {
            this.swapTotal = swapTotal;
            return this;
        }

        public Builder setSwapFree(int swapFree) {
            this.swapFree = swapFree;
            return this;
        }
        public MemInfo build(){
            return new MemInfo(this);
        }
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static MemInfo fromJson(String jsonString) {
        Gson gson = new Gson();
        return gson.fromJson(jsonString,MemInfo.class);
    }
}

