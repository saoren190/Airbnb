
package com.laioffer.staybooking.repository;


import com.laioffer.staybooking.model.Reservation;
import com.laioffer.staybooking.model.Stay;
import com.laioffer.staybooking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.util.List;


@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {


    List<Reservation> findByGuest(User guest);


    List<Reservation> findByGuest_Username(String username);


    List<Reservation> findByStay(Stay stay);


    List<Reservation> findByStay_Id(Long stayId);


    Reservation findByIdAndGuest(Long id, User guest);


    Reservation findByIdAndGuest_Username(Long id, String username);


    List<Reservation> findByStayAndCheckoutDateAfter(Stay stay, LocalDate date);
}
