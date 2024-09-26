package OneTOMany_Bidirections;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
	
	@Autowired
	private OrderItemRepository orditRepo;
	
	@Autowired
	private OrdersRepository ordRepo;
	
	
	public void insertOrder() {
		
//	    Order Obj
		
		Orders ord=new Orders();
		ord.setOrderDate("2024-06-19");
		
//		OrderItem obj -1
		
		OrderItems orditem1=new OrderItems();
		orditem1.setItemName("Mobile 3");
		orditem1.setPrice(4000);
		orditem1.setOrder(ord);
		
//		OrderItem obj -2
		
		OrderItems orditem2=new OrderItems();
		orditem2.setItemName("Mobile 4");
		orditem2.setPrice(4000);
		orditem2.setOrder(ord);
		
		ArrayList<OrderItems> items=new ArrayList<>();
		items.add(orditem1);
		items.add(orditem2);
		ord.setOrderItems(items);
		
		ordRepo.save(ord);
		System.out.println("Done");
		
	}
	
	public void insertOrderItems() {
		
//		Orders Obj
		
		Orders ord=new Orders();
		ord.setOrderDate("2024-06-15");

//		OrderItems-obj
		
		OrderItems ordit=new OrderItems();
		ordit.setItemName("Mobile 5");
		ordit.setPrice(5000);
		ordit.setOrder(ord);
		
		orditRepo.save(ordit);
		System.out.println("Element Added Sucessfully");
		
	}

}
