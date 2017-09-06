package com.matthew.file;

import com.matthew.file.CopyFileRoute;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

import java.io.File;

public class CopyFileRouteTest extends CamelTestSupport {

    public RoutesBuilder createRouteBuilder() throws Exception {
        return new CopyFileRoute();
    }

    @Test
    public void checkFileExistsInOutputDirectory() throws InterruptedException {

        Thread.sleep(5000);

        File file = new File("data/output");

        assertTrue(file.isDirectory());
        System.out.println("Total no of files in the output directory : " + file.listFiles().length);
        assertEquals(2, file.listFiles().length);
    }
}
