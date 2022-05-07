package canvara.fhir.phr.api.masterdata.values;

import java.util.Arrays;
import java.util.List;

public class CoverageMasterDataValues {

    public static final List<String> coverageTypes = Arrays
            .asList("mock-coverage", "updated-mock-coverage");
    public static final List<String> coverageStatus = Arrays
            .asList("mock-coverage-status", "updated-mock-coverage-status");
    public static final List<String> coverageClassType = Arrays
            .asList("mock-coverage-class-type", "updated-mock-coverage-class-type");
    public static final List<String> coverageRelationship = Arrays
            .asList("mock-coverage-relationship", "updated-mock-coverage-relationship");
    public static final List<String> coverageBeneficiaryType = Arrays
            .asList("mock-coverage-beneficiary", "updated-mock-coverage-beneficiary");
    public static final List<String> coverageBeneficiaryMoneyCurrency = Arrays
            .asList("mock-coverage-beneficiary-money", "updated-mock-coverage-beneficiary-money");
    public static final List<String> coverageBeneficiaryCurrencyCodes = Arrays
            .asList("mock-coverage-currency-code", "updated-mock-coverage-currency-code");
    public static final List<String> coverageExceptionTypes = Arrays
            .asList("mock-coverage-exception", "update-mock-coverage-exception");
}
