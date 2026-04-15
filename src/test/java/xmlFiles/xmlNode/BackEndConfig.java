package xmlFiles.xmlNode;

import lombok.Getter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
@Getter
public class BackEndConfig {

    @XmlElement(name = "baseURL")
    public String baseURL;
    @XmlElement(name = "contentType")
    public String contentType;
}
