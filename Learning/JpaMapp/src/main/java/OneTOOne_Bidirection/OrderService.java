package OneTOOne_Bidirection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OrderService {
	@Autowired
	private OrderRepository ordRepo;
	@Autowired
	private OrderItemsRepository ordItmRepo;

	public void createOrderManually() {
		Order order = new Order();
		order.setOrderDate("2024-09-24");
		OrderItems orderItems = new OrderItems();
		orderItems.setItemName("Watch");
		orderItems.setOrder(order);
		order.setOrderitem(orderItems);
		ordRepo.save(order);
		System.out.println("Order and Order Items saved successfully!");
	}
	
	public void CreatedOrderitesManually() {
		OrderItems ordItem=new OrderItems();
		ordItem.setItemName("Redmi");
		ordItem.setQuantity(1);
		
		Order ord=new Order();
		
		ord.setOrderDate("2024-05-10");
		ordItem.setOrder(ord);
		
		ordItmRepo.save(ordItem);
		System.out.println("Done");
	}
}
