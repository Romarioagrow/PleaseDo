package server.controllers;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import server.domain.Order;
import server.domain.User;
import server.repos.UserRepo;
import server.services.OrderService;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Log
@RestController
@AllArgsConstructor
@RequestMapping("/api/order")
public class OrderApiController {
    private final OrderService orderService;
    private final UserRepo userRepo;

    @PostMapping("/addProduct")
    private Order addProductToOrder(@RequestBody String productID, @AuthenticationPrincipal User user) {
        return orderService.addProductToOrder(productID, user);
    }

    @PostMapping("/orderedProducts")
    private LinkedList<Object> getOrderedProducts(@AuthenticationPrincipal User user) {
        return orderService.getOrderData(user);
    }

    @PostMapping("/deleteProduct")
    private LinkedList<Object> deleteProductFromOrder(@RequestBody String productID, @AuthenticationPrincipal User user) {
        return orderService.deleteProductFromOrder(productID, user);
    }

    @PostMapping("/increaseAmount")
    private LinkedList<Object> increaseAmount(@RequestBody String productID, @AuthenticationPrincipal User user ) {
        return orderService.increaseAmount(productID, user);
    }

    @PostMapping("/decreaseAmount")
    private LinkedList<Object> decreaseAmount(@RequestBody String productID, @AuthenticationPrincipal User user ) {
        return orderService.decreaseAmount(productID, user);
    }

    @PostMapping("/acceptOrder")
    private boolean acceptOrder(@RequestBody Map<String, String> orderDetails) {
        return orderService.acceptOrder(orderDetails);
    }

    @GetMapping("/getAcceptedOrders")
    private List<Order> getAcceptedOrders(@AuthenticationPrincipal User user) {
        return orderService.getAllAcceptedOrders(user);
    }

    @GetMapping("/getCompletedOrders")
    private List<Order> getCompletedOrders(@AuthenticationPrincipal User user) {
        return orderService.getUserCompletedOrders(user);
    }

    @GetMapping("/checkSessionOrder")
    private boolean checkSessionOrder() {
        return orderService.hasCurrentSessionOrder();
    }

    @GetMapping("/showUserBonus")
    private int showUserBonus(@AuthenticationPrincipal User user) {
        return userRepo.findByUserID(user.getUserID()).getBonus();
    }
}
