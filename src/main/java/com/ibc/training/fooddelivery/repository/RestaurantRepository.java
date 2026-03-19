package com.ibc.training.fooddelivery.repository;

import com.ibc.training.fooddelivery.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    //gives the bellow functionalities by JpaRepository

    //CRUD operations: save, findById, findAll, deleteById, etc.
    //restaurantRepository.save(restaurant); // Inserts (if new), Updates (if ID exists)

    //Read
    //restaurantRepository.findById(id); //Returns Optional<Restaurant>
    //restaurantRepository.findAll(); //returns all restaurants

    //Delete
    //restaurantRepository.deleteById(id);
    //restaurantRepository.delete(restaurant);

    //Pagination & Sorting
    //Page<Restaurant> page = repository.findAll(PageRequest.of(0, 5));
    //List<Restaurant> list = repository.findAll(Sort.by("name"));

    //Existence Check
    //repository.existsById(id);

    //Count
    //long count = repository.count();

    //Bulk Operations
    //repository.deleteAll();
    //repository.saveAll(list);

    //Flush Operations (Advanced)
    //repository.flush();
    //repository.saveAndFlush(entity);

    //Derived Query Methods (You Can Add)
    //List<Restaurant> findByName(String name);
    //List<Restaurant> findByCuisineType(String cuisineType);
    //👉 Spring will automatically generate SQL 🔥






}