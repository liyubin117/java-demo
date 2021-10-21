package org.rick.nio;


import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.*;

public class WatchServiceDemo {
    WatchService watchService;
    Path path;

    @Before
    public void setUp() throws IOException {
        this.watchService = FileSystems.getDefault().newWatchService();
        this.path = Paths.get("dir");
        path.register(watchService,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_MODIFY);
    }

    @Test
    public void test1() throws InterruptedException {
        WatchKey key;
        while ((key = watchService.take()) != null){
            for(WatchEvent event : key.pollEvents()){
                System.out.println(
                        "Event kind:" + event.kind()
                                + ". File affected: " + event.context() + ".");
            }
            key.reset();
        }
    }

}
