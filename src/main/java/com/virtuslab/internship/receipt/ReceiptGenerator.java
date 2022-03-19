package com.virtuslab.internship.receipt;

import com.virtuslab.internship.basket.Basket;
import com.virtuslab.internship.product.Product;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReceiptGenerator {

    public Receipt generate(Basket basket) {
        var receipt = getReceipt(basket);
        return receipt;
    }

    private Receipt getReceipt(Basket basket) {
        List<ReceiptEntry> receiptEntries = new ArrayList<>();
        List<Product> products = basket.getProducts();
        Map<Product, Integer> amountProduct = new HashMap<>();
        for(Product product : products){
            if(!amountProduct.containsKey(product)) { amountProduct.put(product,1); }
            else { amountProduct.replace(product,amountProduct.get(product) + 1); }
        }
        amountProduct.forEach((key, value)->{
            receiptEntries.add(new ReceiptEntry(key,value));
                });
        return new Receipt(receiptEntries);
    }
}
