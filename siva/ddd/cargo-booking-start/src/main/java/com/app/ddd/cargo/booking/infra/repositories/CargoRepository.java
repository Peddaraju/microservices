package com.app.ddd.cargo.booking.infra.repositories;


import java.util.List;

import com.app.ddd.cargo.booking.domain.model.aggregates.BookingId;
import com.app.ddd.cargo.booking.domain.model.aggregates.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;



/**
 * Repository class for the Cargo Aggregate
 */
public interface CargoRepository extends JpaRepository<Cargo, Long> {

     Cargo findByBookingId(String bookingId);

     List<BookingId> findAllBookingIds();

     List<Cargo> findAll();

}
