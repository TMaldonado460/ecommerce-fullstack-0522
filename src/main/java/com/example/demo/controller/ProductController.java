package com.example.demo.controller;

import com.example.demo.dto.ArrayProductDTO;
import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Image;
import com.example.demo.entity.Product;
import com.example.demo.entity.Review;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController @RequestMapping("/api/v1/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    //Este método incluye todas las reviews de un producto y todas las imagenes de un producto
    @GetMapping("/{id}")
    public ProductDTO getProductById(@PathVariable UUID id) {
        return productService.findById(id);
    }
    // /api/v1/products post (@RequestBody List<ProductDTO> productsToInsert)
    @GetMapping("/")
    public List<ArrayProductDTO> getAllProducts(
            @RequestParam(required = false) String attribute,
            @RequestParam(required = false) String order,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer limit) {

        if (attribute == null) {
            attribute = "id";
        }
        if (order == null) {
            order = "asc";
        }
        if (page == null) {
            page = 0;
        }
        if (limit == null) {
            limit = 10;
        }

        if (order.equals("asc")) {
            return productService.findAllProducts(PageRequest.of(page, limit, Sort.by(Sort.Direction.ASC, attribute)));
        }
        else {
            return productService.findAllProducts(PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, attribute)));
        }
    }
//    /api/v1/products (@RequestParam(required = false) String attribute, @RequestParam(required = false) String order, @RequestParam(required = false) Integer offset)
//    ex: /api/v1/products?attribute=name&order=desc&page=2&limit=25
    /*
    offset 2 limit 3
    - - (- -) - - -
     */
//            * En el service se va a tener que hacer algo por cada producto (buscar la imagen que corresponde a la miniatura e insertarla en cada uno)
//    * Hacer un switch en relacion a posibles valores de los requestParams? no es necesario, fijarse cual es mejor



    @PostMapping("/api/v1/products")
    public List<ProductDTO> createProducts(@RequestBody List<ProductDTO> productsToInsert) {
        return productService.saveAllProducts(productsToInsert);
    }




    //el body va a ser un json--->array










    //SECCION DE FALLIDOS
    // /api/v1/products/{id} (@RequestParam UUID id)
//    @GetMapping("/api/v1/products/{id}")
//    public List<Object> getAllReviewsAndImages(@RequestParam UUID id) {
//        List<Object> list = new ArrayList<>();
//        List<Review> reviews=productService.findAllReviews(id);
//        List<Image> images=productService.findAllImages(id);
//        list.add(images);
//        list.add(reviews);
//        return list;
//    }

    //    @PostMapping("/category/{categoryId}")
//    public List<Product> getProductsByCategory(@PathVariable String categoryId, @RequestBody String body) {
//        Sort sort = null;
//        switch (body) {
//            case "asc":
//                sort = Sort.by("name").ascending();
//                break;
//            case "desc":
//                sort = Sort.by("name").descending();
//                break;
//            default:
//                sort = Sort.by("name").ascending();
//        }
//    }
}
