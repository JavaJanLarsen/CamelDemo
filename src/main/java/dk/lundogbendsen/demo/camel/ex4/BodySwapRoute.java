package dk.lundogbendsen.demo.camel.ex4;

import org.apache.camel.builder.endpoint.EndpointRouteBuilder;

public class BodySwapRoute extends EndpointRouteBuilder {
	 	
	@Override
    public void configure() throws Exception {
		from(direct("start"))
			.choice()
				.when(simple("${header.newBody} != null")).setBody(simple("${header.newBody}"))
			.end()
	        .to(log("root"))
	        .to(file("C:/CamelDemo/to"));					
	}
}
