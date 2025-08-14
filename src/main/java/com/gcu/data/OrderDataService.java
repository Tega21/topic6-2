package com.gcu.data;

import com.gcu.data.entity.OrderEntity;
import com.gcu.data.repository.OrdersRepository;
import com.gcu.model.OrderModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Qualifier("orderDataService")
public class OrderDataService implements DataAccessInterface<OrderModel> {

    @Autowired
    private OrdersRepository ordersRepository;

    @Override
    public List<OrderModel> findAll() {
        List<OrderEntity> ordersEntity = ordersRepository.findAll();
        List<OrderModel> ordersDomain = new ArrayList<>();

        // Convert OrderEntity to OrderModel
        for (OrderEntity entity : ordersEntity) {
            ordersDomain.add(new OrderModel(
                    entity.getId(),
                    entity.getOrderNo(),
                    entity.getProductName(),
                    entity.getPrice(),
                    entity.getQuantity()
            ));
        }

        return ordersDomain;
    }

    @Override
    public OrderModel findById(String id) {
        // Use the built-in findById method from MongoRepository
        Optional<OrderEntity> entity = ordersRepository.findById(id);

        if (entity.isPresent()) {
            OrderEntity orderEntity = entity.get();
            return new OrderModel(
                    orderEntity.getId(),
                    orderEntity.getOrderNo(),
                    orderEntity.getProductName(),
                    orderEntity.getPrice(),
                    orderEntity.getQuantity()
            );
        }
        return null;
    }

    @Override
    public boolean create(OrderModel order) {
        try {
            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setOrderNo(order.getOrderNo());
            orderEntity.setProductName(order.getProductName());
            orderEntity.setPrice(order.getPrice());
            orderEntity.setQuantity(order.getQuantity());

            ordersRepository.save(orderEntity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(OrderModel order) {
        try {
            if (order.getId() != null && ordersRepository.existsById(order.getId())) {
                OrderEntity orderEntity = new OrderEntity();
                orderEntity.setId(order.getId());
                orderEntity.setOrderNo(order.getOrderNo());
                orderEntity.setProductName(order.getProductName());
                orderEntity.setPrice(order.getPrice());
                orderEntity.setQuantity(order.getQuantity());

                ordersRepository.save(orderEntity);
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(OrderModel order) {
        try {
            if (order.getId() != null && ordersRepository.existsById(order.getId())) {
                ordersRepository.deleteById(order.getId());
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}