package com.example._190503021_mithat_can.BaseClass;


public class KeyValuePair {
    private final String key;
    private final String value;
    public KeyValuePair(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey()   {    return key;    }

    @Override
    public String toString() {
        return this.value;
    }
}
