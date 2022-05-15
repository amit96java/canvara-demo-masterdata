package canvara.fhir.phr.api.masterdata.service;

import canvara.fhir.phr.api.masterdata.entity.CoverageMasterDetail;
import canvara.fhir.phr.api.masterdata.entity.InsurancePlanMasterDetail;
import canvara.fhir.phr.api.masterdata.exception.MasterDataException;
import canvara.fhir.phr.api.masterdata.repository.CoverageMasterDataRepository;
import canvara.fhir.phr.api.masterdata.repository.InsuranceMasterDataRepository;
import canvara.fhir.phr.api.masterdata.values.InsurancePlanMasterDataValues;
import canvara.fhir.pojos.constant.MasterDataConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static canvara.fhir.phr.api.masterdata.constant.MasterDataConstant.*;
import static canvara.fhir.phr.api.masterdata.values.CoverageMasterDataValues.*;
import static canvara.fhir.pojos.constant.MasterDataConstant.InsurancePlanKeyConstant.*;

@Service
public class InsurancePlanMasterDataService extends InsurancePlanMasterDataValues {
    private static final Logger logger = LoggerFactory.getLogger(InsurancePlanMasterDataService.class);

    @Autowired
    private InsuranceMasterDataRepository insuranceMasterDataRepository;

    private final HashMap<String, List<String>> insurancePlanMasterDataContainer = new HashMap<>();

    public void insertDataInInsurancePlanContainer(final String key, final List<String> value) {
        insurancePlanMasterDataContainer.put(key, value);
    }

    public List<String> getInsurancePlanMasterData(final String key) {
        return insurancePlanMasterDataContainer.get(key);
    }

    public void insertInsurancePlanMasterData() throws MasterDataException {
        InsurancePlanMasterDetail insurancePlanMasterDetail = new InsurancePlanMasterDetail();
        try {

            insurancePlanMasterDetail.setInsurancePlanStatus(insurancePlanStatus);
            insurancePlanMasterDetail.setInsurancePlanType(insurancePlanType);
            insurancePlanMasterDetail.setInsuranceContactPurpose(insuranceContactPurpose);
            insurancePlanMasterDetail.setInsuranceHumanName(insuranceHumanNameUse);
            insurancePlanMasterDetail.setInsuranceContactPointSystem(insuranceContactPointSystem);
            insurancePlanMasterDetail.setInsuranceContactPointUse(insuranceContactPointUse);
            insurancePlanMasterDetail.setInsuranceAddressUse(insuranceAddressUse);
            insurancePlanMasterDetail.setInsuranceAddressType(insuranceAddressType);
            insurancePlanMasterDetail.setInsurancePlanPlanType(insurancePlanPlanType);
            insurancePlanMasterDetail.setInsuranceCoverageType(insuranceCoverageType);
            insurancePlanMasterDetail.setInsuranceCoverageBenefitType(insuranceCoverageBenefitType);
            insurancePlanMasterDetail.setInsuranceCoverageBenefitLimitCode(insuranceCoverageLimit);
            insurancePlanMasterDetail.setInsuranceGeneralCostType(insuranceGeneralCostType);
            insurancePlanMasterDetail.setInsuranceGeneralCostCurrency(insuranceGeneralCurrency);
            insurancePlanMasterDetail.setInsuranceSpecificCostCategory(insuranceSpecificCategory);
            insurancePlanMasterDetail.setInsuranceSpecificCostBenefitType(insuranceSpecificBenefitType);
            insurancePlanMasterDetail.setInsuranceSpecificCostBenefitCostType(insuranceSpecificBenefitCostType);
            insurancePlanMasterDetail.setInsuranceSpecificCostApplicability(insuranceApplicability);
            insurancePlanMasterDetail.setInsuranceSpecificCostQualifier(insuranceQualifier);
            //delete master data
            if ((insuranceMasterDataRepository.findAll().size() > 0)) {
                insuranceMasterDataRepository.deleteAll();
            }
            //save master data
            insuranceMasterDataRepository.save(insurancePlanMasterDetail);
        } catch (Exception exp) {
            throw new MasterDataException(COVERAGE_MASTER_DATA_LOAD_FAIL);
        }
    }

