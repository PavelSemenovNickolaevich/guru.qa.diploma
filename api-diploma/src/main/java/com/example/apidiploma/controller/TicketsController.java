package com.example.apidiploma.controller;

import com.example.apidiploma.entity.City;
import com.example.apidiploma.entity.RouteData;
import com.example.apidiploma.entity.TicketInfo;
import com.example.apidiploma.util.Data;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TicketsController {

    @GetMapping("tickets/getAll")
    @ApiOperation("Get all tickets")
    public HashMap<RouteData, List<TicketInfo>> showAllTicket() {
        Data data = new Data();
        return data.createData();
    }

    @GetMapping("tickets/getAllDepartures")
    @ApiOperation("Get all departures cities and airports")
    public List<List<City>> showAllDepartureCities() {

        Data data = new Data();

        HashMap<RouteData, List<TicketInfo>> allData = data.createData();

        return allData.values()
                .stream()
                .flatMap(List::stream)
                .map(TicketInfo::getDeparture)
                .collect(Collectors.toList());
    }

    @GetMapping("tickets/getAllArrivals")
    @ApiOperation("Get all arrivals cities and airports")
    public List<List<City>> showAllArrivalCities() {

        Data data = new Data();

        HashMap<RouteData, List<TicketInfo>> allData = data.createData();

        return allData.values()
                .stream()
                .flatMap(List::stream)
                .map(TicketInfo::getArrival)
                .collect(Collectors.toList());
    }

    @GetMapping("/tickets/filterByPrice")
    @ApiOperation("Get all filtered tickets by price")
    public List<TicketInfo> getFilteredTicketsByPrice(@RequestParam int minPrice, int maxPrice) {
        return getTicketInfos(minPrice, maxPrice);
    }

    private List<TicketInfo> getTicketInfos(@RequestParam int minPrice, int maxPrice) {
        Data data = new Data();

        HashMap<RouteData, List<TicketInfo>> allData = data.createData();

        return allData.values()
                .stream()
                .flatMap(List::stream)
                .filter(p -> Integer.parseInt(p.getPrice()) >= minPrice && Integer.parseInt(p.getPrice()) <= maxPrice)
                .collect(Collectors.toList());
    }

    @PostMapping("/tickets/addNewTicket")
    @ApiOperation("Adding new ticket in tickets storage")
    private HashMap<RouteData, List<TicketInfo>> addInBasket(@RequestBody @Validated Data.CityTicketData cityTicketData) {
        Data data = new Data();
        List<TicketInfo> ticketInfoList = data.createTicket(cityTicketData);
        HashMap<RouteData, List<TicketInfo>> listOdTickets = data.createData();
        listOdTickets.put(cityTicketData.getRouteData(), ticketInfoList);
        return listOdTickets;
    }
}

