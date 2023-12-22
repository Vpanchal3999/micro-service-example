package com.synoverge.common.enums;

public enum     PaymentMode {

    CASH(1,"CASH"),
    CHEQUE(2,"CHEQUE");

    final int id;
    final String modeType;

    PaymentMode(int id, String modeType) {
        this.id = id;
        this.modeType = modeType;
    }

    public int getId() {
        return id;
    }

    public String getModeType() {
        return modeType;
    }
}
