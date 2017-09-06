package com.matthew.file;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

/* step 1: Use Camel instead of traditional java file copy (fileinputStream or fileOutputStream
   step 2: Enable logging like event log => using log4j and slf4j
   step 3: Consumer and producer
           consumers wrap the payload and create the exchange
           Two types of consumers - Event Driven and Polling Consumer
           Event Driven:
           Consumers reacts to events in this case. These are called asynchronous consumer
           e.g jms, kafka
           Polling Consumer:
           These are called synchronous consumer. Polls and reads teh content from
           a source on a regular interval.
           e.g Polling a directory on a regular interval in a ftp server.
           Producers in camel refers to an entity which can create and send a message to an endpoint
           Producers converts the exchange according to the endpoint behind the scenes.

           https://stackoverflow.com/questions/27594006/camels-producer-consumer-confusion
           https://stackoverflow.com/questions/2119128/apache-camel-producers-and-consumers

           https://github.com/dilipSundar/TeachApacheCamel
           http://camel.apache.org/components.html
           http://camel.apache.org/enterprise-integration-patterns.html
           https://dzone.com/articles/open-source-integration-apache
 */
public class CopyFileCamel {
    public static void main(String[] args){

        /*
        Camel uses a Java based Routing Domain Specific Language (DSL) or an Xml Configuration to configure routing and mediation rules
        which are added to a CamelContext to implement the various Enterprise Integration Patterns, EIP.

        When you use Camel you typically have to start the CamelContext which will start all the various components
        and endpoints and activate the routing rules until the context is stopped again.
        CamelContext is in a similar way to the Spring ApplicationContext

        An Endpoint acts rather like a URI or URL in a web application or a Destination in a JMS system; you can communicate with an endpoint;
        either sending messages to it or consuming messages from it. You can then create a Producer or Consumer on an Endpoint to exchange messages with it.

        http://camel.apache.org/lifecycle.html
        The CamelContext provides methods to control its lifecycle:

            start
            stop
            suspend Camel 2.5
            resume Camel 2.5
         */
        CamelContext context = new DefaultCamelContext();
        try{
            //RouteBuilder => create routing rules using the DSL
            context.addRoutes(new RouteBuilder() {
                @Override
                public void configure() throws Exception {
                    // If noop=true, Camel will set idempotent=true as well,
                    // to avoid consuming the same files over and over again.
                    // http://camel.apache.org/file2.html
                    from("file:data/input?noop=true")     //consumer starts polling files from its directory which instructs its Endpoint to create an Exchange
                            .to("file:data/output");          //This Exchange is handed over to the FileProducer
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
