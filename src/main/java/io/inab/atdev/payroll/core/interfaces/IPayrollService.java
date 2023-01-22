package io.inab.atdev.payroll.core.interfaces;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IPayrollService<T> {
    List<T> process(MultipartFile file, String locale, String company) throws IOException;
}
