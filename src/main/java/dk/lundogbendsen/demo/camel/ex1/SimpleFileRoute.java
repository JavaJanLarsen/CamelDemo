package dk.lundogbendsen.demo.camel.ex1;

import org.apache.camel.builder.endpoint.EndpointRouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class SimpleFileRoute extends EndpointRouteBuilder {
	 	
		@Override
	    public void configure() throws Exception {
			from(file("C:/CamelDemo/from").readLockMinLength(0))
				.routeId("SimpleFileRoute")
		        .to(file("C:/CamelDemo/to"));
		}
}
