package com.balu.ecommerce_product_service.controller;

import com.balu.ecommerce_product_service.DTO.ProductDto;
import com.balu.ecommerce_product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // POST /api/products
    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto dto) {
        ProductDto created = productService.createProduct(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // GET /api/products
    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    // GET /api/products/1
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    // UPDATE /api/products/1
    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProductById(@PathVariable Long id, @RequestBody ProductDto dto) {
        return ResponseEntity.ok(productService.updateProduct(id, dto));
    }

    // DELETE /api/products/1
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
        return ResponseEntity.ok("Product deleted Successfully");
    }

    // GET /api/products/category/Electronics
    @GetMapping("/category/{category}")
    public ResponseEntity<List<ProductDto>> getAllProductsByCategory(@PathVariable String category) {
        return ResponseEntity.ok(productService.getProductsByCategory(category));
    }

//    @GetMapping("/search")
//    @PreAuthorize("isAuthenticated()")
//    public ResponseEntity<PagedResponseDTO<ProductDto>> searchProducts(
//            @ModelAttribute ProductFilterDTO filter){
//        return ResponseEntity.ok(productService.getFilteredProductDto(filter));
//    }

    // This endpoint is called by Order Service internally
    // PATCH /api/products/{id}/deduct-stock?quantity=2
    @PutMapping("/{id}/deduct-stock")
    public ResponseEntity<String> deductStock(
            @PathVariable Long id,
            @RequestParam int quantity) {
        productService.deductStock(id, quantity);
        return ResponseEntity.ok("Stock deducted successfully");
    }

    // Also add this GET for order service to fetch product details
    // GET /api/products/{id}/details
    @GetMapping("/{id}/details")
    public ResponseEntity<ProductDto> getProductDetails(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }
}
