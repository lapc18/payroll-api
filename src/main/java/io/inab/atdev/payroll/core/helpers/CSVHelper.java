package io.inab.atdev.payroll.core.helpers;

import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

public class CSVHelper {

    public static <T> List<T> toList(MultipartFile file, Class<T> tClass) throws Exception {
        if(file.isEmpty() || tClass == null) return null;

        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

            var csvToBean = new CsvToBeanBuilder<T>(reader)
                    .withType(tClass)
                    .withIgnoreQuotations(true)
                    .build();

            return csvToBean.parse();
        } catch (Exception ex) {
            if(ex instanceof RuntimeException) throw new RuntimeException("Number of data fields does not match number of headers!");

            throw new Exception(ex);
        }
    }

}
