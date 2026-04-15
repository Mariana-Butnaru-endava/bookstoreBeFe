package xmlFiles.xmlNode;

import lombok.Getter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
@Getter
public class FrontEndConfig {
    @XmlElement(name = "localBrowser")
    public String localBrowser;
    @XmlElement(name = "url")
    public String url;
    @XmlElement(name = "headless")
    public Boolean headless;
    @XmlElement(name = "gpu")
    public String gpu;
    @XmlElement(name = "infobars")
    public String infobars;
    @XmlElement(name = "sandbox")
    public String sandbox;
    @XmlElement(name = "remoteUrl")
    public String remoteUrl;
    @XmlElement(name = "implicitWait")
    public Integer implicitWait;
    @XmlElement(name = "scriptTimeout")
    public Integer scriptTimeout;
    @XmlElement(name = "pageLoadTimeout")
    public Integer pageLoadTimeout;
}
