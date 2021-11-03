package com.example.GDP.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GdpGrowthRate {

    private String countryCode;
    private Double year1Gdp;
    private Double year2Gdp;

    @Override
    public String toString() {
        return "GDP Growth Rate of "+ countryCode + " = " +((year2Gdp-year1Gdp)/year1Gdp);
    }
}
