package io.inab.atdev.payroll.core.interfaces;

import org.springframework.web.multipart.MultipartFile;

public interface IPayrollService {
    void process(MultipartFile file);
}
