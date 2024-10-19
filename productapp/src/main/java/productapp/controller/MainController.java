package productapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import productapp.dao.ProductDao;
import productapp.model.Product;

@Controller
public class MainController {
	@Autowired
	private ProductDao productDao;
	
	@RequestMapping("/")
	public String home(Model m)
	{
		List<Product> products = productDao.getAllProducts();
		m.addAttribute("products", products);
		return "index";
	}
	//show add product form
	@RequestMapping("/addProduct")
	public String addProduct(Model m)
	{
		System.out.println("add product method");
		m.addAttribute("title", "Add Product");
		return "productForm";
	}
	//handle product form
	@RequestMapping(value= "/handle-form", method=RequestMethod.POST)
	public RedirectView handleForm(@ModelAttribute Product product, HttpServletRequest req)
	{
		RedirectView rv = new RedirectView();
		System.out.println("handling form method");
		productDao.createProduct(product);
		rv.setUrl(req.getContextPath() + "/");
		return rv;
	}
	//delete
	@RequestMapping("/delete/{productId}")
	public RedirectView deleteProduct(@PathVariable("productId") int productId ,HttpServletRequest req)
	{
		RedirectView rv = new RedirectView();
		productDao.deleteProduct(productId);
		rv.setUrl(req.getContextPath()+"/");
		return rv;
	}
	//update
	@RequestMapping("/update/{productId}")
	public String updateProduct(@PathVariable("productId") int productId, Model m)
	{
		Product p = productDao.getSinleProduct(productId);
		System.out.println("Product Update form displayed");
		m.addAttribute("product", p);
		return "/updateForm";
	}
	//handle update
	@RequestMapping(value="/handle-update", method=RequestMethod.POST)
	public RedirectView handleUpdate(@ModelAttribute Product product, HttpServletRequest req)
	{
		System.out.println("Into the product update handle controller");
		RedirectView rv = new RedirectView();
		productDao.updateProduct(product);
		rv.setUrl(req.getContextPath() + "/");
		return rv;
	}
}
