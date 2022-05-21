package canvara.fhir.phr.api.masterdata.service;

import canvara.fhir.phr.api.masterdata.entity.MasterDetail;
import canvara.fhir.phr.api.masterdata.exception.MasterDataException;
import canvara.fhir.phr.api.masterdata.repository.MasterDataRepository;
import canvara.fhir.pojos.constant.MasterDataConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static canvara.fhir.phr.api.masterdata.constant.AppConstant.*;
import static canvara.fhir.phr.api.masterdata.values.MasterDataValues.*;

@Component
public class MasterService {
    private static final Logger logger = LoggerFactory.getLogger(MasterService.class);

    @Autowired
    private MasterDataRepository masterDataRepository;

    private final HashMap<String, List<String>> masterDataMap = new HashMap<>();

    public void insertDataInMasterMap(String key, List<String> value) {
        masterDataMap.put(key, value);
    }

    public List<String> getMasterData(String key) {
        return masterDataMap.get(key);
    }

    public void insertMasterData() throws MasterDataException {
        MasterDetail masterDetail = new MasterDetail();
        try {
            masterDetail.setOrgType(organizationTypes);
            masterDetail.setContactSystem(contactSystems);
            masterDetail.setContactUse(contactUse);
            masterDetail.setAddressUse(addressUse);
            masterDetail.setAddressType(addressType);
            masterDetail.setContactPurpose(contactPurpose);
            masterDetail.setHumanNameUse(humanNameUse);
            masterDetail.setEndPointStatus(endPointStatus);
            masterDetail.setLocStatus(locationStatus);
            masterDetail.setLocMode(locationModes);
            masterDetail.setDays(days);
            masterDetail.setGender(genders);
            masterDetail.setAssurance(assurance);
            masterDetail.setMaritalStatus(maritalStatus);
            masterDetail.setLangCodes(languageCodes);
            masterDetail.setQualificationCodes(qualificationCodes);
            masterDetail.setPractitionerRoleCode(practitionerRoleCode);
            masterDetail.setPractitionerRoleSpecialty(practitionerRoleSpecialty);
            masterDetail.setEndPointConnectionType(endpointConnectionType);
            masterDetail.setEndPointPayloadType(endpointPayloadType);
            masterDetail.setEndPointPayloadMimeType(endpointPayloadMimeType);
            masterDetail.setServiceCategory(serviceCategory);
            masterDetail.setServiceType(serviceType);
            masterDetail.setServiceSpeciality(serviceSpeciality);
            masterDetail.setServiceProvisionCode(serviceProvisionCode);
            masterDetail.setProgram(program);
            masterDetail.setReferralMethod(referralMethod);
            masterDetail.setAccountStatus(accountStatus);
            masterDetail.setAccountType(accountType);
            masterDetail.setPatientRelation(patientRelation);
            masterDetail.setPatientLinkType(patientLinkType);
            if ((masterDataRepository.findAll().size() > 0)) {
                masterDataRepository.deleteAll();
            }
            masterDataRepository.save(masterDetail);
        } catch (Exception exp) {
            throw new MasterDataException(MASTER_DATA_LOAD_FAIL);
        }
    }

    /**
     * key is master field name
     *
     * @param key
     * @return
     */
    public void loadMasterData(String key) throws Exception {
        logger.info(MASTER_DATA_LOADING);
        AtomicReference<Boolean> isDataPresent = new AtomicReference<>(true);
        masterDataRepository.findAll().stream().findFirst().ifPresentOrElse(masterDetail -> {
            insertDataInMasterMap(MasterDataConstant.GENDER_KEY, masterDetail.getGender());
            insertDataInMasterMap(MasterDataConstant.ASSURANCE_KEY, masterDetail.getAssurance());
            insertDataInMasterMap(MasterDataConstant.MARITAL_KEY, masterDetail.getMaritalStatus());
            insertDataInMasterMap(MasterDataConstant.LANG_KEY, masterDetail.getLangCodes());
            insertDataInMasterMap(MasterDataConstant.ORG_TYPE_KEY, masterDetail.getOrgType());
            insertDataInMasterMap(MasterDataConstant.CONTACT_SYSTEM_KEY, masterDetail.getContactSystem());
            insertDataInMasterMap(MasterDataConstant.CONTACT_USE_KEY, masterDetail.getContactUse());
            insertDataInMasterMap(MasterDataConstant.ADDRESS_USE_KEY, masterDetail.getAddressUse());
            insertDataInMasterMap(MasterDataConstant.ADDRESS_TYPE_KEY, masterDetail.getAddressType());
            insertDataInMasterMap(MasterDataConstant.CONTACT_PURPOSE_KEY, masterDetail.getContactPurpose());
            insertDataInMasterMap(MasterDataConstant.HUMAN_NAME_USE_KEY, masterDetail.getHumanNameUse());
            insertDataInMasterMap(MasterDataConstant.ENDPOINT_STATUS_KEY, masterDetail.getEndPointStatus());
            insertDataInMasterMap(MasterDataConstant.LOC_STATUS_KEY, masterDetail.getLocStatus());
            insertDataInMasterMap(MasterDataConstant.LOC_MODE_KEY, masterDetail.getLocMode());
            insertDataInMasterMap(MasterDataConstant.DAYS_KEY, masterDetail.getDays());
            insertDataInMasterMap(MasterDataConstant.PAYLOAD_TYPE_KEY, masterDetail.getEndPointPayloadType());
        }, () -> isDataPresent.set(false));

        if (!isDataPresent.get()) throw new MasterDataException(MASTER_DATA_IS_NULL);
        logger.info(MASTER_DATA_LOAD_SUCCESSFULLY);

    }
}
