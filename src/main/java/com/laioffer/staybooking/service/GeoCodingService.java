package com.laioffer.staybooking.service;


import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.laioffer.staybooking.exception.GeoCodingException;
import com.laioffer.staybooking.exception.InvalidStayAddressException;
import com.laioffer.staybooking.model.Location;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.stereotype.Service;


import java.io.IOException;


@Service
public class GeoCodingService {


    private final GeoApiContext context;

    public GeoCodingService(GeoApiContext context) {
        this.context = context;
    }


    public Location getLatLng(Long id, String address) {
        try {
            GeocodingResult result = GeocodingApi.geocode(context, address).await()[0];
            if (result.partialMatch) {
                throw new InvalidStayAddressException("Failed to find stay address");
            }
            return new Location(id, new GeoPoint(result.geometry.location.lat, result.geometry.location.lng));
        } catch (IOException | ApiException | InterruptedException e) {
            e.printStackTrace();
            throw new GeoCodingException("Failed to encode stay address");
        }
    }
}
