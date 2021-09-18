package com.example.samplecodedump;

public class StringHolder {
    private final String top;
    private final String middle;
    private final String bot;

    public StringHolder(String top, String middle, String bot) {
        this.top = top;
        this.middle = middle;
        this.bot = bot;
    }

    public String getTop() {
        return top;
    }

    public String getMiddle() {
        return middle;
    }

    public String getBot() {
        return bot;
    }
}
