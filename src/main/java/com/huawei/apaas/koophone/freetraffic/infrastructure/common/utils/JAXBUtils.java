package com.huawei.apaas.koophone.freetraffic.infrastructure.common.utils;

import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLFilterImpl;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXSource;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * xml和bean互转util
 * @author zhangjihong
 * @since 2023-05-28
 */
public class JAXBUtils {
    private static final String HEAD = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";

    private JAXBUtils() { }
    public static String obj2xmlString(Object obj) {
        return obj2xmlString(obj, HEAD);
    }
    public static String obj2xmlString(Object obj, String head) {
        StringWriter writer = new StringWriter();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
            writer.write(head);
            marshaller.marshal(obj, writer);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
        return writer.toString();
    }
    public static String obj2xmlStringWithNS(Object obj) {
        return obj2xmlStringWithNS(obj, HEAD);
    }
    public static String obj2xmlStringWithNS(Object obj, String head) {
        StringWriter writer = new StringWriter();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
            writer.write(head);

            OutputFormat format = new OutputFormat();
            format.setIndent(true);
            format.setNewlines(true);
            format.setNewLineAfterDeclaration(false);
            XMLWriter xmlWriter = new XMLWriter(writer, format);

            XMLFilterImpl nsfFilter = new XMLFilterImpl() {
                private boolean ignoreNamespace = false;
                private String rootNamespace = null;
                private boolean isRootElement = true;

                @Override
                public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
                    if (this.ignoreNamespace) uri = "";
                    if (this.isRootElement) this.isRootElement = false;
                    else if (!uri.equals("") && !localName.contains("xmlns")) localName = localName + " xmlns=\"" + uri + "\"";

                    super.startElement(uri, localName, localName, atts);
                }

                @Override
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    if (this.ignoreNamespace) uri = "";
                    super.endElement(uri, localName, localName);
                }

                @Override
                public void startPrefixMapping(String prefix, String url) throws SAXException {
                    if (this.rootNamespace != null) url = this.rootNamespace;
                    if (!this.ignoreNamespace) super.startPrefixMapping("", url);

                }
            };

            nsfFilter.setContentHandler(xmlWriter);
            marshaller.marshal(obj, nsfFilter);
            return writer.toString();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T xml2Obj(Class<T> clz, String xml) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(clz);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            StringReader reader = new StringReader(xml);
            return (T) unmarshaller.unmarshal(reader);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T xml2ObjIgnoreNS(Class<T> clz, String xml) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(clz);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            StringReader reader = new StringReader(xml);

            SAXParserFactory sax = SAXParserFactory.newInstance();
            sax.setNamespaceAware(false);
            XMLReader xmlReader = sax.newSAXParser().getXMLReader();
            Source source = new SAXSource(xmlReader, new InputSource(reader));

            return (T) unmarshaller.unmarshal(source);
        } catch (JAXBException | ParserConfigurationException | SAXException e) {
            throw new RuntimeException(e);
        }
    }
}
