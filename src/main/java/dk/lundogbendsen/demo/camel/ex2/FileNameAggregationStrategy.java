package dk.lundogbendsen.demo.camel.ex2;

import org.apache.camel.AggregationStrategy;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.support.DefaultExchange;

public class FileNameAggregationStrategy implements AggregationStrategy {
	private final CamelContext camelContext;
	private final String firstLine;
	
	public FileNameAggregationStrategy(CamelContext camelContext, String firstLine) {
		this.camelContext = camelContext;
		this.firstLine = firstLine;
	}
	@Override
	public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
		String fileName = (String) newExchange.getIn().getHeader("CamelFileName", String.class);
		
		if (oldExchange == null) { // First aggregation
			oldExchange = new DefaultExchange(camelContext);
			oldExchange.getIn().setBody(firstLine);
		}
		oldExchange.getIn().setBody(oldExchange.getIn().getBody()+"\n"+fileName);
		return oldExchange;
	}

}
