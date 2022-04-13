package com.example.networktest;

import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ContentHandler extends DefaultHandler {

    private String nodeName;
    private StringBuilder name;
    private StringBuilder price;
    private StringBuilder description;
    private StringBuilder calories;
    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        name=new StringBuilder();
        price=new StringBuilder();
        description=new StringBuilder();
        calories=new StringBuilder();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        nodeName=localName;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        if("name".equals(nodeName)){
            name.append(ch,start,length);
        }else if("price".equals(nodeName)){
            price.append(ch,start,length);
        }else if("description".equals(nodeName)){
            description.append(ch,start,length);
        }else if("calories".equals(nodeName)){
            calories.append(ch,start,length);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        if("food".equals(localName)){
            Log.d("ContentHandler","tiaoshi id is: "+name.toString().trim());
            Log.d("ContentHandler","tiaoshi name is: "+price.toString().trim());
            Log.d("ContentHandler","tiaoshi version is: "+description.toString().trim());
            Log.d("ContentHandler","tiaoshi version is: "+calories.toString().trim());
            //最后要将StringBuilder清空掉
            name.setLength(0);
            price.setLength(0);
            description.setLength(0);
            calories.setLength(0);
        }
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();

    }
}
