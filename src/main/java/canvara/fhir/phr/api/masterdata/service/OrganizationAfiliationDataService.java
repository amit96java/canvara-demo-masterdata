package canvara.fhir.phr.api.masterdata.service;

import canvara.fhir.phr.api.masterdata.constant.AppConstant;
import canvara.fhir.phr.api.masterdata.entity.OrganizationAfiliationMasterDetail;
import canvara.fhir.phr.api.masterdata.exception.MasterDataException;
import canvara.fhir.phr.api.masterdata.repository.OrganizationAfiliationDataRepository;
import canvara.fhir.pojos.constant.MasterDataConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static canvara.fhir.phr.api.masterdata.constant.AppConstant.*;
import static canvara.fhir.phr.api.masterdata.values.OrganizationAfiliationValues.*;

@Service
public class OrganizationAfiliationDataService {
    private static final Logger logger = LoggerFactory.getLogger(OrganizationAfiliationDataService.class);

    @Autowired private OrganizationAfiliationDataRepository organizationAfiliationDataRepository;

    private final HashMap<String, List<String>> orgAfiliationMasterDataContainer = new HashMap<>();

    public void insertDataInOrgAfiliationContainer(final String key, final List<String> value) {
        orgAfiliationMasterDataContainer.put(key, value);
    }

    public List<String> getOrgAfiliationMasterData(final String key) {
        return orgAfiliationMasterDataContainer.get(key);
    }

    public void insertOrgAfiliationMasterData() throws MasterDataException {
        OrganizationAfiliationMasterDetail organizationAfiliationMasterDetail = new OrganizationAfiliationMasterDetail();
        try {
            organizationAfiliationMasterDetail.setOrgAfiliationCode(orgAfiliationCode);
            organizationAfiliationMasterDetail.setOrgAfiliationSpeciality(orgAfiliationSpeciality);
            organizationAfiliationMasterDetail.setOrgAfiliationContactPointSystem(contactPointSystem);
            organizationAfiliationMasterDetail.setOrgAfiliationContactPointUse(contactPointUse);
            //delete master data
            if ((organizationAfiliationDataRepository.findAll().size() > 0)) {
                organizationAfiliationDataRepository.deleteAll();
            }
            //save master data
            organizationAfiliationDataRepository.save(organizationAfiliationMasterDetail);
        } catch (Exception exp) {
            throw new MasterDataException(OrgAfiliationConstant.ORG_AFILIATION_MASTER_DATA_LOAD_FAIL);
        }
    }

    public HashMap<String, List<String>> getInsurancePLanMasterData() throws MasterDataException {
        logger.info(OrgAfiliationConstant.RESOURCE_ORG_AFILIATION+" "+MASTER_DATA_LOADING);
        AtomicReference<Boolean> isDataPresent = new AtomicReference<>(true);

        organizationAfiliationDataRepository.findAll().stream().findFirst().ifPresentOrElse(data -> {
            insertDataInOrgAfiliationContainer(
                    MasterDataConstant.OrganizationAfiliationKeyConstant.ORG_AFILIATION_CODE_KEY, data.getOrgAfiliationCode()
            );
            insertDataInOrgAfiliationContainer(
                    MasterDataConstant.OrganizationAfiliationKeyConstant.ORG_AFILIATION_SPECIALITY_KEY, data.getOrgAfiliationSpeciality()
            );
            insertDataInOrgAfiliationContainer(
                    MasterDataConstant.OrganizationAfiliationKeyConstant.ORG_AFILIATION_CONTACT_POINT_SYSTEM_KEY, data.getOrgAfiliationContactPointSystem()
            );
            insertDataInOrgAfiliationContainer(
                    MasterDataConstant.OrganizationAfiliationKeyConstant.ORG_AFILIATION_CONTACT_POINT_USE_KEY, data.getOrgAfiliationContactPointUse()
            );

        }, () -> isDataPresent.set(false));

        if (!isDataPresent.get()) throw new MasterDataException(OrgAfiliationConstant.RESOURCE_ORG_AFILIATION+" "+MASTER_DATA_IS_NULL);
        logger.info(OrgAfiliationConstant.RESOURCE_ORG_AFILIATION+" "+MASTER_DATA_LOAD_SUCCESSFULLY);
        return orgAfiliationMasterDataContainer;
    }
}
