package com.oio.wawj.util;

 
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.rpc.ParameterMode;
//import javax.xml.rpc.ParameterMode;
//import javax.xml.rpc.ServiceException;
//import javax.xml.rpc.encoding.XMLType;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;
import org.apache.axis.message.SOAPHeaderElement;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import java.io.StringWriter;
import java.net.URL;

/**
 * Created by Administrator on 2016/9/21.
 */
public class WebServiceTest {

    public static SOAPHeaderElement getSoapHeader(String name, String pwd)
            throws ParserConfigurationException,Exception {
        // create document of w3c
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.newDocument();
        // create Element
        String address = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd";
        Element security = document.createElementNS("namespace", "Security");
        security.setAttribute("xmlns:wsse", address);
        Element usernameToken = document.createElementNS("namespace",
                "UsernameToken");
        Element username = document.createElementNS("namespace", "Username");
        Element password = document.createElementNS("namespace", "Password");
        password.setAttribute("Type", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText");
        // add Element
        usernameToken.appendChild(username);
        usernameToken.appendChild(password);
        security.appendChild(usernameToken);
        // set value
        Text usernameValue = document.createTextNode(name);
        username.appendChild(usernameValue);
        Text passwordValue = document.createTextNode(pwd);
        password.appendChild(passwordValue);
        // create SOAPHeaderElement
        SOAPHeaderElement ppElement = new SOAPHeaderElement(security);
        ppElement.setPrefix("wsse");

        return ppElement;
    }

    public static void print(Document doc)throws Exception{
        TransformerFactory f = TransformerFactory.newInstance();
        Transformer former = f.newTransformer();
        former.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        DOMSource source = new DOMSource(doc);
        StringWriter sw = new StringWriter();
        StreamResult sr =new StreamResult(sw);
        former.transform(source, sr);

        System.out.println("@:"+sw.toString());
    }

    public static void main(String[] args) throws Exception {

        try {
            //String arg0= new String("151504");
            //String endpoint_1 = "http://10.10.101.83:7099/5i5j/services/ChangeHouseAndCustomerStatus?wsdl";

//            String arg0 = new String("20170307");
//            String arg1 = new String("20170307");
//            String arg0 = new String("18");
//            String arg1 = new String("201703071230");
//            String arg2 = new String("");
//            String arg3 = new String("");
            

            //String endpoint_1 = "http://10.10.101.83:7099/5i5j/services/LastWTContract?wsdl";
//            String endpoint_1 = "http://10.2.1.15/5i5j/services/selectContract?wsdl";
//            String endpoint_1 = "http://10.2.1.15/5i5j/services/selectShowing?wsdl";
//            String endpoint_1 = "http://10.10.101.81/5i5j/services/selectShowing?wsdl";
//            String endpoint_1 = "http://10.55.2.111/5i5j/services/selectContract?wsdl";
            String endpoint_1 = "http://101.251.207.10/5i5j/services/UsersMobilePhone?wsdl";

            String namespace="http://website.webService.erp.wiwj.com/";
            Service service = new Service();
            Call call = (Call) service.createCall();
            SOAPHeaderElement ss = getSoapHeader("shanghaiws", "shbacic5i5j");

            print(ss.getAsDocument());

            call.addHeader(ss);
			call.setOperationName(new QName(namespace, "getHouseBasic"));//getHouseBasic
			call.setTargetEndpointAddress(new URL(endpoint_1));
//			call.addParameter(new QName(namespace, "arg0"),
//                    org.apache.axis.encoding.XMLType.XSD_STRING,
//                    javax.xml.rpc.ParameterMode.IN); 
            call.setTargetEndpointAddress(new java.net.URL(endpoint_1));
            //������
            call.setOperationName(new QName(namespace, "getUsersMobilePhone"));
            //call.setOperationName(new QName(namespace, "getHouseStateByHouseId"));
            //������ڲ���Ͳ�������
          //  call.addParameter(new QName(namespace, "arg0"),XMLType.XSD_STRING, ParameterMode.IN);
//            call.addParameter(new QName(namespace, "arg1"),XMLType.XSD_STRING, ParameterMode.IN);
//            call.addParameter(new QName(namespace, "arg2"),XMLType.XSD_STRING, ParameterMode.IN);
//            call.addParameter(new QName(namespace, "arg3"),XMLType.XSD_STRING, ParameterMode.IN);
          
            
            //call.addParameter(new QName(namespace, "arg2"),XMLType.XSD_STRING, ParameterMode.IN);
            //call.addParameter(new QName(namespace, "password"),XMLType.XSD_STRING, ParameterMode.IN);
            call.setUseSOAPAction(true);
            //Action��ַ
            call.setSOAPActionURI(endpoint_1);
            //System.out.println(call.g("arg0"));
            //call.setReturnType(new QName(namespace, "arg0"));
            Object[] obj = {};
//            Object[] obj = {arg0,arg1,arg2,arg3};
            String result = (String) call.invoke(obj);
            //System.out.println("result=" + result);
        }

        catch (Exception e) {
            System.err.println(e.toString());
        }
    }
}