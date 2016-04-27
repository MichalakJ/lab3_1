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
        public ProductBuilder getProduct(){
            product = new Product(Id.generate(), price, name, productType);
            return this;
        }
        public Product build(){
            return product;
        }
        public ProductBuilder withName(String name){
            this.name = name;
            return this;
        }
        public ProductBuilder withPrice(Money price){
            this.price = price;
            return this;
        }
        public ProductBuilder withProductType(ProductType productType){
            this.productType = productType;
            return this;
        }
}
