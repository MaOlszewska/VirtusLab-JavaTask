package com.virtuslab.internship.discount;

import com.virtuslab.internship.receipt.Receipt;

public class DiscountGiver {

    private final Discount tenDiscount = new TenPercentDiscount();
    private final Discount fifteenDiscount = new FifteenPercentDiscount();

    public Receipt checkDiscount(Receipt receipt){
        Receipt receipt1 = receipt;
        receipt1 = fifteenDiscount.apply(receipt1);
        receipt1 = tenDiscount.apply(receipt1);
        return receipt1;
    }
}
