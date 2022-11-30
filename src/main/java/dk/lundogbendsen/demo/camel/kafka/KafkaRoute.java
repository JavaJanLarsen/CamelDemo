package dk.lundogbendsen.demo.camel.kafka;

import org.apache.camel.builder.endpoint.EndpointRouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class KafkaRoute extends EndpointRouteBuilder {
	@Override
	public void configure() throws Exception {
		from(file("C:/CamelDemo/from"))
		.routeId("KafkaProducerRoute")
		.to(kafka("sos-topic").brokers("localhost:9092"));
	
		from(kafka("sos-topic").brokers("localhost:9092"))
		.routeId("KafkaConsumerRoute")
		.log("${body}");
	}
	
}
