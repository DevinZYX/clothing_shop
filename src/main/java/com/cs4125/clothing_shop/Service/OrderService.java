package com.cs4125.clothing_shop.Service;

import com.cs4125.clothing_shop.Dto.Cart.CartDto;
import com.cs4125.clothing_shop.Dto.Cart.CartItemDto;
import com.cs4125.clothing_shop.Dto.CheckoutItemDto;
import com.cs4125.clothing_shop.Model.Order;
import com.cs4125.clothing_shop.Model.OrderItem;
import com.cs4125.clothing_shop.Model.Product;
import com.cs4125.clothing_shop.Model.User.User;
import com.cs4125.clothing_shop.Observer.OrderObserver;
import com.cs4125.clothing_shop.Repository.OrderItemsRepo;
import com.cs4125.clothing_shop.Repository.OrderRepo;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
@Transactional
public class OrderService {


    @Autowired
    private CartService cartService;

    @Autowired
    OrderRepo orderRepository;

    @Autowired
    OrderItemsRepo orderItemsRepository;


    private List<OrderObserver> observers = new ArrayList<>();

    public OrderService(ProductService productService) {

        observers.add(productService);
    }
    public void placeOrder(User user) {
        // first let get cart items for the user
        CartDto cartDto = cartService.listCartItems(user);

        List<CartItemDto> cartItemDtoList = cartDto.getcartItems();

        // create the order and save it
        Order newOrder = new Order();
        newOrder.setCreatedDate(new Date());
        newOrder.setUser(user);
        newOrder.setTotalPrice(cartDto.getTotalCost());
        orderRepository.save(newOrder);

        for (CartItemDto cartItemDto : cartItemDtoList) {
            // create orderItem and save each one
            OrderItem orderItem = new OrderItem();
            orderItem.setCreatedDate(new Date());
            orderItem.setPrice(cartItemDto.getProduct().getPrice());
            orderItem.setProduct(cartItemDto.getProduct());
            orderItem.setQuantity(cartItemDto.getQuantity());
            orderItem.setOrder(newOrder);
            // add to order item list
            orderItemsRepository.save(orderItem);

        }

        for (OrderObserver observer : observers) {
            observer.onOrderPlaced(newOrder);
        }

        //
        cartService.deleteUserCartItems(user);
    }

    public List<Order> listOrders(User user) {
        return orderRepository.findAllByUserOrderByCreatedDateDesc(user);
    }
}
