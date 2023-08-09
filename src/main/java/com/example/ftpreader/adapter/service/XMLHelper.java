package com.example.ftpreader.adapter.service;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

/**
 * XML parser helper
 * <br>
 * <p/>
 * Creation date: 09/08/2023<br>
 *
 * @author dobr
 */
@Slf4j
@UtilityClass
public class XMLHelper {

    public static NodeList getElementsByTagName(File file, String tagName) {
        try {
            var document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
            document.getDocumentElement().normalize();
            return document.getElementsByTagName(tagName);
        } catch (Exception e) {
            log.error("error during parsing xml file: {}", file.getName());
            return null;
        }
    }

    public static String getContentFromTag(Element xmlElement, String tag) {
        return xmlElement.getElementsByTagName(tag).item(0).getTextContent();
    }
}
