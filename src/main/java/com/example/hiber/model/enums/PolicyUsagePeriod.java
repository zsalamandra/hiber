package com.example.hiber.model.enums;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public enum PolicyUsagePeriod {

    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TWELVE(12);

    private final int months;

    PolicyUsagePeriod(int months) {
        this.months = months;
    }

    public LocalDateTime getPolicyUsageEnd(@NotNull LocalDateTime usageStart) {
        return usageStart.plusMonths(months);
    }

    public int getMonths() {
        return this.months;
    }
}
