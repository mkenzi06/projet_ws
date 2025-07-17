
package foot.web.projet.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour player complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="player"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="equipe" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="poste" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="prenom" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="teamName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "player", propOrder = {
    "equipe",
    "id",
    "poste",
    "prenom",
    "teamName"
})
public class Player {

    protected int equipe;
    protected int id;
    protected String poste;
    protected String prenom;
    protected String teamName;
    protected int age;
    /**
     * Obtient la valeur de la propri�t� equipe.
     * 
     */
    public int getEquipe() {
        return equipe;
    }

    /**
     * D�finit la valeur de la propri�t� equipe.
     * 
     */
    public void setEquipe(int value) {
        this.equipe = value;
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
     * Obtient la valeur de la propri�t� poste.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPoste() {
        return poste;
    }

    /**
     * D�finit la valeur de la propri�t� poste.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPoste(String value) {
        this.poste = value;
    }

    /**
     * Obtient la valeur de la propri�t� prenom.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * D�finit la valeur de la propri�t� prenom.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrenom(String value) {
        this.prenom = value;
    }

    /**
     * Obtient la valeur de la propri�t� teamName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTeamName() {
        return teamName;
    }

    /**
     * D�finit la valeur de la propri�t� teamName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTeamName(String value) {
        this.teamName = value;
    }
    /**
     * Obtient la valeur de la propri�t� age.
     * 
     */
    public int getAge() {
        return age;
        }
    /**
     * D�finit la valeur de la propri�t� age. 
     * @param value
     *     allowed object is     
     * {@link int }
     */
    public void setAge(int value) {
        this.age = value;
        }



}
