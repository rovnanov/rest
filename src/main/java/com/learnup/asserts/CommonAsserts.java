package com.learnup.asserts;

import com.learnup.dto.Product;
import com.learnup.enums.ProductType;
import lombok.experimental.UtilityClass;
import org.hamcrest.CoreMatchers;

import static com.learnup.asserts.IsCategoryExists.isCategoryExists;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@UtilityClass
public class CommonAsserts {

    public Integer postProductPositiveAssert(Product product, Product response) {
        Integer id = response.getId();
        assertThat(id, CoreMatchers.is(not(nullValue())));
        assertThat(response.getCategoryTitle(), isCategoryExists());
        assertThat(response.getCategoryTitle(), equalTo(product.getCategoryTitle()));
        assertThat(response.getTitle(), equalTo(product.getTitle()));
        assertThat(response.getPrice(), equalTo(product.getPrice()));
        return id;
    }
    public Integer putProductTest(Product response, Product product) {
        Integer id = response.getId();
        assertThat(response.getId(), equalTo(product.getId()));
        assertThat(response.getTitle(), equalTo(product.getTitle()));
        assertThat(response.getPrice(), equalTo(product.getPrice()));
        assertThat(response.getCategoryTitle(), equalTo(product.getCategoryTitle()));
        return id;
    }
    public void getProductTest(Product response, ProductType product) {
        assertThat(response.getId(), equalTo(product.getId()));
        assertThat(response.getTitle(), equalTo(product.getTitle()));
        assertThat(response.getPrice(), equalTo(product.getPrice()));
        assertThat(response.getCategoryTitle(), equalTo(product.getCategory()));
    }
    public Integer postProductTest(Product response, Product product) {
        Integer id = response.getId();
        assertThat(response.getId(), is(not(nullValue())));
        assertThat(response.getTitle(), equalTo(product.getTitle()));
        assertThat(response.getPrice(), equalTo(product.getPrice()));
        assertThat(response.getCategoryTitle(), equalTo(product.getCategoryTitle()));
        return id;
    }

}