package com.example.restaurant_system.restaurant.repository;

import com.example.restaurant_system.restaurant.document.Restaurant;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface RestaurantRepository
        extends ElasticsearchRepository<Restaurant, String> {
}
