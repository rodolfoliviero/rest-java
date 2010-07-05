package app.controllers;

import static br.com.caelum.vraptor.view.Results.representation;

import javax.persistence.EntityManager;

import app.models.Order;
import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.Routes;
import br.com.caelum.vraptor.view.Status;

@Resource
public class OrderController {
	
	private final EntityManager entityManager;
	private final Result result;
	private final Status status;
	private final Routes routes;
	
	public OrderController(EntityManager entityManager, Result result, Status status, Routes routes) {
		this.entityManager = entityManager;
		this.result = result;
		this.status = status;
		this.routes = routes;
	}
	
	@Post
	@Path("/orders")
	@Consumes
	public void add(Order order) {
		System.out.println("add order");
		entityManager.persist(order);
		System.out.println("created order =" + order.getId());
		
		routes.uriFor(OrderController.class).get(order);
		status.created(routes.getUri());
	}

	@Get
	@Path("/orders/{order.id}")
	public void get(Order order) {
		order = entityManager.find(Order.class, order.getId());
		if (order != null) {
			result.use(representation()).from(order).serialize();
		} else {
			System.out.println("order not found");
			status.notFound();
		}
	}
}