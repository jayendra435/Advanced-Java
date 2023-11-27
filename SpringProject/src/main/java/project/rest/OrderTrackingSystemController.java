package project.rest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import project.entities.AllOrderItemList;
import project.entities.Customer;
import project.entities.CustomerRepo;
import project.entities.Order;
import project.entities.OrderItem;
import project.entities.OrderItemCompositePk;
import project.entities.OrderItemList;
import project.entities.OrderItemRepo;
import project.entities.OrderList;
import project.entities.OrderRepo;
import project.entities.OrderView;
import project.entities.Product;
import project.entities.ProductRepo;

@RestController
public class OrderTrackingSystemController {
	@Autowired
	CustomerRepo customerRepo;

	@Autowired
	OrderRepo orderRepo;

	@Autowired
	OrderItemRepo orderItemRepo;

	@Autowired
	ProductRepo productRepo;

	// ======= 01 ======//
	// <-----LIST CUSTOMERS----->//PAGENATION
	@CrossOrigin
	@GetMapping("/customers")
	@Operation(summary = "List Customers", description = "Lists all customers deatils")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Success...!") })
	public Page<Customer> getAllCustomers(@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
		PageRequest pageable = PageRequest.of(page, size);
		return customerRepo.findAll(pageable);
	}

	// <-----ADD CUSTOMER----->
	@PostMapping("/customers/add")
	@PreAuthorize("hasRole('ADMIN')")
	@Operation(summary = "Add Customer", description = "Adds new customer into the customer table with his full details")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully added the customer"),
			@ApiResponse(responseCode = "400", description = "Bad Request.! Validation Error...!"),
			@ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR ") })
	public Customer addNewCustomer(@Valid @RequestBody Customer customer) {
		try {
			var Customer = customerRepo.findByEmail(customer.getEmail());
			if (!(Customer == null))
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer Email Already exists");
			customerRepo.save(customer);
			return customer;
		} catch (DataAccessException ex) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
		}
	}

	// <-----DELETE CUSTOMER----->
	@DeleteMapping("/customers/delete/{customerId}")
	@PreAuthorize("hasRole('ADMIN')")
	@Operation(summary = "Delete Customer", description = "Deletes all the details of a particular customer by customer Id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully deleted the customer"),
			@ApiResponse(responseCode = "404", description = "Customer Id Not Found...!") })
	public void deleteOneCustomer(@PathVariable("customerId") int customerId) {
		var optCustomer = customerRepo.findById(customerId);
		if (optCustomer.isPresent())
			customerRepo.deleteById(customerId);
		else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer ID Not Found!");
	}

	// <-----UPDATE CUSTOMER----->
	@PutMapping("/customers/update/{customerId}")
	@PreAuthorize("hasRole('ADMIN')")
	@Operation(summary = "Update customer details", description = "Updates the customerName,  email,mobile of the customer by customer Id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully updated the customer"),
			@ApiResponse(responseCode = "400", description = "Bad Request.! Validation Error...!"),
			@ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR while processing request ") })
	public void updateProduct(@PathVariable("customerId") int customerId, @Valid @RequestBody Customer customer) {
		var optCustomer = customerRepo.findById(customerId);
		if (optCustomer.isPresent()) {
			var customerobj = optCustomer.get();
			customerobj.setCustomerName(customer.getCustomerName());
			customerobj.setEmail(customer.getEmail());
			customerobj.setMobile(customer.getMobile());
			customerRepo.save(customerobj);
		} else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer ID Not Found!");
	}

	// ======= 02 ======//
	// <-----LIST PRODUCTS----->//PAGENATION
	@GetMapping("/products")
	@Operation(summary = "List Products", description = "Lists all Products details")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Success...!") })
	public Page<Product> getAllProducts(@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
		PageRequest pageable = PageRequest.of(page, size);
		return productRepo.findAll(pageable);
	}

	// <-----ADD PRODUCT----->
	@PostMapping("/products/add")
	@Operation(summary = "Add Product", description = "Adds new product into the product table with  full details")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully added the product"),
			@ApiResponse(responseCode = "400", description = "Bad Request.! Validation Error...!"),
			@ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR ") })
	public Product addNewCustomer(@Valid @RequestBody Product product) {
		try {
			var optProduct = productRepo.findById(product.getProductId());
			if (optProduct.isPresent())
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product ID Already exists");
			productRepo.save(product);
			return product;
		} catch (DataAccessException ex) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
		}
	}

	// <-----DELETE PRODUCT----->
	@DeleteMapping("/products/delete/{productId}")
	@Operation(summary = "Delete Product", description = "Deletes all the details of a particular product by product Id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully deleted the product"),
			@ApiResponse(responseCode = "404", description = "Product Id Not Found...!") })
	public void deleteOneProduct(@PathVariable("productId") String productId) {
		var optProduct = productRepo.findById(productId);
		if (optProduct.isPresent())
			productRepo.deleteById(productId);
		else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product ID Not Found!");

	}

	// <-----UPDATE PRODUCT----->
	@PutMapping("/products/update/{productId}")
	@Operation(summary = "Update product details", description = "Updates the productname, Description,Price of the product by product Id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully updated the product"),
			@ApiResponse(responseCode = "400", description = "Bad Request.! Validation Error...!"),
			@ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR while processing request ") })
	public void updateProduct(@PathVariable("productId") String productId, @Valid @RequestBody Product product) {
		var optProduct = productRepo.findById(productId);
		if (optProduct.isPresent()) {
			var productobj = optProduct.get();
			productobj.setProductName(product.getProductName());
			productobj.setDescription(product.getDescription());
			productobj.setPrice(product.getPrice());
			productRepo.save(product);
		} else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product ID Not Found!");
	}

	// ======= 03 ======//
	// <-----ADD ORDER WITH ORDERITEMS----->
	@PostMapping("/orders/orderItems/add")
	@Operation(summary = "Add Order", description = "Adds new order with order tiems which will be effected in both tables  ")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully added the order "),
			@ApiResponse(responseCode = "400", description = "Bad Request.! Validation Error...!"),
			@ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR ") })
	public OrderView addNewOrder(@Valid @RequestBody OrderView orderView) {
		try {
			var optProductId = orderRepo.findById(orderView.getOrdViewId());
			if (optProductId.isPresent())
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Order ID Already exists");
			var optCustomerId = customerRepo.findById(orderView.getCustId());
			if (optCustomerId.isEmpty())
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer Id Not Found");
			if (!orderView.getStatus().equals("N"))
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Only 'New(N)' orders are allowed.");
			Order obj = new Order();
			obj.setOrderId(orderView.getOrdViewId());
			obj.setOrderDate(orderView.getOrderDate());
			obj.setCustId(orderView.getCustId());
			obj.setStatus(orderView.getStatus());
			obj.setDeliveryDate(orderView.getDeliveryDate());
			orderRepo.save(obj);

			for (OrderItem o : orderView.getOrderItems()) {
				OrderItem obj1 = new OrderItem();
				OrderItemCompositePk key = new OrderItemCompositePk(); // Create a new OrderItemCompositePk
				key.setOrdId(o.getOrderItemKey().getOrdId());
				key.setProdId(o.getOrderItemKey().getProdId());
				obj1.setOrderItemKey(key); // Set the OrderItemCompositePk in obj1
				obj1.setQty(o.getQty());
				obj1.setPrice(o.getPrice());
				obj1.setOrder(obj);
				orderItemRepo.save(obj1);
			}
			return orderView;
		} catch (DataAccessException ex) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
		}
	}

	// <-----DELETE ORDER WITH ORDERITEMS----->
	@DeleteMapping("/orders/delete/{OrderId}")
	@Operation(summary = "Delete Order", description = "Deletes all the details of a particular order with order items  by order Id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully deleted the order"),
			@ApiResponse(responseCode = "404", description = "Order Id Not Found...!") })
	public void deleteOrder(@PathVariable("OrderId") String orderId) {
		var optOrder = orderRepo.findById(orderId);
		if (optOrder.isPresent()) {
			List<OrderItem> listOrderItems = orderItemRepo.findByOrderItemKeyOrdId(orderId);
			for (var o : listOrderItems)
				orderItemRepo.deleteByOrderItemKeyOrdId(o.getOrderItemKey().getOrdId());
			orderRepo.deleteById(orderId);
		} else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Orders Found For This Order ID...!");

	}

	// ======= 04 ======//
	// <-----UPDATE STATUS OF AN ORDER----->
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/order/status/update/{orderId}")
	@Operation(summary = "Update Status of Order", description = "Updates the status , delivery date of the order by order Id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully updated the status "),
			@ApiResponse(responseCode = "400", description = "Bad Request.! Validation Error...!"),
			@ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR while processing request ") })
	public String updateOrder(@Valid @PathVariable("orderId") String orderId, @RequestParam("status") String status) {
		var optOrder = orderRepo.findById(orderId);
		if (optOrder.isPresent()) {
			var order = optOrder.get();
			if (order.getStatus().equals("D"))
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order Already Delivered");
			else if (order.getStatus().equals("C"))
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order Already Cancelled");
			else {
				order.setStatus(status);
				if (status.equals("C"))
					order.setDeliveryDate(null);
				else {
					Date currentDateWithoutTime = new Date(System.currentTimeMillis());
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					String formattedDate = dateFormat.format(currentDateWithoutTime);
					order.setDeliveryDate(formattedDate);

				}
				orderRepo.save(order);
				return "Status Updated";
			}

		} else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order ID Not Found!");
	}

	// ===== 05, 06 ==== UPSIDE //
	// ======= 07 ======//
	// <-----LIST ORDERS BY CUSTOMER ID ----->
	@GetMapping("/orders/by/customer/{custId}")
	@Operation(summary = "List Orders by customer Id ", description = "Lists all Order details of a customer ")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Success...!") })
	public List<Order> getOrdersByCustomer(@PathVariable("custId") int custId) {
		var optCustomer = customerRepo.findById(custId);
		if (optCustomer.isPresent()) {
			List<Order> ordersList = orderRepo.findByCustId(custId);
			return ordersList;
		} else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer ID Not Found!");
	}

	// ======= 08 ======//
	// <-----LIST ORDERS WITH GIVEN STATUS ----->//PAGENATION
	@GetMapping("/orders/by/status/{status}")
	@Operation(summary = "List Orders by status ", description = "Lists all Order by the status given by the user ")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Success...!") })
	public Page<Order> getOrdersByStatus(@PathVariable("status") String status,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
		PageRequest pageable = PageRequest.of(page, size);
		return orderRepo.findByStatus(status, pageable);
	}

	// ======= 09 ======//
	// <-----LIST ORDERITEMS IN GIVEN ORDER ----->
	@GetMapping("/orderItems/OrderId")
	@Operation(summary = "List OrderItems by Order Id ", description = "Lists all Order Items by the order id given by the user ")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Success...!") })
	public List<OrderItemList> getOrderItems(@RequestParam String orderId) {
		List<OrderItemList> orderItemsList = orderItemRepo.getOrderItemListMethod(orderId);
		return orderItemsList;
	}
	
