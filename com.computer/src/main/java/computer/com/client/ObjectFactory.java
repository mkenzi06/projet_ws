
package computer.com.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the computer.com.client package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Player_QNAME = new QName("http://www.com.computer", "Player");
    private final static QName _AddPlayer_QNAME = new QName("http://www.com.computer", "addPlayer");
    private final static QName _AddPlayerResponse_QNAME = new QName("http://www.com.computer", "addPlayerResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: computer.com.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Player }
     * 
     */
    public Player createPlayer() {
        return new Player();
    }

    /**
     * Create an instance of {@link AddPlayer }
     * 
     */
    public AddPlayer createAddPlayer() {
        return new AddPlayer();
    }

    /**
     * Create an instance of {@link AddPlayerResponse }
     * 
     */
    public AddPlayerResponse createAddPlayerResponse() {
        return new AddPlayerResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Player }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Player }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.com.computer", name = "Player")
    public JAXBElement<Player> createPlayer(Player value) {
        return new JAXBElement<Player>(_Player_QNAME, Player.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddPlayer }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AddPlayer }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.com.computer", name = "addPlayer")
    public JAXBElement<AddPlayer> createAddPlayer(AddPlayer value) {
        return new JAXBElement<AddPlayer>(_AddPlayer_QNAME, AddPlayer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddPlayerResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AddPlayerResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.com.computer", name = "addPlayerResponse")
    public JAXBElement<AddPlayerResponse> createAddPlayerResponse(AddPlayerResponse value) {
        return new JAXBElement<AddPlayerResponse>(_AddPlayerResponse_QNAME, AddPlayerResponse.class, null, value);
    }

}
