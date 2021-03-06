package canvara.fhir.phr.api.masterdata.values;

import java.util.Arrays;
import java.util.List;

public class MasterDataValues {

    public static final List<String> organizationTypes = Arrays
            .asList("prov", "dept", "team", "govt", "ins", "pay", "edu", "reli", "crs", "cg", "bus", "other");
    public static final List<String> contactSystems = Arrays
            .asList("phone", "fax", "email", "paper", "url", "sms", "other");
    public static final List<String> contactUse = Arrays
            .asList("home", "work", "temp", "old", "mobile");
    public static final List<String> addressUse = Arrays
            .asList("home", "work", "temp", "old", "billing");
    public static final List<String> addressType = Arrays
            .asList("postal", "physical", "both");
    public static final List<String> contactPurpose = Arrays
            .asList("bill", "admin", "hr", "payor", "patinf", "press");
    public static final List<String> humanNameUse = Arrays
            .asList("usual", "official", "temp", "nickname", "anonymous", "old", "maiden");
    public static final List<String> endPointStatus = Arrays
            .asList("mock-status", "active", "suspended", "error", "off", "entered_in_error", "test");
    public static final List<String> locationStatus = Arrays
            .asList("active", "suspended", "inactive");
    public static final List<String> locationModes = Arrays
            .asList("instance", "kind");
    public static final List<String> days = Arrays
            .asList("mon", "tue", "wed", "thu", "fri", "sat", "sun");
    public static final List<String> genders = Arrays
            .asList("male", "female", "other", "unknown");
    public static final List<String> assurance = Arrays
            .asList("level1", "level2", "level3", "level4");
    public static final List<String> maritalStatus = Arrays
            .asList("a", "d", "i", "l", "m", "c", "p", "t", "u", "s", "w", "unk");
    public static final List<String> languageCodes = Arrays
            .asList("ar", "bn", "cs", "da", "de", "de-AT");
    //https://terminology.hl7.org/3.1.0/ValueSet-v2-0360.html
    public static final List<String> qualificationCodes = Arrays
            .asList("pn", "aas", "aa", "aba");
    public static final List<String> practitionerRoleCode = Arrays
            .asList("doctor", "nurse", "pharmacist", "researcher", "...remaining");
    public static final List<String> practitionerRoleSpecialty = Arrays
            .asList("408467006", "394577000", "...remaining");
    public static final List<String> endpointConnectionType = Arrays
            .asList("mock-connectionType");
    public static final List<String> endpointPayloadType = Arrays
            .asList("mock-payload-type");
    public static final List<String> endpointPayloadMimeType = Arrays
            .asList("mock-mime-type");
}
