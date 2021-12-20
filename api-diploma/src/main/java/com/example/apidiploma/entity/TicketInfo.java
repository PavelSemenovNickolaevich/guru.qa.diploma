package com.example.apidiploma.entity;

import lombok.*;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class TicketInfo {

    private String price;
    private List<City> departure;
    private List<City> arrival;
    private Integer counts;

    @Override
    public String toString() {
        return "TicketInfo{" +
                "price='" + price + '\'' +
                ", departure=" + departure +
                ", arrival=" + arrival +
                ", counts=" + counts +
                '}';
    }
}

