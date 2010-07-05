package app.client;

import java.net.URI;
import java.net.URISyntaxException;

import app.models.Order;
import br.com.caelum.restfulie.Resources;

public class Entry {
	
	public static void main(String[] args) throws Exception {
		String[] args2 = {"http://localhost:8080/orders"};
		URI uri = processCommandLineArgs(args2);
		happyPathTest(uri);
    }

    private static void happyPathTest(URI uri) throws Exception {
    	Resources resources = new MappingConfig().getServer();

        // Place the order
        System.out.println(String.format("About to start happy path test. Placing order at [%s] via POST", uri.toString()));
        Order order = createOrder();
        order = resources.entryAt(uri).post(order);
        System.out.println("order = " + order);
        System.out.println(String.format("Order placed at [%s]", order.getSelfUri()));
    }

	private static Order createOrder() {
		Order order = new Order();
		return order;
	}
    

    private static URI processCommandLineArgs(String[] args) throws URISyntaxException {
        if(args.length != 1) {
            System.out.println("Must specify entry point URI as the only command line argument ");
            System.exit(1);
        } else {
            System.out.println("Binding to service at: " + args[0]);
        }
        return new URI(args[0]);
    }

}
