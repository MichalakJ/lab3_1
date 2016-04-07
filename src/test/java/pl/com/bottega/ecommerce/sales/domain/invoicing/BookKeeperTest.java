/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.com.bottega.ecommerce.sales.domain.invoicing;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Matchers.any;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.ClientData;
import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.Id;

/**
 *
 * @author student
 */
public class BookKeeperTest {
    
    @Before
    public void setForTest(){
        InvoiceFactory invoiceFactory = Mockito.mock(InvoiceFactory.class);
        InvoiceRequest invoiceRequest = Mockito.mock(InvoiceRequest.class);
        
        ClientData client = new ClientData(Id.generate(), "client");
        when(invoiceFactory.create(client)).thenReturn(new Invoice(Id.generate(), client));
    }
    
    @Test
    public void givenInvoiceRequestWithSingleItem_whenIssuance_thenInvoiceWithSingleItem(){

        
    }
    
}
