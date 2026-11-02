package com.university.placement.repository;

import java.math.BigDecimal;

public interface OfferAggregateProjection {

    long getTotalOffers();

    BigDecimal getAveragePackageLpa();

    BigDecimal getHighestPackageLpa();
}
