package com.matthew.file;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class CopyFilesCamelMultiRoute {

    public static void main(String[] args) {
        CamelContext context= new DefaultCamelContext();

        try{
            context.addRoutes(new RouteBuilder() {
                @Override
                public void configure() throws Exception {
                    from("file:data/input?noop=true")
                            // Approach 2
                            .to("log:?level=INFO&showBody=true&showHeaders=true")
                            /* Approach 1
                            .log("Received Message is ${body}") //and Headers are ${headers}
                            */
                            .to("file:data/output")
                            .to("file:data/output1");

                    from("file:data/input1?noop=true")
                            .to("file:data/output1");

                }
            });

            context.start();
            Thread.sleep(5000);
            context.stop();

        }catch(Exception e){
            System.out.println("Inside Exception : " + e);
        }

    }
}
