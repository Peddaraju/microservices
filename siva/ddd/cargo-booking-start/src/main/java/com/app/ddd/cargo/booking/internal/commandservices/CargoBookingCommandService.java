package com.app.ddd.cargo.booking.internal.commandservices;


import com.app.ddd.cargo.booking.domain.model.aggregates.BookingId;
import com.app.ddd.cargo.booking.domain.model.aggregates.Cargo;
import com.app.ddd.cargo.booking.domain.model.commands.BookCargoCommand;
import com.app.ddd.cargo.booking.domain.model.commands.RouteCargoCommand;
import com.app.ddd.cargo.booking.domain.model.valueobjects.CargoItinerary;
import com.app.ddd.cargo.booking.domain.model.valueobjects.LastCargoHandledEvent;
import com.app.ddd.cargo.booking.infra.repositories.CargoRepository;
import com.app.ddd.cargo.booking.internal.outboundservices.ExternalCargoRoutingService;
import com.app.ddd.cargo.booking.shareddomain.events.CargoHandledEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;





/**
 * Application Service class for the Cargo Booking Commands
 */

@Service
public class CargoBookingCommandService {


	@Autowired
    private CargoRepository cargoRepository;
	@Autowired
    private ExternalCargoRoutingService externalCargoRoutingService;

  

    /**
     * Service Command method to book a new Cargo
     * @return BookingId of the Cargo
     */

    public BookingId bookCargo(BookCargoCommand bookCargoCommand){

        String random = UUID.randomUUID().toString().toUpperCase();
        System.out.println("Random is :"+random);
        bookCargoCommand.setBookingId(random);
       // bookCargoCommand.setBookingId(random.substring(0, random.indexOf("-")));
        Cargo cargo = new Cargo(bookCargoCommand);
        cargoRepository.save(cargo);
        return new BookingId(random);
    }

    /**
     * Service Command method to assign a route to a Cargo
     * @param routeCargoCommand
     */

    public void assignRouteToCargo(RouteCargoCommand routeCargoCommand){
    	
    	System.out.println("CargoBookingCommandService.assignRouteToCargo()===========================");

        Cargo cargo = cargoRepository.findByBookingId(routeCargoCommand.getCargoBookingId());
       
        
        CargoItinerary cargoItinerary = externalCargoRoutingService.fetchRouteForSpecification(cargo.getRouteSpecification());
        
       /* CargoItinerary cargoItinerary = externalCargoRoutingService.fetchRouteForSpecification(new RouteSpecification(
                new Location(routeCargoCommand.getOriginLocation()),
                new Location(routeCargoCommand.getDestinationLocation()),
                routeCargoCommand.getArrivalDeadline()
        ));*/
        routeCargoCommand.setCargoItinerary(cargoItinerary);
        
        cargo.assignToRoute(routeCargoCommand);
        cargoRepository.save(cargo);

    }
    
    public void updateDelivery(CargoHandledEvent cargoHandledEvent) {
    	
    	System.err.println("CargoBookingCommandService.updateDelivery()");
   	 Cargo cargo = cargoRepository.findByBookingId(cargoHandledEvent.getCargoHandledEventData().getBookingId());
   	 LastCargoHandledEvent lastCargoHandledEvent= new LastCargoHandledEvent(1,
   			 cargoHandledEvent.getCargoHandledEventData().getHandlingType(), cargoHandledEvent.getCargoHandledEventData().getVoyageNumber(), 
   			 cargoHandledEvent.getCargoHandledEventData().getHandlingLocation());
   	 
   	 cargo.deriveDeliveryProgress(lastCargoHandledEvent);
   	 
   	 cargoRepository.save(cargo);
   	 
   	 
   }


}
