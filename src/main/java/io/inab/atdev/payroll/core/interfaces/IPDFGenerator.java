package io.inab.atdev.payroll.core.interfaces;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Map;

public interface IPDFGenerator {
    byte[] generatePdfFile(String templateName, Map<String, Object> data, String pdfFileName);
}
