package com.virtuslab.internship.discount;

import com.virtuslab.internship.product.ProductDb;
import com.virtuslab.internship.receipt.Receipt;
import com.virtuslab.internship.receipt.ReceiptEntry;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DiscountGiverTest {

    @Test
    void giveTwoDiscounts() throws Exception {
        var productDb = new ProductDb();
        var orange = productDb.getProduct("Orange");
        var steak = productDb.getProduct("Steak");
        var bread = productDb.getProduct("Bread");
        var cereals = productDb.getProduct("Cereals");

        List<ReceiptEntry> receiptEntries = new ArrayList<>();
        receiptEntries.add(new ReceiptEntry(orange, 1));
        receiptEntries.add(new ReceiptEntry(steak, 1));
        receiptEntries.add(new ReceiptEntry(bread, 3));
        receiptEntries.add(new ReceiptEntry(cereals, 2));

        var dicountGiver = new DiscountGiver();
        var receipt = new Receipt(receiptEntries);

        var receiptAfterDiscount =  dicountGiver.checkDiscount(receipt);
        var expectedTotalPrice = receipt.totalPrice().multiply(BigDecimal.valueOf(0.85)).multiply(BigDecimal.valueOf(0.90));

        assertEquals(expectedTotalPrice, receiptAfterDiscount.totalPrice());
        assertEquals(2, receiptAfterDiscount.discounts().size());
    }

    @Test
    void giveOnlyTenDiscount() throws Exception {
        var productDb = new ProductDb();
        var orange = productDb.getProduct("Orange");
        var steak = productDb.getProduct("Steak");
        var bread = productDb.getProduct("Bread");
        var cereals = productDb.getProduct("Cereals");

        List<ReceiptEntry> receiptEntries = new ArrayList<>();
        receiptEntries.add(new ReceiptEntry(orange, 1));
        receiptEntries.add(new ReceiptEntry(steak, 1));
        receiptEntries.add(new ReceiptEntry(bread, 1));
        receiptEntries.add(new ReceiptEntry(cereals, 1));

        var dicountGiver = new DiscountGiver();
        var receipt = new Receipt(receiptEntries);

        var receiptAfterDiscount =  dicountGiver.checkDiscount(receipt);
        var expectedTotalPrice = receipt.totalPrice().multiply(BigDecimal.valueOf(0.90));

        assertEquals(expectedTotalPrice, receiptAfterDiscount.totalPrice());
        assertEquals(1, receiptAfterDiscount.discounts().size());
    }

}
