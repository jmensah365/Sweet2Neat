package com.skillstorm.project_one.Services;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.skillstorm.project_one.Models.Orders;
import com.skillstorm.project_one.Repositories.OrdersRepo;

/**
 * Service class for managing orders-related operations.
 */
@Service
public class OrdersService {
    
    private OrdersRepo repo;

    // Constructor injection for OrdersRepo
    public OrdersService(OrdersRepo repo) {
        this.repo = repo;
    }

    /**
     * Retrieve all Orders 
     * @return Iterable of Orders entities
     */
    public Iterable<Orders> findAll() {
        return repo.findAllByOrderByIdAsc();
    }

    /**
     * Retrieve an Orders by its ID
     * @param id The ID of the Orders entity
     * @return Optional containing the Orders entity, if found
     */
    public Optional<Orders> getOrdersById(int id) {
        return repo.findById(id);
    }

    /**
     * Retrieve Orders entities by their status
     * @param status The status of the Orders entities
     * @return Iterable of Orders entities with the specified status
     */
    public Iterable<Orders> getOrdersByStatus(String status) {
        return repo.findByStatus(status);
    }

    /**
     * Create a new Orders entity
     * @param orders The Orders entity to create
     * @return The created Orders entity
     */
    public Orders createOrders(Orders orders) {
        return repo.save(orders);
    }

    /**
     * Update an existing Orders entity.
     * @param id The ID of the existing Orders entity
     * @param order The Orders entity with updated data
     */
    public void updateOrders(int id, Orders order) {
        if (!repo.existsById(id)) {
            throw new NoSuchElementException("Order with id " + id + " does not exist");
        }
        order.setId(id);
        repo.save(order);
    }

    /**
     * Delete an Orders entity by its ID
     * @param id The ID of the Orders entity to delete
     */
    public void deleteOrder(int id) {
        repo.deleteById(id);
    }
}
