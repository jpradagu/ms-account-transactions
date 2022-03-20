package com.bootcamp.accounttransactions.util.types;

import java.math.BigDecimal;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum TransferType {

    THIRD_TRANSFERS(new BigDecimal(1), "THIRD TRANSFER"),
    SELF_TRANSFER(new BigDecimal(2), "SELF TRANSFER"),
    OTHER_BANKS_TRANSFER(new BigDecimal(3), "OTHER BANKS TRANSFER");

    private BigDecimal transferTypeId;
    private String transferDescription;

    private static final Map<BigDecimal, TransferType> lookup = new HashMap<>();

    static {
        EnumSet.allOf(TransferType.class).forEach(x -> lookup.put(x.transferTypeId, x));
    }

    TransferType(BigDecimal key, String value) {
        this.transferTypeId = key;
        this.transferDescription = value;
    }

    public static TransferType get(BigDecimal key) {
        return lookup.get(key);
    }

    public BigDecimal getTransferTypeId() {
        return transferTypeId;
    }

    public String getTransferDescription() {
        return transferDescription;
    }
}
