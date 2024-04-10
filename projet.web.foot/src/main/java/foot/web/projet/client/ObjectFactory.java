
package foot.web.projet.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the foot.web.projet.client package. 
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

    private final static QName _AddPlayer_QNAME = new QName("http://www.projet.web.foot", "addPlayer");
    private final static QName _AddPlayerResponse_QNAME = new QName("http://www.projet.web.foot", "addPlayerResponse");
    private final static QName _AddTeam_QNAME = new QName("http://www.projet.web.foot", "addTeam");
    private final static QName _AddTeamResponse_QNAME = new QName("http://www.projet.web.foot", "addTeamResponse");
    private final static QName _DeleteTeam_QNAME = new QName("http://www.projet.web.foot", "deleteTeam");
    private final static QName _DeleteTeamResponse_QNAME = new QName("http://www.projet.web.foot", "deleteTeamResponse");
    private final static QName _Player_QNAME = new QName("http://www.projet.web.foot", "player");
    private final static QName _Team_QNAME = new QName("http://www.projet.web.foot", "team");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: foot.web.projet.client
     * 
     */
    public ObjectFactory() {
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
     * Create an instance of {@link AddTeam }
     * 
     */
    public AddTeam createAddTeam() {
        return new AddTeam();
    }

    /**
     * Create an instance of {@link AddTeamResponse }
     * 
     */
    public AddTeamResponse createAddTeamResponse() {
        return new AddTeamResponse();
    }

    /**
     * Create an instance of {@link DeleteTeam }
     * 
     */
    public DeleteTeam createDeleteTeam() {
        return new DeleteTeam();
    }

    /**
     * Create an instance of {@link DeleteTeamResponse }
     * 
     */
    public DeleteTeamResponse createDeleteTeamResponse() {
        return new DeleteTeamResponse();
    }

    /**
     * Create an instance of {@link Player }
     * 
     */
    public Player createPlayer() {
        return new Player();
    }

    /**
     * Create an instance of {@link Team }
     * 
     */
    public Team createTeam() {
        return new Team();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddPlayer }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AddPlayer }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.projet.web.foot", name = "addPlayer")
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
    @XmlElementDecl(namespace = "http://www.projet.web.foot", name = "addPlayerResponse")
    public JAXBElement<AddPlayerResponse> createAddPlayerResponse(AddPlayerResponse value) {
        return new JAXBElement<AddPlayerResponse>(_AddPlayerResponse_QNAME, AddPlayerResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddTeam }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AddTeam }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.projet.web.foot", name = "addTeam")
    public JAXBElement<AddTeam> createAddTeam(AddTeam value) {
        return new JAXBElement<AddTeam>(_AddTeam_QNAME, AddTeam.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddTeamResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AddTeamResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.projet.web.foot", name = "addTeamResponse")
    public JAXBElement<AddTeamResponse> createAddTeamResponse(AddTeamResponse value) {
        return new JAXBElement<AddTeamResponse>(_AddTeamResponse_QNAME, AddTeamResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteTeam }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DeleteTeam }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.projet.web.foot", name = "deleteTeam")
    public JAXBElement<DeleteTeam> createDeleteTeam(DeleteTeam value) {
        return new JAXBElement<DeleteTeam>(_DeleteTeam_QNAME, DeleteTeam.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteTeamResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DeleteTeamResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.projet.web.foot", name = "deleteTeamResponse")
    public JAXBElement<DeleteTeamResponse> createDeleteTeamResponse(DeleteTeamResponse value) {
        return new JAXBElement<DeleteTeamResponse>(_DeleteTeamResponse_QNAME, DeleteTeamResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Player }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Player }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.projet.web.foot", name = "player")
    public JAXBElement<Player> createPlayer(Player value) {
        return new JAXBElement<Player>(_Player_QNAME, Player.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Team }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Team }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.projet.web.foot", name = "team")
    public JAXBElement<Team> createTeam(Team value) {
        return new JAXBElement<Team>(_Team_QNAME, Team.class, null, value);
    }

}
