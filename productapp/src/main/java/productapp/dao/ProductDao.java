package productapp.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import productapp.model.Product;

@Component
public class ProductDao {
	@Autowired
	private HibernateTemplate hibernateTemplate;
	//create
	@Transactional
	public int createProduct(Product product)
	{
		int newId = (Integer)this.hibernateTemplate.save(product);
		return newId;
	}
	//get all
	public List<Product> getAllProducts()
	{
		List<Product> products = this.hibernateTemplate.loadAll(Product.class);
		return products;
	}
	//get Single
	public Product getSinleProduct(int id)
	{
		Product p = this.hibernateTemplate.get(Product.class, id);
		return p;
	}
	//delete
	@Transactional
	public void deleteProduct(int id)
	{
		Product p = this.hibernateTemplate.get(Product.class, id);
		this.hibernateTemplate.delete(p);
	}
	//update
	@Transactional
	public void updateProduct(Product p)
	{
		this.hibernateTemplate.update(p);
	}
}
