package com.learnup.asserts;

import com.learnup.dto.Product;
import com.learnup.enums.ProductType;
import io.qameta.allure.Step;
import lombok.experimental.UtilityClass;
import org.hamcrest.CoreMatchers;

import static com.learnup.asserts.IsCategoryExists.isCategoryExists;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@UtilityClass
public class CommonAsserts {

    @Step
    public Integer postProductPositiveAssert(Product product, Product response) {
        Integer id = response.getId();
        assertThat(id, CoreMatchers.is(not(nullValue())));
        assertThat(response.getCategoryTitle(), isCategoryExists());
        assertThat(response.getCategoryTitle(), equalTo(product.getCategoryTitle()));
        assertThat(response.getTitle(), equalTo(product.getTitle()));
        assertThat(response.getPrice(), equalTo(product.getPrice()));
        return id;
    }
    @Step("PUT product MATCHES PUT parameters TEST")
    public Integer putProductTest(Product response, Product product) {
        Integer id = response.getId();
        assertThat(response.getId(), equalTo(product.getId()));
        assertThat(response.getTitle(), equalTo(product.getTitle()));
        assertThat(response.getPrice(), equalTo(product.getPrice()));
        assertThat(response.getCategoryTitle(), equalTo(product.getCategoryTitle()));
        return id;
    }
    @Step("GET product MATCHES GET parameters TEST")
    public void getProductTest(Product response, ProductType product) {
        assertThat(response.getId(), equalTo(product.getId()));
        assertThat(response.getTitle(), equalTo(product.getTitle()));
        assertThat(response.getPrice(), equalTo(product.getPrice()));
        assertThat(response.getCategoryTitle(), equalTo(product.getCategory()));
    }
    @Step("POST product MATCHES GET parameters TEST")
    public Integer postProductTest(Product response, Product product) {
        Integer id = response.getId();
        assertThat(response.getId(), is(not(nullValue())));
        assertThat(response.getTitle(), equalTo(product.getTitle()));
        assertThat(response.getPrice(), equalTo(product.getPrice()));
        assertThat(response.getCategoryTitle(), equalTo(product.getCategoryTitle()));
        return id;
    }
}