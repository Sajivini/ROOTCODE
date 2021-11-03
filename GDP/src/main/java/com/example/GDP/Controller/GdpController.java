package com.example.GDP.Controller;

import com.example.GDP.Entity.GdpGrowthRate;
import com.example.GDP.Entity.GdpInput;
import com.example.GDP.Service.GdpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/gdp")
public class GdpController {

    @Autowired
    GdpService gdpService ;

    @GetMapping("/growth")
    public GdpGrowthRate getGdpGrowthRate(@RequestBody GdpInput gdpInput) throws IOException {
        return gdpService.getGdpGrowthRate(gdpInput.getCountryCode(),gdpInput.getYear1(),gdpInput.getYear2());
    }
}
