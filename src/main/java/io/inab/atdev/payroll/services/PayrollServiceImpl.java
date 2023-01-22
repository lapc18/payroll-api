package io.inab.atdev.payroll.services;

import io.inab.atdev.payroll.core.enums.Locales;
import io.inab.atdev.payroll.core.helpers.CSVHelper;
import io.inab.atdev.payroll.core.interfaces.IPayrollService;
import io.inab.atdev.payroll.core.models.MailDetails;
import io.inab.atdev.payroll.core.models.PayrollResponse;
import io.inab.atdev.payroll.domain.entities.Employee;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cglib.core.Local;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;


@Service
public class PayrollServiceImpl implements IPayrollService {

    @Autowired
    private MailServiceImpl mailService;

    @Autowired
    private PDFGeneratorImpl pdfGeneratorService;

    @Value("${spring.mail.username}")
    private String from;

    @Value(value = "classpath:static/atdev.png")
    private Resource company;

    @Value(value = "classpath:static/fakeClients.png")
    private Resource fakeCompany;

    @Override
    public List<PayrollResponse> process(MultipartFile file, @Nullable String locale, @Nullable String company) throws IOException {
        String filename = "payroll_" + Calendar.getInstance().getTime() + ".pdf";
        var templateName = this.getTemplateName(locale);
        List<Employee> employees = null;
        List<PayrollResponse> results = new ArrayList<>();


        if(!locale.toLowerCase().contains(Locales.LOCALE_US.toString()) || !locale.toLowerCase().contains(Locales.LOCALE_DO.toString()))
            locale = Locales.LOCALE_DO.toString();





        try {
            employees = new LinkedList<Employee>(Objects.requireNonNull(CSVHelper.toList(file, Employee.class)));
            employees.forEach(employee -> {
                Map<String, Object> map = new HashMap<>();
                var companyImageFile = this.getCompanyLocalFile(company);
                var base64Img = this.fileToBase64(companyImageFile);
                var totalDiscount = this.getTotalDiscount(employee);

                map.put("employee", employee);
                map.put("company", base64Img);
                map.put("totalDiscounts", totalDiscount);


                var byteArrayInputStreams= this.pdfGeneratorService
                        .generatePdfFile(templateName, map, filename);

                var details = new MailDetails(
                        employee.getEmail(),
                        this.from,
                        "Payroll from period" + employee.getPeriod(),
                        "Please, find attached the Payroll file."
                );
                details.setAttachment(byteArrayInputStreams);
                var emailResponse = this.mailService.sendEmailWithAttachment(details, filename);
                results.add(new PayrollResponse(emailResponse.getEmail(), emailResponse.getDateTime().toString(), emailResponse.isEmailSent()));
            });

            return results;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private File getCompanyLocalFile(String company) {
        try {
            if(company == null || company.length() == 0) return this.fakeCompany.getFile();
            if(company.toLowerCase().contains("atdev")) return this.company.getFile();
        } catch (IOException e) {
            return null;
        }
        return null;
    }

    private String fileToBase64(@Nullable File file) {
        if(file == null) return null;

        var base64 = new org.apache.tomcat.util.codec.binary.Base64();
        byte[] img = new byte[0];
        try {
            img = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            return null;
        }
        return base64.encodeAsString(img);
    }

    private String getTemplateName(String locale) {
        if(locale.toLowerCase().contains(Locales.LOCALE_US.toString())) return "/email-template-en.html";

        return "/email-template-es.html";
    }

    private double getTotalDiscount(Employee employee) {
        return (employee.getSocial_discount_amount() +
                        employee.getHealth_discount_amount() +
                        employee.getTaxes_discount_amount() +
                        employee.getOther_discount_amount());
    }
}
