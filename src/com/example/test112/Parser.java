package com.example.test112;

import doan.placeandroidmap.Place;
import doan.placeandroidmap.PlaceList;

public interface Parser {
    public Route parse();
    public PlaceList parseToPlaces();
    public Place parseDetailPlace(Place place);
}
