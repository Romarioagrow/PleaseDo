package server.controllers;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import server.domain.Product;
import server.dto.FiltersList;
import server.services.ProductService;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

@Log
@RestController
@AllArgsConstructor
@RequestMapping("/api/products")
public class ProductApiController {
    private final ProductService productService;

    @GetMapping("/build_filters/{group}")
    private FiltersList createFiltersLists(@PathVariable String group) {
        return productService.createProductsFilterLists(group);
    }

    @GetMapping("/group/{group}/{page}")
    private Page<Product> listProductsByGroupPage(@PathVariable String group, @PathVariable(required = false) int page) {
        return productService.getProductsByGroup(group, PageRequest.of(page, 15, Sort.Direction.ASC, "pic"));
    }

    @GetMapping("/show/{productID}")
    private Product listProductByID(@PathVariable String productID) {
        return productService.getProductByID(productID);
    }

    @PostMapping("/filter/{group}/{page}")
    private Page<Product> filterProducts(@RequestBody Map<String, String[]> filters, @PathVariable String group, @PathVariable(required = false) int page) {
        return productService.filterProducts(filters, group, PageRequest.of(page, 15, Sort.Direction.ASC, "pic"));
    }

    @PostMapping("/search")
    private List<Product> searchProducts(@RequestBody String searchRequest) {
        return productService.searchProducts(searchRequest);
    }


}
