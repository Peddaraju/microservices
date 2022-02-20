package com.app.ddd.cargo.booking.interfaces.eventhandlers;


import javax.transaction.Transactional;

import com.app.ddd.cargo.booking.internal.commandservices.CargoBookingCommandService;
import com.app.ddd.cargo.booking.shareddomain.events.CargoHandledEvent;
import com.app.ddd.cargo.booking.shareddomain.events.CargoHandledEventData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;


@Service
public class CargoHandledEventHandler {

		@Autowired
       private CargoBookingCommandService cargoBookingCommandService;
        /**
         * Cargo Handled Event handler
         * @param event
         */
        @StreamListener(target = "cargoHandlingChannel")
        public void observeCargoHandledEvent( CargoHandledEvent event) {
            System.out.println("***Cargo Handled Event in booking service****Booking ID =="+event.getCargoHandledEventData().getBookingId());
                CargoHandledEventData eventData = event.getCargoHandledEventData();
                System.out.println(eventData.getBookingId());
                System.out.println(eventData.getHandlingLocation());
                System.out.println(eventData.getHandlingCompletionTime());
                System.out.println(eventData.getHandlingType());
                System.out.println(eventData.getVoyageNumber());
               cargoBookingCommandService.updateDelivery(event);
        }


}