    public HashMap<String, List<String>> getInsurancePLanMasterData() throws MasterDataException {
        logger.info(RESOURCE_INSURANCE_PLAN+" "+MASTER_DATA_LOADING);
        AtomicReference<Boolean> isDataPresent = new AtomicReference<>(true);

        insuranceMasterDataRepository.findAll().stream().findFirst().ifPresentOrElse(data -> {
            insertDataInInsurancePlanContainer(INSURANCE_PLAN_STATUS_KEY, data.getInsurancePlanStatus());
            insertDataInInsurancePlanContainer(INSURANCE_PLAN_TYPE_KEY, data.getInsurancePlanType());
            insertDataInInsurancePlanContainer(INSURANCE_PLAN_CONTACT_PURPOSE_KEY, data.getInsuranceContactPurpose());
            insertDataInInsurancePlanContainer(INSURANCE_PLAN_HUMAN_NAME_USE_KEY, data.getInsuranceHumanName());
            insertDataInInsurancePlanContainer(INSURANCE_PLAN_CONTACT_POINT_SYSTEM_KEY, data.getInsuranceContactPointSystem());
            insertDataInInsurancePlanContainer(INSURANCE_PLAN_CONTACT_POINT_USE_KEY, data.getInsuranceContactPointUse());
            insertDataInInsurancePlanContainer(INSURANCE_PLAN_ADDRESS_USE_KEY, data.getInsuranceAddressUse());
            insertDataInInsurancePlanContainer(INSURANCE_PLAN_ADDRESS_TYPE_KEY, data.getInsuranceAddressType());
            insertDataInInsurancePlanContainer(INSURANCE_PLAN_PLAN_TYPE_KEY, data.getInsurancePlanPlanType());
            insertDataInInsurancePlanContainer(INSURANCE_PLAN_COVERAGE_TYPE_KEY, data.getInsuranceCoverageType());
            insertDataInInsurancePlanContainer(INSURANCE_PLAN_COVERAGE_BENEFIT_TYPE_KEY, data.getInsuranceCoverageBenefitType());
            insertDataInInsurancePlanContainer(INSURANCE_PLAN_COVERAGE_LIMIT_KEY, data.getInsuranceCoverageBenefitLimitCode());
            insertDataInInsurancePlanContainer(INSURANCE_PLAN_GENERAL_COST_TYPE_KEY, data.getInsuranceGeneralCostType());
            insertDataInInsurancePlanContainer(INSURANCE_PLAN_GENERAL_CURRENCY_KEY, data.getInsuranceGeneralCostCurrency());
            insertDataInInsurancePlanContainer(INSURANCE_PLAN_SPECIFIC_CATEGORY_KEY, data.getInsuranceSpecificCostCategory());
            insertDataInInsurancePlanContainer(INSURANCE_PLAN_SPECIFIC_BENEFIT_TYPE_KEY, data.getInsuranceSpecificCostBenefitType());
            insertDataInInsurancePlanContainer(INSURANCE_PLAN_SPECIFIC_BENEFIT_COST_KEY, data.getInsuranceSpecificCostBenefitCostType());
            insertDataInInsurancePlanContainer(INSURANCE_PLAN_APPLICABILITY_KEY, data.getInsuranceSpecificCostApplicability());
            insertDataInInsurancePlanContainer(INSURANCE_PLAN_QUALIFIER_KEY, data.getInsuranceSpecificCostQualifier());

        }, () -> isDataPresent.set(false));

        if (!isDataPresent.get()) throw new MasterDataException(RESOURCE_INSURANCE_PLAN+" "+MASTER_DATA_IS_NULL);
        logger.info(RESOURCE_INSURANCE_PLAN+" "+MASTER_DATA_LOAD_SUCCESSFULLY);
        return insurancePlanMasterDataContainer;
    }
}
