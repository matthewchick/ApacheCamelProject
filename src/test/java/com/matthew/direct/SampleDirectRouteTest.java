package com.matthew.direct;


import org.apache.camel.Exchange;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

import java.io.File;
import java.util.Scanner;

public class SampleDirectRouteTest extends CamelTestSupport {

    public RoutesBuilder createRouteBuilder() throws Exception {
        return new SampleDirectRoute();
    }

    @Test
    public void sampleRouteTest() throws InterruptedException {

        String result;
        System.out.print("Please enter a message: ");
        Scanner input = new Scanner(System.in);
        result = input.nextLine();
        /**
         * Producer Template. push a message to the endpoint using the producer template
         */
        template.sendBody("direct:sampleInput", result);

        Thread.sleep(5000);

        File file = new File("sampleOutput");

        assertTrue(file.isDirectory());

        /**
         * Consumer Template.
         */
        Exchange exchange = consumer.receive("file:sampleOutput");

        System.out.println("Received body is :" + exchange.getIn().getBody());
        System.out.println("File Name is :" + exchange.getIn().getHeader("CamelFileName"));

        assertEquals("output.txt", exchange.getIn().getHeader("CamelFileName"));

    }
}
