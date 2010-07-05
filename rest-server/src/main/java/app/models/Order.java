package app.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import app.controllers.OrderController;
import br.com.caelum.vraptor.restfulie.Restfulie;
import br.com.caelum.vraptor.restfulie.hypermedia.HypermediaResource;
import br.com.caelum.vraptor.restfulie.relation.Relation;
import br.com.caelum.vraptor.restfulie.resource.Cacheable;

@Entity
@Table(name="pedido")
@XStreamAlias("order")
public class Order implements HypermediaResource, Cacheable {
	
	@Id @GeneratedValue
	private Long id;
	
	private String name;
	
	private transient String status;
	
	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public List<Relation> getRelations(Restfulie control) {
		System.out.println("creating relation");
		control.relation("self").uses(OrderController.class).get(this);
		
		//if (status.equals("self")) {
		//}
		return control.getRelations();
	}

	/**
	 * Cacheable for two hours.
	 */
	public int getMaximumAge() {
		return 2 * 60 * 60; 
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
}