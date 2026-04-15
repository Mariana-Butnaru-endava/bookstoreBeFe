package xmlFiles.xmlNode;

import lombok.Getter;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
//in prezentare nu exista si XmlAccessorType
@XmlRootElement(name = "configuration")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
public class Configuration {

    @XmlElement(name="backEndConfig")
    public BackEndConfig backEndConfig;
    @XmlElement(name="frontEndConfig")
    public FrontEndConfig frontEndConfig;
}
