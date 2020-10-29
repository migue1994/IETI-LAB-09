package eci.ieti.data;

import eci.ieti.data.model.Customer;
import eci.ieti.data.model.Product;
import eci.ieti.data.model.Todo;
import eci.ieti.data.model.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.PageRequest;

import java.util.List;


@SpringBootApplication
public class Application implements CommandLineRunner {

	private final CustomerRepository customerRepository;
	private final ProductRepository productRepository;
	private final UserRepository userRepository;
	private final TodoRepository todoRepository;

	public Application(CustomerRepository customerRepository, ProductRepository productRepository, UserRepository userRepository, TodoRepository todoRepository) {
		this.customerRepository = customerRepository;
		this.productRepository = productRepository;
		this.userRepository = userRepository;
		this.todoRepository = todoRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) {
		customerRepository.deleteAll();
		userRepository.deleteAll();
		todoRepository.deleteAll();

		userMockData();
		todoMockData();

		customerRepository.save(new Customer("Alice", "Smith"));
		customerRepository.save(new Customer("Bob", "Marley"));
		customerRepository.save(new Customer("Jimmy", "Page"));
		customerRepository.save(new Customer("Freddy", "Mercury"));
		customerRepository.save(new Customer("Michael", "Jackson"));

		System.out.println("Customers found with findAll():");
		System.out.println("-------------------------------");

		customerRepository.findAll().forEach(System.out::println);
		System.out.println();

		productRepository.deleteAll();

		productRepository.save(new Product(1L, "Samsung S8", "All new mobile phone Samsung S8"));
		productRepository.save(new Product(2L, "Samsung S8 plus", "All new mobile phone Samsung S8 plus"));
		productRepository.save(new Product(3L, "Samsung S9", "All new mobile phone Samsung S9"));
		productRepository.save(new Product(4L, "Samsung S9 plus", "All new mobile phone Samsung S9 plus"));
		productRepository.save(new Product(5L, "Samsung S10", "All new mobile phone Samsung S10"));
		productRepository.save(new Product(6L, "Samsung S10 plus", "All new mobile phone Samsung S10 plus"));
		productRepository.save(new Product(7L, "Samsung S20", "All new mobile phone Samsung S20"));
		productRepository.save(new Product(8L, "Samsung S20 plus", "All new mobile phone Samsung S20 plus"));
		productRepository.save(new Product(9L, "Samsung S20 ultra", "All new mobile phone Samsung S20 ultra"));

		System.out.println("Paginated search of products by criteria:");
		System.out.println("-------------------------------");

		productRepository.findByDescriptionContaining("plus", PageRequest.of(0, 5)).stream()
				.forEach(System.out::println);

		System.out.println();

		System.out.println("-------------------------------");

		System.out.println("Todos that are assigned to given user and have priority greater equal to 5");

		todoRepository.existsByResponsible().forEach(System.out::println);

	}

	private void userMockData(){
		for(int i = 0; i < 10; i++){
			User u = new User("user"+Integer.toString(i),"email"+i);
			userRepository.save(u);
		}
	}

	private void todoMockData(){
		for(int i = 0; i < 25 ; i ++){
			Todo td = new Todo("todo "+Integer.toString(1+i), i, "Oct "+Integer.toString(6+i) +" - 2020", "user", "pending");
			todoRepository.save(td);
		}
	}
}
