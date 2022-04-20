package dk.lundogbendsen.demo.camel.ex4;

import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.AdviceWith;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.Test;

public class BodySwapRouteTest extends CamelTestSupport {	
	
	private static final String NEW_BODY = "New Body";


	@Override
	protected RoutesBuilder createRouteBuilder() throws Exception {
		return new BodySwapRoute();
	}

	@Test
	public void testBodySwap() throws Exception {
        AdviceWith.adviceWith(context.getRouteDefinitions().get(0), context, new AdviceWithRouteBuilder() {
            @Override
            public void configure() throws Exception {
                mockEndpoints();
                weaveAddLast().to("mock:end");
            }
        });
        
        getMockEndpoint("mock:log:root").expectedMessageCount(1);
        
        MockEndpoint resultEndpoint = getMockEndpoint("mock:end");
        resultEndpoint.expectedMessageCount(1);
        resultEndpoint.message(0).body().isEqualTo(NEW_BODY);

		template.sendBodyAndHeader("direct:start", "Old body", "newBody", NEW_BODY);

		assertMockEndpointsSatisfied();
	}
}
