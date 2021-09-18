package com.example.samplecodedump;

// just a test class for holding strings (i know i could have just used an arraylist)
public class StringHolder {
    private final String first;
    private final String second;
    private final String third;

    public StringHolder(String first, String second, String third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public String getFirst() {
        return first;
    }

    public String getSecond() {
        return second;
    }

    public String getThird() {
        return third;
    }
}
