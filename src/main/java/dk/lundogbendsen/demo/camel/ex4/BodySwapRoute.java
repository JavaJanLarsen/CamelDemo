package dk.lundogbendsen.demo.camel.ex4;

import org.apache.camel.builder.endpoint.EndpointRouteBuilder;

/*
 * Lab14
 * This class is for testing using dk.lundogbendsen.camel.lab14.Lab14Test in the src\test\java folder
 */
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
