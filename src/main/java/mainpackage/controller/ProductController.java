package mainpackage.controller;

import mainpackage.model.*;
import mainpackage.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    public AddProductResponse addProduct(@RequestBody Product product){

        AddProductResponse addProductResponse = productService.enlist(product);
        return addProductResponse;

    }

    @GetMapping(value = "/show/{id}", produces = "application/json")
    public GetProductResponse showProduct(@PathVariable long id){

        GetProductResponse getProductResponse = productService.getprod(id);
        return getProductResponse;
    }

    @PutMapping(value = "/update",consumes = "application/json", produces = "application/json")
    public UpdateProductResponse updateProduct(@RequestBody Product product){

        UpdateProductResponse updateProductResponse = productService.updateProduct(product);
        return updateProductResponse;

    }

    @DeleteMapping(value = "/delete/{id}" , produces = "application/json")
    public DeleteProductResponse deleteProduct(@PathVariable long id){

        DeleteProductResponse deleteProductResponse = productService.deleteProd(id);
        return deleteProductResponse;
    }
}
