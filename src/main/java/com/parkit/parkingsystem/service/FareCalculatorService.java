package com.parkit.parkingsystem.service;

import com.parkit.parkingsystem.constants.Fare;
import com.parkit.parkingsystem.model.Ticket;

public class FareCalculatorService {

    public void calculateFare(Ticket ticket){
        if( (ticket.getOutTime() == null) || (ticket.getOutTime().before(ticket.getInTime())) ){
            throw new IllegalArgumentException("Out time provided is incorrect:"+ticket.getOutTime().toString());
        }

        
        double inHour = ticket.getInTime().getTime();
        double outHour = ticket.getOutTime().getTime();

        
        double duration = ((outHour - inHour) / (60 * 60 * 1000));
        
        double discount = 1;
        
        if(duration <= 0.5) {
        	ticket.setPrice(0);
        	return;
        } 
        	if (ticket.getDiscountPrice()) {
        		 discount = Fare.USER_DISCOUNT;
        	}
        		switch (ticket.getParkingSpot().getParkingType()){
            	case CAR: {
            		ticket.setPrice(duration * Fare.CAR_RATE_PER_HOUR * discount );
            		break;
            		}
            	case BIKE: {
            		ticket.setPrice(duration * Fare.BIKE_RATE_PER_HOUR * discount );
            		break;
            		}
            	default: throw new IllegalArgumentException("Unkown Parking Type"); 
        		} 
    	}
}
         		
        	
    
 