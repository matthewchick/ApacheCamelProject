package com.matthew.direct;

import org.apache.camel.builder.RouteBuilder;

public class SampleMockRoute extends RouteBuilder{

    public void configure() throws Exception {
        // use mock:output
        /*
        Remember that Mock is designed for testing. When you add Mock endpoints to a route,
        each Exchange sent to the endpoint will be stored (to allow for later validation) in memory
        until explicitly reset or the JVM is restarted.
        */
        from("direct:sampleInput")
                .log("Received Message is ${body} and Headers are ${headers}")
                .to("mock:output");

    }
}
