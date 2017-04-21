package org.campus.testing.xml;

import static org.junit.Assert.fail;

import java.io.InputStream;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.junit.Test;

public class XmlSchemaValidationTest {

	@Test
	public void xml_schema_validation_test() {
		InputStream xsd = XmlSchemaValidationTest.class.getResourceAsStream("/shiporder.xsd");
		InputStream xml = XmlSchemaValidationTest.class.getResourceAsStream("/order.xml");
		try {
			SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = factory.newSchema(new StreamSource(xsd));
			Validator validator = schema.newValidator();
			validator.validate(new StreamSource(xml));
			System.out.println("Validation OK");
		} catch (Exception ex) {
			System.err.println("Error while processing schema validation. Reason: " + ex.getMessage());
			fail();
		}
	}
}
