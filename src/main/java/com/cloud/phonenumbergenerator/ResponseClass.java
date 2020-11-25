package com.cloud.phonenumbergenerator;

public class ResponseClass {

    private String phoneNumber;
    private int responseCode;
    private boolean isRandom;

    public ResponseClass(String phoneNumber, int responseCode, boolean isRandom) {
        this.phoneNumber = phoneNumber;
        this.responseCode = responseCode;
        this.isRandom = isRandom;

    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

}
