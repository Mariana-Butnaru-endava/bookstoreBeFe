package sharedData;

import xmlFiles.xmlNode.FrontEndConfig;

public interface BrowserService {
    void openBrowser(FrontEndConfig frontEndConfig);
    Object getBrowserOptions(FrontEndConfig frontEndConfig);
}
