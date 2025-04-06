
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gcu.business.OrdersBusinessServiceInterface;
import com.gcu.business.SecurityBusinessService;
import com.gcu.model.LoginModel;
import com.gcu.model.OrderModel;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private OrdersBusinessServiceInterface service;
	@Autowired
	private SecurityBusinessService security;
	
	@GetMapping("/")
	public String display(Model model) {
		// Display Login Form View
		model.addAttribute("title", "Login Form");
		model.addAttribute("loginModel", new LoginModel());
		return "login";
	}

	@PostMapping("/doLogin")
	public String doLogin(@Valid LoginModel loginModel, BindingResult bindingResult, Model model)
	{
		// Call to the test() method
		service.test();		
		// Call to the authenticate method
		security.authenticate("Pavle", "Pass");
	    // Check for validation errors
	    if (bindingResult.hasErrors())
	    {
	        model.addAttribute("title", "Login Form");
	        return "login";
	    }

	    // Call the getOrders() method
	    List<OrderModel> orders = service.getOrders();
	    // Display the Orders View
	    model.addAttribute("title", "My Orders");
	    model.addAttribute("orders", orders);
	    return "orders";
	}

}