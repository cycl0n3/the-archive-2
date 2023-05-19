package com.classicmodels.repository;

import com.classicmodels.pojo.LegacyEmployee;
import java.util.List;
import static jooq.generated.tables.Employee.EMPLOYEE;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public class ClassicModelsRepository {

    private final DSLContext ctx;

    public ClassicModelsRepository(DSLContext ctx) {
        this.ctx = ctx;
    }

    public void fetchEmployees() {

        List<LegacyEmployee> result = ctx.selectFrom(EMPLOYEE)
                .where(EMPLOYEE.SALARY.gt(75000))
                .fetchInto(LegacyEmployee.class);

        System.out.println("Result:\n" + result);
    }
}
