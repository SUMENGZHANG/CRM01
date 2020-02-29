package org.example.crm.enums;

public enum DevResult {
    UNDEV(0),
    DEVING(1),
    DEV_SUCCESS(2),
    DEV_FAILED(3);


    private  int type;

    DevResult(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
