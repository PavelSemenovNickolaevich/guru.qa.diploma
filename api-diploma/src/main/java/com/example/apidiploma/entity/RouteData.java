package com.example.apidiploma.entity;

import lombok.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RouteData {

    private String route;

    @Override
    public String toString() {
        return route;
    }
}
