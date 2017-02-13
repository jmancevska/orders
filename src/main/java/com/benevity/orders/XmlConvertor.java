package com.benevity.orders;

import java.io.OutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

/**
 * @author Jasminka Mancevska
 * 
 */
public class XmlConvertor {


	public static void fromObjectToXml(Object object, OutputStream out) {
		try {

			JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass()); 
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(new JAXBElement<Object>(new QName("uri",
					"list"), Object.class, object), out);

		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}

	public static void toXml(Object object, OutputStream out) {
		try {

			JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass()); 
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(new JAXBElement<Object>(new QName("uri",
					"list"), Object.class, object), out);

		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}

}
