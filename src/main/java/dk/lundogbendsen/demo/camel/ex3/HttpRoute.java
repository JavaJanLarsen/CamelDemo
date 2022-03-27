package dk.lundogbendsen.demo.camel.ex3;

import org.apache.camel.builder.endpoint.EndpointRouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class HttpRoute extends EndpointRouteBuilder {

	@Override
	public void configure() throws Exception {
		String site="www.andrewdavidson.com/gibberish/";
		from(quartz("gibberishTimer").cron("0/10+*+*+*+*+?"))
			.routeId(getClass().getSimpleName())
			.to(http(site))
			.log("${header.CamelHttpResponseCode}")
			.to(file("C:/CamelDemo/to").fileName("gibberish_${date:now:yyyyMMdd_hhmmss}.html"));
	}
}
