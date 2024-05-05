package kg.coffix.app.entity.enums;

import kg.coffix.app.exception.BadRequestException;

public enum ProductType {
    CAKE, FOOD, BEVERAGE, ICE_CREAM, DESSERT, PASTRY;

    public static ProductType of(String value) {
        try {
            return ProductType.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Product type is not valid");
        }
    }
}

