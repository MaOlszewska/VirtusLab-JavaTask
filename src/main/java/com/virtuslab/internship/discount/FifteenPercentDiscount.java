package com.virtuslab.internship.discount;

import com.virtuslab.internship.product.Product;
import com.virtuslab.internship.receipt.Receipt;
import com.virtuslab.internship.receipt.ReceiptEntry;

import java.math.BigDecimal;

public class FifteenPercentDiscount extends Discount{

    public static String NAME = "FifteenPercentDiscount";

    public Receipt apply(Receipt receipt){
        if(shouldApply(receipt)){
            var discounts = receipt.discounts();
            discounts.add(NAME);
            var totalPrice = receipt.totalPrice().multiply(BigDecimal.valueOf(0.85));
            return new Receipt(receipt.entries(), discounts, totalPrice);
        }
        return receipt;
    }

    public Boolean shouldApply(Receipt receipt) {
        int counter = 0;
        for (ReceiptEntry entry : receipt.entries()) {
            if (entry.product().getType().equals(Product.Type.GRAINS)) { counter += entry.getQuantity(); }
        }
        return counter >= 3;
    }
}

