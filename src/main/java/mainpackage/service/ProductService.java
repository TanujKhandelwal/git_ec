package mainpackage.service;

import mainpackage.model.*;
import mainpackage.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public AddProductResponse enlist(Product product){

        Product newProduct = productRepository.save(product);
        AddProductResponse addProductResponse = new AddProductResponse();
        if(newProduct == null){
            addProductResponse.setStatus(false);
            addProductResponse.setMessage("Product cannot be created");
        }
        else{
            addProductResponse.setStatus(true);
            addProductResponse.setMessage("Product created successfully");
        }

        return addProductResponse;
    }

    public GetProductResponse getprod(long id){
        Product product = productRepository.findById(id);
        GetProductResponse getProductResponse = new GetProductResponse();
        if(product == null){
            getProductResponse.setCategory("no category");
            getProductResponse.setId(0);
            getProductResponse.setMessage("No product with such id");
            getProductResponse.setName("No name");
            getProductResponse.setStatus(false);
            getProductResponse.setPrice(0);
        }
        else{
            getProductResponse.setCategory(product.getCategory());
            getProductResponse.setId(product.getId());
            getProductResponse.setMessage("product found");
            getProductResponse.setName(product.getName());
            getProductResponse.setStatus(true);
            getProductResponse.setPrice(product.getPrice());
        }

        return getProductResponse;

    }

    public UpdateProductResponse updateProduct(Product product){
        Product oldProduct = productRepository.findById(product.getId());
        UpdateProductResponse updateProductResponse = new UpdateProductResponse();
        if(oldProduct == null){
            updateProductResponse.setStatus(false);
            updateProductResponse.setMessage("Product with this id is not found");
        }
        else{
            productRepository.save(product);
            updateProductResponse.setStatus(true);
            updateProductResponse.setMessage("Product updated successfully");
        }

        return updateProductResponse;
    }

    public DeleteProductResponse deleteProd(long id){
        Product product = productRepository.findById(id);
        DeleteProductResponse deleteProductResponse=new DeleteProductResponse();

        if(product == null){
            deleteProductResponse.setStatus(false);
            deleteProductResponse.setMessage("Product with this id doesnot exist");
        }
        else{
            productRepository.delete(product);
            deleteProductResponse.setStatus(true);
            deleteProductResponse.setMessage("Product deleted successfully");
        }

        return deleteProductResponse;
    }
}
