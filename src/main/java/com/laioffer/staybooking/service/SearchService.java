package com.laioffer.staybooking.service;


import com.laioffer.staybooking.model.Stay;
import com.laioffer.staybooking.repository.LocationRepository;
import com.laioffer.staybooking.repository.StayRepository;
import com.laioffer.staybooking.repository.StayReservationDateRepository;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class SearchService {
    private final StayRepository stayRepository;
    private final StayReservationDateRepository stayReservationDateRepository;
    private final LocationRepository locationRepository;


    public SearchService(StayRepository stayRepository, StayReservationDateRepository stayReservationDateRepository, LocationRepository locationRepository) {
        this.stayRepository = stayRepository;
        this.stayReservationDateRepository = stayReservationDateRepository;
        this.locationRepository = locationRepository;
    }


    public List<Stay> search(int guestNumber, LocalDate checkinDate, LocalDate checkoutDate, double lat, double lon, String distance) {
        List<Long> stayIds = locationRepository.searchByDistance(lat, lon, distance);
        if (stayIds == null || stayIds.isEmpty()) {
            return Collections.emptyList();
        }
        //pre-processing
        Set<Long> reservedStayIds = stayReservationDateRepository.findByIdInAndDateBetween(stayIds, checkinDate, checkoutDate.minusDays(1));
        List<Long> filteredStayIds = stayIds.stream()
                .filter(stayId -> !reservedStayIds.contains(stayId))
                .collect(Collectors.toList());
        //after we find the exact stayId, we search the stays by id and customer number
        return stayRepository.findByIdInAndGuestNumberGreaterThanEqual(filteredStayIds, guestNumber);
    }
}
