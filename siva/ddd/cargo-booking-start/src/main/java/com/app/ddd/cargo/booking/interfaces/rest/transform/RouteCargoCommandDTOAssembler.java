package com.app.ddd.cargo.booking.interfaces.rest.transform;

import com.app.ddd.cargo.booking.domain.model.commands.RouteCargoCommand;
import com.app.ddd.cargo.booking.interfaces.rest.dto.RouteCargoResource;
import com.app.ddd.cargo.booking.internal.queryservices.CargoBookingQueryService;
import org.springframework.beans.factory.annotation.Autowired;



/**
 * Assembler class to convert the Book Cargo Resource Data to the Book Cargo Model
 */
public class RouteCargoCommandDTOAssembler {

    /**
     * Static method within the Assembler class
     * @param routeCargoResource
     * @return RouteCargoCommand Model
     */
	
	@Autowired
    private CargoBookingQueryService cargoBookingQueryService;
	
	
    public static RouteCargoCommand toCommandFromDTO(RouteCargoResource routeCargoResource){
    	
    	//Cargo cargo=cargoBookingQueryService.find(routeCargoResource.getBookingId());
    	   

        return new RouteCargoCommand(routeCargoResource.getBookingId());
    }
}
