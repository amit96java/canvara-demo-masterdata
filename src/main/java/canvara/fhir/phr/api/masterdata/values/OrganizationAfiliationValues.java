package canvara.fhir.phr.api.masterdata.values;

import java.util.Arrays;
import java.util.List;

public class OrganizationAfiliationValues {
    public static final List<String> orgAfiliationCode = Arrays.asList(
            "mock-code", "updated-code"
    );
    public static final List<String> orgAfiliationSpeciality = Arrays.asList(
            "mock-speciality", "updated-speciality"
    );
    public static final List<String> contactPointSystem = Arrays.asList(
            "mock-system", "updated-system"
    );
    public static final List<String> contactPointUse = Arrays.asList(
            "mock-use", "updated-use"
    );
}
