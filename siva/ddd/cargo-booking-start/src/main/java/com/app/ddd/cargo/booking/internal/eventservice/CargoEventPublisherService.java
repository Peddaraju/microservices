package com.app.ddd.cargo.booking.internal.eventservice;


import com.app.ddd.cargo.booking.infra.brokers.CargoEventSource;
import com.app.ddd.cargo.booking.shareddomain.events.CargoBookedEvent;
import com.app.ddd.cargo.booking.shareddomain.events.CargoRoutedEvent;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;




@Service
public class CargoEventPublisherService {

    CargoEventSource cargoEventSource;

    public CargoEventPublisherService(CargoEventSource cargoEventSource){
        this.cargoEventSource = cargoEventSource;
    }

    @TransactionalEventListener
    public void handleCargoBookedEvent(CargoBookedEvent cargoBookedEvent){
    	System.err.println("Publishing CargoBookedEvent with booking id *** " +cargoBookedEvent.getCargoBookedEventData().getBookingId());
        cargoEventSource.cargoBooking().send(MessageBuilder.withPayload(cargoBookedEvent).build());
    }

    @TransactionalEventListener
    public void handleCargoRoutedEvent(CargoRoutedEvent cargoRoutedEvent){
    	System.err.println("Publishing CargoRoutedEvent with booking id ***"+cargoRoutedEvent.getCargoRoutedEventData().getBookingId());
        cargoEventSource.cargoRouting().send(MessageBuilder.withPayload(cargoRoutedEvent).build());

    }
}
