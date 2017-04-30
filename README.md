# XmlRpcApp
Postman can be used to query from outside.Below is a sample request body:
<?xml version="1.0"?>
<methodCall>
   <methodName>Multiplier.multiply</methodName>
      <params>
         <param>
            <value><int>13</int></value>
             <value><int>20</int></value>
         </param>
      </params>
</methodCall>
#Result body:
<?xml version="1.0" encoding="UTF-8"?>
<methodResponse xmlns:ex="http://ws.apache.org/xmlrpc/namespaces/extensions">
    <params>
        <param>
            <value>
                <i4>260</i4>
            </value>
        </param>
    </params>
</methodResponse>

Do remember to use credentials in case the authentication is enabled.
