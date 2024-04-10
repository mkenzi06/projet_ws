
package foot.web.projet.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour deleteTeam complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="deleteTeam"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="deleteteam" type="{http://www.projet.web.foot}team" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "deleteTeam", propOrder = {
    "deleteteam"
})
public class DeleteTeam {

    protected Team deleteteam;

    /**
     * Obtient la valeur de la propriété deleteteam.
     * 
     * @return
     *     possible object is
     *     {@link Team }
     *     
     */
    public Team getDeleteteam() {
        return deleteteam;
    }

    /**
     * Définit la valeur de la propriété deleteteam.
     * 
     * @param value
     *     allowed object is
     *     {@link Team }
     *     
     */
    public void setDeleteteam(Team value) {
        this.deleteteam = value;
    }

}
