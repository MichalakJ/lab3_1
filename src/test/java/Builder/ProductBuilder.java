/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Builder;

import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.Id;
import pl.com.bottega.ecommerce.sales.domain.productscatalog.Product;
import pl.com.bottega.ecommerce.sales.domain.productscatalog.ProductType;
import pl.com.bottega.ecommerce.sharedkernel.Money;

/**
 *
 * @author Kuba
 */
public class ProductBuilder {
        private Money price = new Money(1);
	private String name = "defualtName";
	private ProductType productType = ProductType.DRUG;
        private Product product;
        public ProductBuilder(){
            
        }
        public void getProduct(){
            product = new Product(Id.generate(), price, name, productType);
        }
        public Product build(){
            return product;
        }
        public void withName(String name){
            this.name = name;
        }
        public void withPrice(Money price){
            this.price = price;
        }
        public void withProductType(ProductType productType){
            this.productType = productType;
        }
}
