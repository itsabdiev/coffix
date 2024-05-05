package kg.coffix.app.entity.enums;

import kg.coffix.app.exception.BadRequestException;

public enum QuantityType {
    MASS,PIECE,VOLUME;

    public static QuantityType of(String value) {
        try {
            return QuantityType.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Quantity type is not valid");
        }
    }
}
