package com.example.apidiploma.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class City {

    @JsonProperty("city_name")
    private String cityName;
    @JsonProperty("airport_name")
    private String airportName;
    private Date date;

    @Override
    public String toString() {
        return "City{" +
                "cityName='" + cityName + '\'' +
                ", airportName='" + airportName + '\'' +
                ", date=" + date +
                '}';
    }
}
