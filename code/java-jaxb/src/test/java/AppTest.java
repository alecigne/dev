import net.lecigne.codingkatas.javajaxb.model.Person;
import net.lecigne.codingkatas.javajaxb.model.PersonBis;
import net.lecigne.codingkatas.javajaxb.model.PersonForUnmarshalling;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.xml.sax.SAXException;
import org.xmlunit.assertj.XmlAssert;
import org.xmlunit.builder.Input;
import org.xmlunit.validation.Languages;
import org.xmlunit.validation.ValidationResult;
import org.xmlunit.validation.Validator;

import javax.xml.XMLConstants;
import javax.xml.bind.*;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.StringReader;
import java.io.StringWriter;
import java.time.LocalDate;

import static net.lecigne.codingkatas.javajaxb.model.Sex.M;
import static org.assertj.core.api.Assertions.*;

class AppTest {

    @Nested
    class Marshalling {

        /**
         * Check that marshalling an instance of a correctly annotated class ({@link Person}) produces valid XML.
         */
        @Test
        void marshal_givenInstanceOfCorrectlyAnnotatedClass_shouldGenerateValidXml() throws JAXBException {
            // Given
            var person = new Person(M, "Jacques", "Dupont", LocalDate.of(2000, 12, 25), 1, 2000);

            // A JAXB marshaller
            JAXBContext context = JAXBContext.newInstance(Person.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            StringWriter sw = new StringWriter();

            // When
            marshaller.marshal(person, sw);

            // Then
            String marshalled = sw.toString();

            // XML validation with XMLUnit
            Validator validator = Validator.forLanguage(Languages.W3C_XML_SCHEMA_NS_URI);
            validator.setSchemaSource(Input.fromStream(AppTest.class.getResourceAsStream("/individual.xsd")).build());
            ValidationResult result = validator.validateInstance(new StreamSource(new StringReader(marshalled)));
            assertThat(result.isValid()).isTrue();

            // Other tests
            Input.Builder input = Input.fromString(marshalled);
            XmlAssert.assertThat(input).doesNotHaveXPath("//salary");
            XmlAssert.assertThat(input).valueByXPath("//birthDate").isEqualTo("2000-12-25");
        }

        /**
         * Check that marshalling an instance of an incorrectly annotated class ({@link PersonBis}) produces invalid
         * XML.
         */
        @Test
        void marshal_givenInstanceOfIncorrectlyAnnotatedClass_shouldGenerateInvalidXml() throws JAXBException {
            // Given
            var person = new PersonBis(M, "Jacques", "Dupont", LocalDate.of(2000, 12, 25), 1, 2000);

            // A JAXB marshaller
            JAXBContext context = JAXBContext.newInstance(PersonBis.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            StringWriter sw = new StringWriter();

            // When
            marshaller.marshal(person, sw);

            // Then
            String marshalled = sw.toString();

            // XML validation
            Validator validator = Validator.forLanguage(Languages.W3C_XML_SCHEMA_NS_URI);
            validator.setSchemaSource(Input.fromStream(AppTest.class.getResourceAsStream("/individual.xsd")).build());
            ValidationResult result = validator.validateInstance(new StreamSource(new StringReader(marshalled)));
            assertThat(result.isValid()).isFalse();
        }

        /**
         * Check that marshalling an instance of a correctly annotated class ({@link Person}) does not produce an
         * exception when the marshaller knows about the schema.
         */
        @Test
        void marshal_givenInstanceOfAnnotatedClassAndSchema_shouldWork() throws JAXBException, SAXException {
            // Given
            var person = new Person(M, "Jacques", "Dupont", LocalDate.of(2000, 12, 25), 1, 2000);

            // JAXB marshaller
            JAXBContext context = JAXBContext.newInstance(Person.class);
            Marshaller marshaller = context.createMarshaller();
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(Input.fromStream(AppTest.class.getResourceAsStream("/individual.xsd")).build());
            marshaller.setSchema(schema);
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            StringWriter sw = new StringWriter();

            // When - Then
            assertThatCode(() -> marshaller.marshal(person, sw)).doesNotThrowAnyException();
        }

        /**
         * Check that marshalling an instance of an incorrectly annotated class ({@link PersonBis}) produces an exception
         * when the marshaller knows about the schema.
         */
        @Test
        void marshal_givenInstanceOfAnnotatedClassAndSchema_shouldThrowExceptionWhenMismath() throws JAXBException,
                SAXException {
            // Given
            var person = new PersonBis(M, "Jacques", "Dupont", LocalDate.of(2000, 12, 25), 1, 2000);

            // JAXB marshaller
            JAXBContext context = JAXBContext.newInstance(PersonBis.class);
            Marshaller marshaller = context.createMarshaller();
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(Input.fromStream(AppTest.class.getResourceAsStream("/individual.xsd")).build());
            marshaller.setSchema(schema);
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            StringWriter sw = new StringWriter();

            // When - Then
            assertThatThrownBy(() -> marshaller.marshal(person, sw)).isInstanceOf(MarshalException.class);
        }
    }

    @Nested
    class Unmarshalling {

        /**
         * Check that unmarshalling works as long as all the necessary fields are present (in any order and even when a
         * transient field is present).
         */
        @ParameterizedTest
        @ValueSource(strings = {"/individual.xml", "/individual_mismatch.xml", "/individual_with_extra_elements.xml"})
        void unmarshall_givenXmlWithNecessaryElements_shouldGenerateInstance(String xmlFilePath) throws JAXBException {
            // Given
            Source xmlSource = Input.fromStream(AppTest.class.getResourceAsStream(xmlFilePath)).build();

            // A JAXB unmarshaller
            JAXBContext context = JAXBContext.newInstance(PersonForUnmarshalling.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            var expectedPerson = new PersonForUnmarshalling(M, "Michel", "Martin", LocalDate.of(1950, 6, 25), 3, 0);

            // When
            var actualPerson = (PersonForUnmarshalling) unmarshaller.unmarshal(xmlSource);

            // Then
            assertThat(actualPerson)
                    .isNotNull()
                    .usingRecursiveComparison()
                    .isEqualTo(expectedPerson);
        }

        /**
         * Check that unmarshalling an incorrect XML file relative to a schema produces an exception (wrong order or
         * extra elements).
         */
        @ParameterizedTest
        @ValueSource(strings = {"/individual_mismatch.xml", "/individual_with_extra_elements.xml"})
        void unmarshal_givenIncorrectXmlAndSchema_shouldThrowException(String xmlFilePath) throws JAXBException, SAXException {
            // Given
            // A JAXB unmarshaller
            JAXBContext context = JAXBContext.newInstance(Person.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(Input.fromStream(AppTest.class.getResourceAsStream("/individual.xsd")).build());
            unmarshaller.setSchema(schema);
            Source source = Input.fromStream(AppTest.class.getResourceAsStream(xmlFilePath)).build();

            // When - Then
            assertThatThrownBy(() -> unmarshaller.unmarshal(source)).isInstanceOf(UnmarshalException.class);
        }
    }

}
