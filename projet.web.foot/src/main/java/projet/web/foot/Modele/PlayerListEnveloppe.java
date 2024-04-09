package projet.web.foot.Modele;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import java.io.StringWriter;
import java.util.ArrayList;


public class PlayerListEnveloppe {
	   public static String convertPlayersToXml(ArrayList<Player> players) {
	        try {
	            // Créer le contexte JAXB pour les classes Player et toute autre classe associée
	            JAXBContext context = JAXBContext.newInstance(Player.class); /* Ajoutez d'autres classes si nécessaire */;

	            // Créer le marshaller JAXB
	            Marshaller marshaller = context.createMarshaller();

	            // Configuration du marshaller pour l'indentation du XML
	            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

	            // Utiliser un StringWriter pour stocker le XML
	            StringWriter writer = new StringWriter();

	            // Marshalliser la liste de joueurs en XML
	            marshaller.marshal(players, writer);

	            // Récupérer le XML sous forme de chaîne de caractères depuis StringWriter
	            return writer.toString();

	        } catch (JAXBException e) {
	            e.printStackTrace();
	            // Gérer l'exception selon vos besoins
	            return null;
	        }
	    }
}
