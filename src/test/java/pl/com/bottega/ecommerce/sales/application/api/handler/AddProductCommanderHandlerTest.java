/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.com.bottega.ecommerce.sales.application.api.handler;

import org.junit.Before;
import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.Id;
import pl.com.bottega.ecommerce.sales.application.api.command.AddProductCommand;

/**
 *
 * @author student
 */
public class AddProductCommanderHandlerTest {
    
    AddProductCommand command;
    @Before
    public void setUpForTest(){
        command = new AddProductCommand(Id.generate(), Id.generate(), 2);
        
    }
    
}
