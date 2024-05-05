package kg.coffix.app.entity.enums;

import kg.coffix.app.exception.BadRequestException;

public enum SeasonalType {
    ALL_SEASONAL,SEASONAL;

    public static SeasonalType of(String value) {
        try {
            return SeasonalType.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Seasonal type is not valid");
        }
    }

}
