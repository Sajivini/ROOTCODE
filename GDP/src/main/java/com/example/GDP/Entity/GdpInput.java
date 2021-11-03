package com.example.GDP.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GdpInput {

    @JsonProperty("countryCode")
    private String countryCode;
    @JsonProperty("year1")
    private int year1;
    @JsonProperty("year2")
    private int year2;
}
