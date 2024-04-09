package computer.com.client;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.5.7
 * 2024-04-10T00:41:48.682+02:00
 * Generated source version: 3.5.7
 *
 */
@WebServiceClient(name = "PlayerWebServiceService",
                  wsdlLocation = "file:/C:/Users/HP/eclipse-workspace/com.computer/src/main/webapp/wsdl/playerwebservice.wsdl",
                  targetNamespace = "http://www.com.computer")
public class PlayerWebServiceService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://www.com.computer", "PlayerWebServiceService");
    public final static QName PlayerWebServicePort = new QName("http://www.com.computer", "PlayerWebServicePort");
    static {
        URL url = null;
        try {
            url = new URL("file:/C:/Users/HP/eclipse-workspace/com.computer/src/main/webapp/wsdl/playerwebservice.wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(PlayerWebServiceService.class.getName())
                .log(java.util.logging.Level.INFO,
                     "Can not initialize the default wsdl from {0}", "file:/C:/Users/HP/eclipse-workspace/com.computer/src/main/webapp/wsdl/playerwebservice.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public PlayerWebServiceService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public PlayerWebServiceService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public PlayerWebServiceService() {
        super(WSDL_LOCATION, SERVICE);
    }

    public PlayerWebServiceService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public PlayerWebServiceService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public PlayerWebServiceService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }




    /**
     *
     * @return
     *     returns PlayerWebService
     */
    @WebEndpoint(name = "PlayerWebServicePort")
    public PlayerWebService getPlayerWebServicePort() {
        return super.getPort(PlayerWebServicePort, PlayerWebService.class);
    }

    /**
     *
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns PlayerWebService
     */
    @WebEndpoint(name = "PlayerWebServicePort")
    public PlayerWebService getPlayerWebServicePort(WebServiceFeature... features) {
        return super.getPort(PlayerWebServicePort, PlayerWebService.class, features);
    }

}
