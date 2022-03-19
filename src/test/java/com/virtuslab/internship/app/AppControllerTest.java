package com.virtuslab.internship.app;

import com.virtuslab.internship.basket.Basket;
import com.virtuslab.internship.discount.DiscountGiver;
import com.virtuslab.internship.product.ProductDb;
import com.virtuslab.internship.receipt.Receipt;
import com.virtuslab.internship.receipt.ReceiptEntry;
import com.virtuslab.internship.rest.AppController;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppControllerTest {

    @Test
    void showReceipt() throws Exception {
        var productDb = new ProductDb();
        var orange = productDb.getProduct("Orange");
        var steak = productDb.getProduct("Steak");
        var bread = productDb.getProduct("Bread");
        var cereals = productDb.getProduct("Cereals");

        var basket = new Basket();
        basket.addProduct(orange);
        basket.addProduct(steak);
        basket.addProduct(bread);
        basket.addProduct(bread);
        basket.addProduct(cereals);
        basket.addProduct(cereals);

        var appController = new AppController();
        var receipt = appController.showReceipt(basket);

        List<ReceiptEntry> receiptEntries = new ArrayList<>();
        receiptEntries.add(new ReceiptEntry(steak, 1));
        receiptEntries.add(new ReceiptEntry(bread, 2));
        receiptEntries.add(new ReceiptEntry(cereals, 2));
        receiptEntries.add(new ReceiptEntry(orange, 1));

        var dicountGiver = new DiscountGiver();
        var expectedReceipt = new Receipt(receiptEntries);
        expectedReceipt =  dicountGiver.checkDiscount(expectedReceipt);

        assertEquals(expectedReceipt.discounts(), receipt.discounts());
        assertEquals(expectedReceipt.totalPrice(), receipt.totalPrice());
        assertEquals(expectedReceipt.entries(), receipt.entries());

    }
}
