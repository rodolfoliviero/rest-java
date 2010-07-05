package app.client;

import app.models.Order;
import br.com.caelum.restfulie.Resources;
import br.com.caelum.restfulie.Restfulie;

public class MappingConfig {

    private Resources server = Restfulie.resources();
    
    public MappingConfig() {
        server.configure(Order.class);
    }

	public Resources getServer() {
		return server;
	}
}
