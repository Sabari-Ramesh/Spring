package OneTOOne_Bidirection;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name="orders")
public class Order {

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public OrderItems getOrderitem() {
		return orderitem;
	}

	public void setOrderitem(OrderItems orderitem) {
		this.orderitem = orderitem;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String orderDate;
	
	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
	private OrderItems orderitem;
	
	
}
