package com.university.placement.api;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlacementStatsTest {

    @Test
    void percentageIsRoundedToTwoDecimals() {
        PlacementStats stats = new PlacementStats(14, 3, 3,
                new BigDecimal("25.67"), new BigDecimal("42.00"));
        assertEquals(new BigDecimal("21.43"), stats.placementPercentage());
    }

    @Test
    void percentageIsZeroWhenNoStudents() {
        PlacementStats stats = new PlacementStats(0, 0, 0, BigDecimal.ZERO, BigDecimal.ZERO);
        assertEquals(new BigDecimal("0.00"), stats.placementPercentage());
    }
}
