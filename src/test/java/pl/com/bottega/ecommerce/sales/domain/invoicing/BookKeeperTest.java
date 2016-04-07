/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.com.bottega.ecommerce.sales.domain.invoicing;

import java.util.ArrayList;
import java.util.Collection;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Matchers.any;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;
import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.ClientData;
import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.Id;
import pl.com.bottega.ecommerce.sales.domain.productscatalog.ProductData;
import pl.com.bottega.ecommerce.sales.domain.productscatalog.ProductType;
import pl.com.bottega.ecommerce.sharedkernel.Money;

/**
 *
 * @author student
 */
public class BookKeeperTest {
    InvoiceFactory invoiceFactory;
    TaxPolicy tax;
    @Before
    public void setForTest(){
        invoiceFactory = mock(InvoiceFactory.class);
        tax = mock(TaxPolicy.class);
        
        
        
       
        
    }
    
    @Test
    public void givenInvoiceRequestWithSingleItem_whenIssuance_thenInvoiceWithSingleItem(){
        ClientData client = new ClientData(Id.generate(), "name");
        Invoice invoice = new Invoice(Id.generate(), client);
        InvoiceRequest invoiceReq = new InvoiceRequest(client);
        ProductData product = new ProductData();
        RequestItem requestItem = new requestItem();
        when(invoiceFactory.create(client)).thenReturn(invoice);
        when(tax.calculateTax(any(ProductType.class), any(Money.class))).thenReturn(new Tax(Money.ZERO, null));
        
    }
    
}
