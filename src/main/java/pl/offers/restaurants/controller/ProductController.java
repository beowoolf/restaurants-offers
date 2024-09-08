package pl.offers.restaurants.controller;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.offers.restaurants.dto.DishDTO;
import pl.offers.restaurants.dto.IngredientDTO;
import pl.offers.restaurants.dto.ProductDTO;
import pl.offers.restaurants.service.ProductService;

import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/products", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

    private final ProductService productService;

    @GetMapping
    @JsonView(ProductListListView.class)
    public List<ProductDTO> get() {
        return productService.getAll();
    }

    @GetMapping("/{uuid}")
    @JsonView(ProductView.class)
    public ProductDTO get(@PathVariable UUID uuid) {
        return productService.getByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Transactional
    @PutMapping("/{uuid}")
    public void put(@PathVariable UUID uuid, @RequestBody @Valid ProductDTO json) {
        productService.put(uuid, json);
    }

    @Transactional
    @DeleteMapping("/{uuid}")
    public void delete(@PathVariable UUID uuid) {
        productService.delete(uuid);
    }

    interface ProductListListView extends ProductDTO.View.Basic {
    }

    interface ProductView extends ProductDTO.View.Extended, IngredientDTO.View.Basic, DishDTO.View.Basic {
    }

}
