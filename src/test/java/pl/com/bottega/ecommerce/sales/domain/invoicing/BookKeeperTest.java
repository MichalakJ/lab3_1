/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.com.bottega.ecommerce.sales.domain.invoicing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Matchers.any;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
    ClientData client;
    Invoice invoice;
    InvoiceRequest invoiceReq;
    RequestItem requestItem;
    ProductData product;
    @Before
    public void setForTest(){
        invoiceFactory = mock(InvoiceFactory.class);
        tax = mock(TaxPolicy.class);
        client = new ClientData(Id.generate(), "name");
        invoice = new Invoice(Id.generate(), client);
        invoiceReq = new InvoiceRequest(client);
        product = mock(ProductData.class);
        requestItem = new RequestItem(product, 1, new Money(32));
        
       
        
    }
    
    @Test
    public void givenInvoiceRequestWithSingleItem_whenIssuance_thenInvoiceWithSingleItem(){

        
        invoiceReq.add(requestItem);
        when(invoiceFactory.create(client)).thenReturn(invoice);
        when(tax.calculateTax(any(ProductType.class), any(Money.class))).thenReturn(new Tax(Money.ZERO, null));
        
        BookKeeper bookKeeper = new BookKeeper(invoiceFactory);
        Invoice result = bookKeeper.issuance(invoiceReq, tax);
        
        assertThat(result.getItems().size(), equalTo(1));
    }
    
    @Test
    public void givenInvoiceRequestWithTwoItems_WhenIssuance_ThenCalculateTaxCalledTwoTimes(){
        
        invoiceReq.add(requestItem);
        invoiceReq.add(requestItem);
        when(product.getType()).thenReturn(ProductType.DRUG);
        when(invoiceFactory.create(client)).thenReturn(invoice);
        when(tax.calculateTax(any(ProductType.class), any(Money.class))).thenReturn(new Tax(Money.ZERO, null));
        
        BookKeeper bookKeeper = new BookKeeper(invoiceFactory);
        Invoice result = bookKeeper.issuance(invoiceReq, tax);
        
        verify(tax, times(2)).calculateTax(ProductType.DRUG, new Money(32));
        
    }
    
    @Test
    public void givenInvoiceRequestWithMultipleItems_whenIssuance_thenInvoiceFactoryCalledOneTime(){
        
        invoiceReq.add(requestItem);
        invoiceReq.add(requestItem);
        BookKeeper bookKeeper = new BookKeeper(invoiceFactory);
        when(invoiceFactory.create(client)).thenReturn(invoice);
        when(product.getType()).thenReturn(ProductType.DRUG);
        when(tax.calculateTax(any(ProductType.class), any(Money.class))).thenReturn(new Tax(Money.ZERO, null));
        Invoice result = bookKeeper.issuance(invoiceReq, tax);
        
        verify(invoiceFactory, times(1)).create(client);
    }
    
    
}
