package org.xml.rpc.server;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.server.XmlRpcHandlerMapping;
import org.apache.xmlrpc.webserver.XmlRpcServlet;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by User on 30-04-2017.
 */
public class MyXmlRpcServlet extends XmlRpcServlet {

    /*Below method is overriden as there is a bug in the original code which reads the properties file from a
    * fixed location. However, with the below code there is a flexibility in placing the properties file.*/
    @Override
    protected XmlRpcHandlerMapping newXmlRpcHandlerMapping() throws XmlRpcException {

        /*To use the below path, keep your XmlRpcServlet.xml in src/resources*/
        String realPath = "/WEB-INF/classes/XmlRpcServlet.properties";
        URL url = null;
        try {
            url = this.getServletContext().getResource(realPath);
            if(url == null) {
            throw new XmlRpcException("Failed to locate resource XmlRpcServlet.properties");
        }
        return newPropertyHandlerMapping(url);
        }catch(IOException e){
            throw new XmlRpcException("Failed to load resource " + realPath + ": " + e.getMessage(), e); }
        }
    }

