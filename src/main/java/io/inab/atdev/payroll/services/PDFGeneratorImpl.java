package io.inab.atdev.payroll.services;

import com.lowagie.text.DocumentException;
import io.inab.atdev.payroll.core.interfaces.IPDFGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.exceptions.TemplateEngineException;
import org.thymeleaf.exceptions.TemplateInputException;
import org.thymeleaf.exceptions.TemplateProcessingException;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.util.Map;

@Service
public class PDFGeneratorImpl implements IPDFGenerator {

    @Autowired
    private TemplateEngine templateEngine;

    @Override
    public byte[] generatePdfFile(String templateName, Map<String, Object> data, String pdfFileName) {
        Context context = new Context();
        context.setVariables(data);



        try {
            String htmlContent = templateEngine.process(templateName, context);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(htmlContent);
            renderer.layout();
            renderer.createPDF(baos);
            baos.close();

            return baos.toByteArray();
        } catch (DocumentException | IOException | TemplateProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
