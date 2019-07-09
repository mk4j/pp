package com.mk.pp;

import java.io.IOException;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import  java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class PageParser {

    static ArrayList<Flat> flatsList = new ArrayList<Flat>();
    public static final int WAIT_TIMEOUT = 60000;
    HashMap<String, Flat> komFlats;

    private String url;
    private String itemsTag = "div.item-with-contact";
    private Document doc;
    private Elements items;
    private Iterator<Element> it;
    private int itemsCnt, itemId;

    public PageParser(){}

    public PageParser(String url) throws  IOException{
        this.url = url;
        this.itemsTag = itemsTag;

        doc = Jsoup.connect(url).get();
        items = doc.select(itemsTag);

        itemsCnt = items.size();
        itemId = 0;

        it = items.iterator();
    }

    public void setItemId(int itemId){
        this.itemId = itemId;
    }

    public boolean hasNext(){
        boolean res = false;
        if(itemId < itemsCnt - 1)
            res = true;

        return res;
    }

    public String nextId(){
        Element item = items.get(++itemId);
        return  item.id();
    }
    public Flat getCurrentFlat(){
        Flat flat = new Flat();
        Element item = items.get(itemId);

        flat.setId(item.id());


        flat.setShortHeader(item.select("a.item-description-title-link")
                .get(0).select("span[itemprop]").text());
        flat.setHref(item.select("a.item-description-title-link")
                .get(0).attr("href"));
        flat.setPrice(Integer.parseInt(item.select("span[itemprop=price]")
                .get(0).attr("content")));
        String distance = item.select("p.address")
                .get(0).select("span.c-2")
                .get(0).text();
        flat.setDistanceToMetro(parseDistance(distance));
        flat.setShortAddress(item.select("p.address")
                .get(0).text());
        flat.setShortPublishDate(item.select("div.js-item-date")
                .get(0).attr("data-absolute-date"));

        return flat;
    }

    public Flat nextFlat(){
        itemId++;
        return getCurrentFlat();
    }

    public HashMap<String, Flat> getAllItems(){
        HashMap<String, Flat> result = new HashMap<String, Flat>();
        setItemId(0);
        String id;
        while(this.hasNext()){
            id = nextId();
            result.put(id, getCurrentFlat());
        }

        return result;
    }

    public static double parseDistance(String distance){
        double result = -1;
        String[] parts = distance.split(" ");
        result = Double.parseDouble(parts[0]);
        if(parts[1] !=null && parts[1].equals("км"))
            result *=1000;

        return result;
    }

}
