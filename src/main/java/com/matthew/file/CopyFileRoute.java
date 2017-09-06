package com.matthew.file;

import org.apache.camel.builder.RouteBuilder;

public class CopyFileRoute extends RouteBuilder {

    public void configure() throws Exception {
        from("file:data/input?noop=true")
                .to("file:data/output");
    }
}
