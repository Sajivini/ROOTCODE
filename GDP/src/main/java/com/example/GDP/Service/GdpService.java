package com.example.GDP.Service;

import com.example.GDP.Entity.Gdp;
import com.example.GDP.Entity.GdpGrowthRate;

import java.io.IOException;
import java.util.List;

public interface GdpService {
    GdpGrowthRate getGdpGrowthRate(String countryCode,int year1Gdp,int year2Gdp) throws IOException;
}
