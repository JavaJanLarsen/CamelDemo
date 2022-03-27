package dk.lundogbendsen.demo.camel.ex2;

import org.apache.camel.builder.endpoint.EndpointRouteBuilder;
import org.apache.camel.processor.aggregate.zipfile.ZipAggregationStrategy;
import org.springframework.stereotype.Component;

//@Component
public class AggregateRoute extends EndpointRouteBuilder {
	 	
		@Override
	    public void configure() throws Exception {
			from(file("C:/CamelDemo/from").readLockMinLength(0))
				.routeId("AggregateRoute")
				.aggregate(new ZipAggregationStrategy()).constant(true).completionTimeout(5000)
				.to(file("C:/CamelDemo/to").fileName("arkiv.zip"));

//				.aggregate(constant("all"), new FileNameAggregationStrategy(getContext(), "Nye filer:")).completionTimeout(5000)
//				.to(file("C:/CamelDemo/to").fileName("list.txt"));
		}
}
