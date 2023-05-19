package com.classicmodels.repository;

import jooq.generated.Routines;
import static jooq.generated.Routines.getSalaryStat;
import jooq.generated.routines.GetSalaryStat;
import jooq.generated.routines.Swap;
import static jooq.generated.tables.Employee.EMPLOYEE;
import org.jooq.DSLContext;
import static org.jooq.impl.DSL.val;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public class ClassicModelsRepository {

    private final DSLContext ctx;

    public ClassicModelsRepository(DSLContext ctx) {
        this.ctx = ctx;
    }
    
    public void executeOutParamFunction() {

        // EXECUTION 1
        GetSalaryStat salStat1 = new GetSalaryStat();
        salStat1.execute(ctx.configuration());

        System.out.println("Execution 1 (min sal): " + salStat1.getReturnValue());
        System.out.println("Execution 1 (min sal): " + salStat1.getMinSal());
        System.out.println("Execution 1 (max sal): " + salStat1.getMaxSal());
        System.out.println("Execution 1 (avg sal): " + salStat1.getAvgSal());

        // EXECUTION 2
        GetSalaryStat salStat2 = Routines.getSalaryStat(ctx.configuration());
        System.out.println("Execution 2 (min sal): " + salStat2.getReturnValue());
        System.out.println("Execution 2 (min sal): " + salStat2.getMinSal());
        System.out.println("Execution 2 (max sal): " + salStat2.getMaxSal());
        System.out.println("Execution 2 (avg sal): " + salStat2.getAvgSal());

        // EXECUTION 3
        ctx.select(val(getSalaryStat(ctx.configuration()).getReturnValue()),
                val(getSalaryStat(ctx.configuration()).getMinSal()),
                val(getSalaryStat(ctx.configuration()).getMaxSal()),
                val(getSalaryStat(ctx.configuration()).getAvgSal())).fetch();
        ctx.fetchValue(val(Routines.getSalaryStat(ctx.configuration()).getMinSal()));
        ctx.fetchValue(val(Routines.getSalaryStat(ctx.configuration()).getReturnValue()));

        // EXECUTION 4
        ctx.select(EMPLOYEE.FIRST_NAME, EMPLOYEE.LAST_NAME, EMPLOYEE.SALARY)
                .from(EMPLOYEE)
                .where(EMPLOYEE.SALARY.coerce(Double.class)
                        .gt(getSalaryStat(ctx.configuration()).getAvgSal()))
                .fetch();
        
        ctx.select(EMPLOYEE.FIRST_NAME, EMPLOYEE.LAST_NAME, EMPLOYEE.SALARY)
                .from(EMPLOYEE)
                .where(EMPLOYEE.SALARY.coerce(Double.class)
                        .gt(getSalaryStat(ctx.configuration()).getReturnValue()))
                .fetch();
    }

    public void executeInOutParamFunction() {
        
        // EXECUTION 1
        Swap swap = new Swap();
        swap.setX(5);
        swap.setY(10);
        
        swap.execute(ctx.configuration());               
                
        System.out.println("Execution 1 (X): " + swap.getX());
        System.out.println("Execution 1 (Y): " + swap.getY());
        System.out.println("Execution 1 (X+Y): " + swap.getReturnValue());
    }
}
