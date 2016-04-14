/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.com.bottega.ecommerce.sales.application.api.handler;

import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.ClientData;
import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.Id;
import pl.com.bottega.ecommerce.sales.application.api.command.AddProductCommand;
import pl.com.bottega.ecommerce.sales.domain.reservation.Reservation;
import pl.com.bottega.ecommerce.sales.domain.reservation.ReservationRepository;

/**
 *
 * @author student
 */
public class AddProductCommanderHandlerTest {
    
    private AddProductCommand command;
    private AddProductCommandHandler handler;
    private ReservationRepository reservationRepository;
    @Before
    public void setUpForTest(){
        command = new AddProductCommand(Id.generate(), Id.generate(), 2);
        handler = new AddProductCommandHandler();
        Reservation reservation = new Reservation(Id.generate(), Reservation.ReservationStatus.CLOSED,
                new ClientData(Id.generate(), "client"), new Date());
        
        reservationRepository = mock(ReservationRepository.class);
        when(reservationRepository.load(any(Id.class))).thenReturn(reservation);
        
        handler.setReservationRepository(reservationRepository);
        
        
    }
    
    
    @Test
    public void someTest(){
        
    }
    
}
