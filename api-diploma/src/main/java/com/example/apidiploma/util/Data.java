package com.example.apidiploma.util;

import com.example.apidiploma.entity.City;
import com.example.apidiploma.entity.RouteData;
import com.example.apidiploma.entity.TicketInfo;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;

import java.util.*;

public class Data {

    public HashMap<RouteData, List<TicketInfo>> createData() {

        List<City> cityOfArrivalList = new ArrayList<>();
        cityOfArrivalList.add(City.builder().cityName("Moscow").airportName("Domodedovo")
                .date(new GregorianCalendar(2021, Calendar.JANUARY, 25).getTime()).build());
        cityOfArrivalList.add(City.builder().cityName("SaintP").airportName("Pulkovo")
                .date(new GregorianCalendar(2021, Calendar.JANUARY, 25).getTime()).build());

        List<City> cityOfDepartureList = new ArrayList<>();
        cityOfDepartureList.add(City.builder().cityName("Izhevsk").airportName("IzhevskAirPort")
                .date(new GregorianCalendar(2021, Calendar.JANUARY, 25).getTime()).build());
        cityOfDepartureList.add(City.builder().cityName("Minsk").airportName("MinskAirport")
                .date(new GregorianCalendar(2021, Calendar.JANUARY, 25).getTime()).build());

        List<City> cityOfArrivalListTwo = new ArrayList<>();
        cityOfArrivalListTwo.add(City.builder().cityName("Moscow").airportName("Domodedovo")
                .date(new GregorianCalendar(2021, Calendar.JANUARY, 25).getTime()).build());
        cityOfArrivalListTwo.add(City.builder().cityName("SaintP").airportName("Pulkovo")
                .date(new GregorianCalendar(2021, Calendar.JANUARY, 25).getTime()).build());

        List<City> cityOfDepartureListTwo = new ArrayList<>();
        cityOfDepartureListTwo.add(City.builder().cityName("Izhevsk").airportName("IzhevskAirPort")
                .date(new GregorianCalendar(2021, Calendar.JANUARY, 25).getTime()).build());
        cityOfDepartureListTwo.add(City.builder().cityName("Minsk").airportName("MinskAirport")
                .date(new GregorianCalendar(2021, Calendar.JANUARY, 25).getTime()).build());

        List<City> cityOfArrivalListThree = new ArrayList<>();
        cityOfArrivalListThree.add(City.builder().cityName("Moscow").airportName("Domodedovo")
                .date(new GregorianCalendar(2021, Calendar.JANUARY, 26).getTime()).build());
        cityOfArrivalListThree.add(City.builder().cityName("SaintP").airportName("Pulkovo")
                .date(new GregorianCalendar(2021, Calendar.JANUARY, 27).getTime()).build());

        List<City> cityOfDepartureListThree = new ArrayList<>();
        cityOfDepartureListThree.add(City.builder().cityName("Izhevsk").airportName("IzhevskAirPort")
                .date(new GregorianCalendar(2021, Calendar.JANUARY, 25).getTime()).build());
        cityOfDepartureListThree.add(City.builder().cityName("Minsk").airportName("MinskAirport")
                .date(new GregorianCalendar(2021, Calendar.JANUARY, 25).getTime()).build());


        TicketInfo ticketInfoOne = new TicketInfo();
        ticketInfoOne.setPrice("1000");
        ticketInfoOne.setArrival(cityOfArrivalList);
        ticketInfoOne.setDeparture(cityOfDepartureList);
        ticketInfoOne.setCounts(cityOfArrivalList.size() + cityOfDepartureList.size());

        TicketInfo ticketInfoTwo = new TicketInfo();
        ticketInfoTwo.setPrice("999");
        ticketInfoTwo.setArrival(cityOfArrivalListTwo);
        ticketInfoTwo.setDeparture(cityOfDepartureListTwo);
        ticketInfoTwo.setCounts(cityOfDepartureListTwo.size() + cityOfDepartureListTwo.size());

        TicketInfo ticketInfoThree = new TicketInfo();
        ticketInfoThree.setPrice("666");
        ticketInfoThree.setArrival(cityOfArrivalListThree);
        ticketInfoThree.setDeparture(cityOfDepartureListThree);
        ticketInfoThree.setCounts(cityOfDepartureListThree.size() + cityOfDepartureListThree.size());

        List<TicketInfo> ticketInfoList = new ArrayList<>();
        ticketInfoList.add(ticketInfoOne);

        List<TicketInfo> ticketInfoListTwo = new ArrayList<>();
        ticketInfoListTwo.add(ticketInfoTwo);

        List<TicketInfo> ticketInfoListThree = new ArrayList<>();
        ticketInfoListThree.add(ticketInfoThree);

        HashMap<RouteData, List<TicketInfo>> listRoute = new HashMap<>();
        listRoute.put(RouteData.builder().route("RouteOne").build(), ticketInfoList);
        listRoute.put(RouteData.builder().route("RouteTwo").build(), ticketInfoListTwo);
        listRoute.put(RouteData.builder().route("RouteThree").build(), ticketInfoListThree);

        return listRoute;
    }

    @Setter
    @Getter
    public static class CityTicketData {
        City cityDep;
        City cityArr;
        RouteData routeData;
        String price;
    }

    public List<TicketInfo> createTicket(CityTicketData cityTicketData) {

        City cityDep = cityTicketData.getCityDep();
        City cityArr = cityTicketData.getCityArr();
        RouteData routeData = cityTicketData.getRouteData();

        List<City> cityOfDepartureList = new ArrayList<>();
        cityOfDepartureList.add(cityDep);

        List<City> cityOfArrivalListTwo = new ArrayList<>();
        cityOfArrivalListTwo.add(cityArr);

        TicketInfo ticketInfoThree = new TicketInfo();
        ticketInfoThree.setPrice(cityTicketData.price);
        ticketInfoThree.setArrival(cityOfDepartureList);
        ticketInfoThree.setDeparture(cityOfArrivalListTwo);
        ticketInfoThree.setCounts(cityOfDepartureList.size() + cityOfArrivalListTwo.size());

        List<TicketInfo> ticketInfoList = new ArrayList<>();
        ticketInfoList.add(ticketInfoThree);

        Data data = new Data();
        HashMap<RouteData, List<TicketInfo>> listOdTickets = data.createData();
        listOdTickets.put(routeData, ticketInfoList);

        return ticketInfoList;
    }

    public JSONObject getJsonObject() {
        JSONObject body = new JSONObject();
        body.put("price", "1000");
        JSONObject cityDep = new JSONObject();
        cityDep.put("airport_name", "TestDep");
        cityDep.put("city_name", "Test2");
        cityDep.put("date", "2021-12-06T06:37:32.847Z");
        body.put("city_dep", cityDep);
        JSONObject cityAr = new JSONObject();
        cityAr.put("airport_name", "Test");
        cityAr.put("city_name", "Test1");
        cityAr.put("date", "2021-12-06T06:37:32.847Z");
        body.put("city_arr", cityAr);
        JSONObject route = new JSONObject();
        route.put("route", "RouteFourth");
        body.put("route_data", route);
        return body;
    }
}
