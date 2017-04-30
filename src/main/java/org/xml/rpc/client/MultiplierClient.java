package org.xml.rpc.client;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfig;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by User on 30-04-2017.
 */
public class MultiplierClient {

    public static void main(String[] args){

        try {
            XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
            config.setServerURL(new URL("http://localhost:8080/XmlRpcApp/rpc"));
            XmlRpcClient client = new XmlRpcClient();
            client.setConfig(config);
            Object[] params = new Object[]{new Integer(10), new Integer(50)};
            Integer result = (Integer)client.execute("Multiplier.multiply", params);
            System.out.println("PRODUCT: "+result.intValue());
        } catch (MalformedURLException e){
            System.out.println(e.getMessage());
        } catch(XmlRpcException x){
            System.out.println(x.getMessage());
        }


    }
}