//	@GetMapping("/orderItems/")
//	public List<OrderItemList> getOrderItems() {
//		List<OrderItemList> orderItemsList = orderItemRepo.getOrderItemListMethod();
//		return orderItemsList;
//	}
	
	

	// ======= 10 ======//
	// <-----LIST PRODUCTS NAME MATCHES STRING ----->//PAGENATION
	@GetMapping("/products/string/{productName}")
	@Operation(summary = "List Products by Name Matching String   ", description = "Retrieve a paginated list of products by searching for a partial name match.  ")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Success...!") })
	public Page<Product> getProductsByString(@PathVariable("productName") String string,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
		PageRequest pageable = PageRequest.of(page, size);
		return productRepo.findByProductNameContaining(string, pageable);
	}

	// ======= 11 ======//
	// <-----LIST ORDERITEMS FOR GIVEN PRODUCT ID ----->
	@GetMapping("/orderItems/by/productId/{productId}")
	@Operation(summary = "List OrderItems by Product Id  ", description = "Retrieve a  list of order items by given product Id   ")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Success...!") })
	public List<AllOrderItemList> getOrderItemsByProduct(@PathVariable("productId") String productId) {
		var optProduct = productRepo.findById(productId);
		if (optProduct.isPresent()) {
			List<AllOrderItemList> orderItemsList = orderItemRepo.getAllOrderItemList(productId);
			return orderItemsList;
		} else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product ID Not Found!");

	}
  
	// ======= 12 ======//
	// <-----ALL ORDER DETAILS FOR GIVEN ORDER ID ----->
	@GetMapping("/orderDetails/by/orderId/{orderId}")
	@Operation(summary = "List Order Details by Order Id  ", description = "Retrieve a  complete details of order for given order Id   ")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Success...!") })
	public List<OrderList> getOrderDetailsByOrderId(@PathVariable("orderId") String orderId) {
		var optProduct = orderRepo.findById(orderId);
		if (optProduct.isPresent()) {
			List<OrderList> ordersList = orderRepo.getOrderDetails(orderId);
			return ordersList;
		} else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order ID Not Found!");
	}

	// ======= 13 ======//
	// <-----ALL PRODUCTS ORDER BY CUSTOMER NAME ----->
	@GetMapping("/products/customer/{customerName}")
	@Operation(summary = "List Products by Customer Name  ", description = "Retrieve a  list of products , and their complete details ordered by the customer name ")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Success...!") })
	public List<Product> getProductsByCustomer(@PathVariable("customerName") String name) {
		Customer obj = customerRepo.findByCustomerName(name);
		if (!(obj == null)) {
			List<Product> productsList = customerRepo.getProductsList(name);
			return productsList;
		} else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer Name Not Found!");
	}

	// ======= 14 ======//
	// <-----ALL ORDERS BETWEEN GIVEN DATES ----->
	
	@GetMapping("/orders/dates/")
	@Operation(summary = "List Products between given dates ", description = "Retrieve a  list of orders between given dates ")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Success...!") })
	public List<Order> getOrdersByDate(
			@Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}") @RequestParam("startDate") String startDate,
			@Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}") @RequestParam("endDate") String endDate) {
		 if (!startDate.matches("\\d{4}-\\d{2}-\\d{2}") || !endDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
			 throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid date format. Use yyyy-MM-dd."); 
		    }
		List<Order> ordersList = orderRepo.getOrdersByDateRange(startDate, endDate);
		return ordersList;
	}

}
