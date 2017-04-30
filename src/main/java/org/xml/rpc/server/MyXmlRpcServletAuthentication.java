package org.xml.rpc.server;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.XmlRpcRequest;
import org.apache.xmlrpc.common.XmlRpcHttpRequestConfig;
import org.apache.xmlrpc.server.AbstractReflectiveHandlerMapping;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcHandlerMapping;
import org.apache.xmlrpc.webserver.XmlRpcServlet;

import java.io.IOException;
import java.net.URL;

/**
 * Created by User on 30-04-2017.
 */
public class MyXmlRpcServletAuthentication extends XmlRpcServlet {

    /*Below method is overriden as there is a bug in the original code which reads the properties file from a
    * fixed location. However, with the below code there is a flexibility in placing the properties file.*/
    @Override
    protected XmlRpcHandlerMapping newXmlRpcHandlerMapping() throws XmlRpcException {

        /*To use the below path, keep your XmlRpcServlet.xml in src/resources*/
        String realPath = "/WEB-INF/classes/XmlRpcServlet.properties";
        URL url = null;
        try {

            AbstractReflectiveHandlerMapping.AuthenticationHandler handler =
                    new AbstractReflectiveHandlerMapping.AuthenticationHandler(){
                        public boolean isAuthorized(XmlRpcRequest pRequest){
                            XmlRpcHttpRequestConfig config =
                                    (XmlRpcHttpRequestConfig) pRequest.getConfig();
                            return isAuthenticated(config.getBasicUserName(),
                                    config.getBasicPassword());
                        };
                    };
            url = this.getServletContext().getResource(realPath);
            if(url == null) {
            throw new XmlRpcException("Failed to locate resource XmlRpcServlet.properties");
        }
            PropertyHandlerMapping propertyHandlerMapping = newPropertyHandlerMapping(url);
            propertyHandlerMapping.setAuthenticationHandler(handler);
            return propertyHandlerMapping;
        }catch(IOException e){
            throw new XmlRpcException("Failed to load resource " + realPath + ": " + e.getMessage(), e); }
        }

    private boolean isAuthenticated(String pUserName, String pPassword) {
        return "foo".equals(pUserName) && "bar".equals(pPassword);
    }
    }

