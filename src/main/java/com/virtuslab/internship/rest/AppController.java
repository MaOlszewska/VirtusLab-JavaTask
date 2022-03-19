package com.virtuslab.internship.rest;
import com.virtuslab.internship.basket.Basket;
import com.virtuslab.internship.receipt.Receipt;
import com.virtuslab.internship.discount.DiscountGiver;
import com.virtuslab.internship.receipt.ReceiptGenerator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    private final ReceiptGenerator receiptGenerator = new ReceiptGenerator();
    private final DiscountGiver giver = new DiscountGiver();

    @RequestMapping("/getReceipt")
    public Receipt showReceipt(Basket basket){
        Receipt receipt = receiptGenerator.generate(basket);
        receipt = giver.checkDiscount(receipt);
        return receipt ;
    }
}