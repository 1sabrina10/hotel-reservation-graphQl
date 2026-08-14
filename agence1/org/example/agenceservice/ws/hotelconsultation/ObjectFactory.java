
package org.example.agence.ws.hotelconsultation;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.example.agenceservice.ws.hotelconsultation package. 
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

    private final static QName _AuthentifierAgence_QNAME = new QName("http://service.hotelservice.web_services.example.org/", "authentifierAgence");
    private final static QName _ConsulterDisponibilites_QNAME = new QName("http://service.hotelservice.web_services.example.org/", "consulterDisponibilites");
    private final static QName _ConsulterDisponibilitesResponse_QNAME = new QName("http://service.hotelservice.web_services.example.org/", "consulterDisponibilitesResponse");
    private final static QName _AuthentifierAgenceResponse_QNAME = new QName("http://service.hotelservice.web_services.example.org/", "authentifierAgenceResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.example.agenceservice.ws.hotelconsultation
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AuthentifierAgence }
     * 
     */
    public AuthentifierAgence createAuthentifierAgence() {
        return new AuthentifierAgence();
    }

    /**
     * Create an instance of {@link ConsulterDisponibilites }
     * 
     */
    public ConsulterDisponibilites createConsulterDisponibilites() {
        return new ConsulterDisponibilites();
    }

    /**
     * Create an instance of {@link ConsulterDisponibilitesResponse }
     * 
     */
    public ConsulterDisponibilitesResponse createConsulterDisponibilitesResponse() {
        return new ConsulterDisponibilitesResponse();
    }

    /**
     * Create an instance of {@link AuthentifierAgenceResponse }
     * 
     */
    public AuthentifierAgenceResponse createAuthentifierAgenceResponse() {
        return new AuthentifierAgenceResponse();
    }

    /**
     * Create an instance of {@link ChambreDTO }
     * 
     */
    public ChambreDTO createChambreDTO() {
        return new ChambreDTO();
    }

    /**
     * Create an instance of {@link Adresse }
     * 
     */
    public Adresse createAdresse() {
        return new Adresse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AuthentifierAgence }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.hotelservice.web_services.example.org/", name = "authentifierAgence")
    public JAXBElement<AuthentifierAgence> createAuthentifierAgence(AuthentifierAgence value) {
        return new JAXBElement<AuthentifierAgence>(_AuthentifierAgence_QNAME, AuthentifierAgence.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsulterDisponibilites }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.hotelservice.web_services.example.org/", name = "consulterDisponibilites")
    public JAXBElement<ConsulterDisponibilites> createConsulterDisponibilites(ConsulterDisponibilites value) {
        return new JAXBElement<ConsulterDisponibilites>(_ConsulterDisponibilites_QNAME, ConsulterDisponibilites.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsulterDisponibilitesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.hotelservice.web_services.example.org/", name = "consulterDisponibilitesResponse")
    public JAXBElement<ConsulterDisponibilitesResponse> createConsulterDisponibilitesResponse(ConsulterDisponibilitesResponse value) {
        return new JAXBElement<ConsulterDisponibilitesResponse>(_ConsulterDisponibilitesResponse_QNAME, ConsulterDisponibilitesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AuthentifierAgenceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.hotelservice.web_services.example.org/", name = "authentifierAgenceResponse")
    public JAXBElement<AuthentifierAgenceResponse> createAuthentifierAgenceResponse(AuthentifierAgenceResponse value) {
        return new JAXBElement<AuthentifierAgenceResponse>(_AuthentifierAgenceResponse_QNAME, AuthentifierAgenceResponse.class, null, value);
    }

}
