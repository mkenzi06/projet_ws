
package foot.web.projet.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour team complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="team"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="coachName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="league" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "team", propOrder = {
    "coachName",
    "id",
    "league",
    "name"
})
public class Team {

    protected String coachName;
    protected int id;
    protected String league;
    protected String name;

    /**
     * Obtient la valeur de la propri�t� coachName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCoachName() {
        return coachName;
    }

    /**
     * D�finit la valeur de la propri�t� coachName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCoachName(String value) {
        this.coachName = value;
    }

    /**
     * Obtient la valeur de la propri�t� id.
     * 
     */
    public int getId() {
        return id;
    }

    /**
     * D�finit la valeur de la propri�t� id.
     * 
     */
    public void setId(int value) {
        this.id = value;
    }

    /**
     * Obtient la valeur de la propri�t� league.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLeague() {
        return league;
    }

    /**
     * D�finit la valeur de la propri�t� league.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLeague(String value) {
        this.league = value;
    }

    /**
     * Obtient la valeur de la propri�t� name.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * D�finit la valeur de la propri�t� name.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

}
