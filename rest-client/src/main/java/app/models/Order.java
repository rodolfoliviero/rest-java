package app.models;

import static br.com.caelum.restfulie.Restfulie.resource;

import java.net.URISyntaxException;

import br.com.caelum.restfulie.Resource;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("order")
public class Order {
	
	private Long id;
	
	private String name;
	
	public String getSelfUri() throws URISyntaxException {
		Resource resource = resource(this);
		System.out.println(resource);
		
		return resource.getRelation("self").getHref();
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
