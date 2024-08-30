package com.spring.batch.impl;

import com.spring.batch.entity.Product;
import org.springframework.batch.item.ItemProcessor;

public class CustomItemProcessorImpl implements ItemProcessor<Product, Product> {

    @Override
    public Product process(Product item) throws Exception {

        try {
            System.out.println(item.getDescription());
            int discountPer = Integer.parseInt(item.getDiscount().trim());
            double originalPrice = Double.parseDouble(item.getPrice().trim());
            double discount = (discountPer / 100) * originalPrice;
            double finalPrice = originalPrice - discount;
            item.setDiscountedPrice(String.valueOf(finalPrice));
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }

        return item;
    }
}
