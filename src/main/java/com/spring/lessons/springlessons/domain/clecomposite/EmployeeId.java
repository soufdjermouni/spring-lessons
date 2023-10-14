package com.spring.lessons.springlessons.domain.clecomposite;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeId implements Serializable {

    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "company_id")
    private Long companyId;
}
