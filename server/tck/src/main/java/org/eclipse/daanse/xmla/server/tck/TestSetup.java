package org.eclipse.daanse.xmla.server.tck;

import java.io.IOException;

import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.cm.annotations.RequireConfigurationAdmin;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.test.common.dictionary.Dictionaries;

@Component(immediate = true)
@RequireConfigurationAdmin
public class TestSetup {

    @Reference
    ConfigurationAdmin ca;

    @Activate
    void activate() throws IOException {
        Configuration c = ca.getFactoryConfiguration("daanse.xmla.server.jakarta.saaj.XmlaServlet", "0815", "?");

        c.update(Dictionaries.dictionaryOf("osgi.http.whiteboard.servlet.name", "TestXmlaServlet",
                "osgi.http.whiteboard.servlet.pattern", "/xmla"));
        System.out.println("TestSetup activated and configuration created.");
    }
}
