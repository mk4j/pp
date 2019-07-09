package com.mk.pp;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.HashMap;

public class Watcher {

    protected String watchUrl;
    protected HashMap<String, Flat> flats = new HashMap<String, Flat>();
    protected static final int WAIT_TIMEOUT = 15000;
    protected static final int EXCEPTION_TIMEOUT = 120000;

    public Watcher(String url) throws  IOException{
        this.watchUrl = url;
        PageParser parser = new PageParser(watchUrl);
        flats = parser.getAllItems();
    }

    public void monitor() throws IOException, InterruptedException {
        while(true){
            try {
                System.out.println("Checking updates");
                Thread.sleep(WAIT_TIMEOUT);
                String id;
                Flat flat;
                PageParser pp = new PageParser(watchUrl);
                while (pp.hasNext()) {
                    id = pp.nextId();
                    if (flats.containsKey(id)) {
                        continue;
                    } else {
                        flat = pp.getCurrentFlat();
                        flats.put(id, flat);
                        System.out.println("New item appears: https://avito.ru/" + flat.getHref());
                    }
                }
            }catch(SocketTimeoutException e){
                System.out.println("Timeout exception");
                //Thread.sleep(EXCEPTION_TIMEOUT);
                continue;
            }
        }

    }




}
