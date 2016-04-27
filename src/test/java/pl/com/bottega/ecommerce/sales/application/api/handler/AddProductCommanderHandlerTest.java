/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.com.bottega.ecommerce.sales.application.api.handler;

import Builder.ProductBuilder;
import java.util.Date;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.ClientData;
import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.Id;
import pl.com.bottega.ecommerce.sales.application.api.command.AddProductCommand;
import pl.com.bottega.ecommerce.sales.domain.client.Client;
import pl.com.bottega.ecommerce.sales.domain.client.ClientRepository;
import pl.com.bottega.ecommerce.sales.domain.equivalent.SuggestionService;
import pl.com.bottega.ecommerce.sales.domain.productscatalog.Product;
import pl.com.bottega.ecommerce.sales.domain.productscatalog.ProductRepository;
import pl.com.bottega.ecommerce.sales.domain.productscatalog.ProductType;
import pl.com.bottega.ecommerce.sales.domain.reservation.Reservation;
import pl.com.bottega.ecommerce.sales.domain.reservation.ReservationRepository;
import pl.com.bottega.ecommerce.sharedkernel.Money;
import pl.com.bottega.ecommerce.system.application.SystemContext;

/**
 *
 * @author student
 */
public class AddProductCommanderHandlerTest {
    

    private AddProductCommandHandler handler;
    private ReservationRepository reservationRepository;
    private ProductRepository productRepository;
    private SuggestionService suggestionService;
    private ClientRepository clientRepository;
    private Product product;
    private Product subsituteProduct;
    private Client client;
    private Id productId;
    private Id orderId;
    private Reservation reservation;
    @Before
    public void setUpForTest(){
        
        handler = new AddProductCommandHandler();

        
        reservationRepository = mock(ReservationRepository.class);
        productId = new Id("1");
        orderId = new Id("1");
        
        reservation = new Reservation(Id.generate(), Reservation.ReservationStatus.OPENED,
                new ClientData(Id.generate(), "client"), new Date());
        when(reservationRepository.load(orderId)).thenReturn(reservation);
        
       
        ProductBuilder builder= new ProductBuilder();
        product = builder.getProduct().build();
        subsituteProduct = builder.getProduct().withName("sub Product").build();
        productRepository = mock(ProductRepository.class);
        when(productRepository.load(any(Id.class))).thenReturn(product);
        client = mock(Client.class);
        suggestionService = mock(SuggestionService.class);
        when(suggestionService.suggestEquivalent(eq(product), any(Client.class))).thenReturn(subsituteProduct);
        
        clientRepository = mock(ClientRepository.class);
        when(clientRepository.load(any(Id.class))).thenReturn(client);
        
        handler.setReservationRepository(reservationRepository);
        handler.setProductRepository(productRepository);
        handler.setSuggestionService(suggestionService);
        handler.setClientRepository(clientRepository);
        handler.setSystemContext(new SystemContext());
    }
    
    
    @Test
    public void givenCommandWithAvaiableProduct_whenHandle_thenItemInReservation(){
        
        AddProductCommand command = new AddProductCommand(orderId, productId, 2);
        handler.handle(command);
        assertThat(reservation.getReservedProducts().size(), equalTo(1));
    }
    
    @Test
    public void givenCommandWithNotAvaiableProduct_whenHandle_substitueProductInReservation(){
        product.markAsRemoved();
        AddProductCommand command = new AddProductCommand(orderId, productId, 2);
        handler.handle(command);
        assertThat(reservation.getReservedProducts().get(0).getName(), equalTo(subsituteProduct.getName()));
    }
    @Test
    public void givenCommandWithAvaiableProduct_whenHandle_suggestionServiceNotCalled(){
        AddProductCommand command = new AddProductCommand(orderId, productId, 2);
        handler.handle(command);
        verify(suggestionService, times(0)).suggestEquivalent(any(Product.class), any(Client.class));
    }
    
}
