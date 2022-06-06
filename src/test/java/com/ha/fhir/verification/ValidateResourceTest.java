package com.ha.fhir.verification;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.IParser;
import ca.uhn.fhir.parser.StrictErrorHandler;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.validation.FhirValidator;
import ca.uhn.fhir.validation.IValidatorModule;
import ca.uhn.fhir.validation.SingleValidationMessage;
import ca.uhn.fhir.validation.ValidationResult;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import org.hl7.fhir.common.hapi.validation.validator.FhirInstanceValidator;
import org.hl7.fhir.r4.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.util.ResourceUtils;

import java.io.*;

public class ValidateResourceTest {

    @Test
    public void ValidateResourceXmlString(){
        FhirContext ctx = FhirContext.forR4();

        // Create a parser and configure it to use the strict error handler
        IParser parser = ctx.newXmlParser();
        //如果检测到任何错误，StrictErrorHandler将引发DataFormatException 。
        parser.setParserErrorHandler(new StrictErrorHandler());
        //记录任何错误但不会中止解析
        //parser.setParserErrorHandler(new LenientErrorHandler());

        // This example resource is invalid, as Patient.active can not repeat
        String input = "<Patient><active value=\"true\"/><active value=\"false\"/></Patient>";

        // The following will throw a DataFormatException because of the StrictErrorHandler
        parser.parseResource(Patient.class, input);
    }

    @Test
    public void ValidateResourceJsonString(){
        FhirContext ctx = FhirContext.forR4();

        // Create a parser and configure it to use the strict error handler
        IParser parser = ctx.newJsonParser();
        parser.setParserErrorHandler(new StrictErrorHandler());
        //记录任何错误但不会中止解析
        //parser.setParserErrorHandler(new LenientErrorHandler());

        // This example resource is invalid, as Patient.active can not repeat
        //String input = readJsonFile("D:\\src\\fhir-demo\\src\\main\\resources\\ha-data\\Patient001.json");

        // The following will throw a DataFormatException because of the StrictErrorHandler
        parser.parseResource(Patient.class, getClass().getClassLoader().getResourceAsStream("ha-data/Patient001.json"));
    }

    @Test
    public void BundlerValidateResource(){
        FhirContext ctx = FhirContext.forR4();

        // Create a parser and configure it to use the strict error handler
        IParser parser = ctx.newJsonParser();
        parser.setParserErrorHandler(new StrictErrorHandler());
        //记录任何错误但不会中止解析
        //parser.setParserErrorHandler(new LenientErrorHandler());

        // This example resource is invalid, as Patient.active can not repeat
        //String input = readJsonFile("D:\\src\\fhir-demo\\src\\main\\resources\\ha-data\\Bundler1.json");

        // The following will throw a DataFormatException because of the StrictErrorHandler
        parser.parseResource(Bundle.class, getClass().getClassLoader().getResourceAsStream("ha-data/Bundler1.json"));
    }

    @Test
    public void PractitionerRoleValidateResource(){
        FhirContext ctx = FhirContext.forR4();

        // Create a parser and configure it to use the strict error handler
        IParser parser = ctx.newJsonParser();
        parser.setParserErrorHandler(new StrictErrorHandler());

        //记录任何错误但不会中止解析
        //parser.setParserErrorHandler(new LenientErrorHandler());

        // This example resource is invalid, as Patient.active can not repeat
        //String input = readJsonFile("D:\\src\\fhir-demo\\src\\main\\resources\\ha-data\\PractitionerRole0001.json");

        // The following will throw a DataFormatException because of the StrictErrorHandler
        parser.parseResource(PractitionerRole.class,  getClass().getClassLoader().getResourceAsStream("ha-data/PractitionerRole0001.json"));
    }

    @Test
    public void MedicationRequestValidateResource(){
        FhirContext ctx = FhirContext.forR4();

        // Create a parser and configure it to use the strict error handler
        IParser parser = ctx.newJsonParser();
        parser.setParserErrorHandler(new StrictErrorHandler());

        // This example resource is invalid, as Patient.active can not repeat
        //String input = readJsonFile("D:\\src\\fhir-demo\\src\\main\\resources\\ha-data\\MedicationRequest001.json");

        // The following will throw a DataFormatException because of the StrictErrorHandler
        parser.parseResource(MedicationRequest.class, getClass().getClassLoader().getResourceAsStream("ha-data/MedicationRequest001.json"));
    }

    @Test
    public void MedicationDispenseValidateResource(){
        FhirContext ctx = FhirContext.forR4();

        // Create a parser and configure it to use the strict error handler
        IParser parser = ctx.newJsonParser();
        parser.setParserErrorHandler(new StrictErrorHandler());

        // This example resource is invalid, as Patient.active can not repeat
        //String input = readJsonFile("ha-data/MedicationDispense001.json");

        // The following will throw a DataFormatException because of the StrictErrorHandler
        parser.parseResource(MedicationDispense.class, getClass().getClassLoader().getResourceAsStream("ha-data/MedicationDispense001.json"));
    }

    @Test
    public void ClientsValidateResource(){
        FhirContext ctx = FhirContext.forR4();

        ctx.setParserErrorHandler(new StrictErrorHandler());

        // This client will have strict parser validation enabled
        IGenericClient client = ctx.newRestfulGenericClient("http://hapi.fhir.org/baseR4");
        System.out.println(client);
    }

    @Test
    public void InstanceValidator(){
        FhirContext ctx = FhirContext.forR4();

        // Ask the context for a validator
        FhirValidator validator = ctx.newValidator();

        // Create a validation module and register it
        IValidatorModule module = new FhirInstanceValidator(ctx);
        validator.registerValidatorModule(module);

        // Pass a resource instance as input to be validated
        Patient resource = new Patient();
        resource.addName().setFamily("Simpson").addGiven("Homer");
        ValidationResult result = validator.validateWithResult(resource);
        System.out.println(result.isSuccessful());

        // The input can also be a raw string (this mechanism can
        // potentially catch syntax issues that would have been missed
        // otherwise, since the HAPI FHIR Parser is forgiving about
        // its input.
//        String resourceText = "<Patient.....>";
//        ValidationResult result2 = validator.validateWithResult(resourceText);

        // The result object now contains the validation results
        for (SingleValidationMessage next : result.getMessages()) {
            System.out.println("-------------");
            System.out.println(next.getLocationString() + " " + next.getMessage());
        }
    }
}
