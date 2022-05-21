package canvara.fhir.phr.api.masterdata.service;

import canvara.fhir.phr.api.masterdata.entity.CoverageMasterDetail;
import canvara.fhir.phr.api.masterdata.exception.MasterDataException;
import canvara.fhir.phr.api.masterdata.repository.CoverageMasterDataRepository;
import canvara.fhir.phr.api.masterdata.values.CoverageMasterDataValues;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static canvara.fhir.phr.api.masterdata.constant.AppConstant.*;
import static canvara.fhir.pojos.constant.MasterDataConstant.CoverageKeyConstant.*;

@Service
public class CoverageMasterDataService extends CoverageMasterDataValues {
    private static final Logger logger = LoggerFactory.getLogger(CoverageMasterDataService.class);

    @Autowired
    private CoverageMasterDataRepository coverageMasterDataRepository;

    private final HashMap<String, List<String>> coverageMasterDataContainer = new HashMap<>();

    public void insertDataInCoverageContainer(final String key, final List<String> value) {
        coverageMasterDataContainer.put(key, value);
    }

    public List<String> getCoverageMasterData(final String key) {
        return coverageMasterDataContainer.get(key);
    }

    public void insertCoverageMasterData() throws MasterDataException {
        CoverageMasterDetail coverageMasterDataDetail = new CoverageMasterDetail();
        try {
            coverageMasterDataDetail.setCoverageType(coverageTypes);
            coverageMasterDataDetail.setCoverageStatus(coverageStatus);
            coverageMasterDataDetail.setCoverageClassType(coverageClassType);
            coverageMasterDataDetail.setCoverageRelationship(coverageRelationship);
            coverageMasterDataDetail.setCoverageBeneficiaryType(coverageBeneficiaryType);
            coverageMasterDataDetail.setCoverageBeneficiaryMoneyCurrency(coverageBeneficiaryMoneyCurrency);
            coverageMasterDataDetail.setCoverageBeneficiaryQuantityCode(coverageBeneficiaryCurrencyCodes);
            coverageMasterDataDetail.setExceptionType(coverageExceptionTypes);
            //delete master data
            if ((coverageMasterDataRepository.findAll().size() > 0)) {
                coverageMasterDataRepository.deleteAll();
            }
            //save master data
            coverageMasterDataRepository.save(coverageMasterDataDetail);
        } catch (Exception exp) {
            throw new MasterDataException(COVERAGE_MASTER_DATA_LOAD_FAIL);
        }
    }

    public HashMap<String, List<String>> getCoverageMasterData() throws MasterDataException {
        logger.info(RESOURCE_COVERAGE+" "+MASTER_DATA_LOADING);
        AtomicReference<Boolean> isDataPresent = new AtomicReference<>(true);

        coverageMasterDataRepository.findAll().stream().findFirst().ifPresentOrElse(data -> {
            insertDataInCoverageContainer(COVERAGE_STATUS_KEY, data.getCoverageStatus());
            insertDataInCoverageContainer(COVERAGE_TYPE_KEY, data.getCoverageType());
            insertDataInCoverageContainer(COVERAGE_RELATIONSHIP_KEY, data.getCoverageRelationship());
            insertDataInCoverageContainer(COVERAGE_CLASS_TYPE_KEY, data.getCoverageClassType());
            insertDataInCoverageContainer(COVERAGE_BENEFICIARY_TYPE_KEY, data.getCoverageBeneficiaryType());
            insertDataInCoverageContainer(COVERAGE_BENEFICIARY_QUANTITY_CODE_KEY, data.getCoverageBeneficiaryQuantityCode());
            insertDataInCoverageContainer(COVERAGE_BENEFICIARY_MONEY_CURRENCY_KEY, data.getCoverageBeneficiaryMoneyCurrency());
            insertDataInCoverageContainer(COVERAGE_EXCEPTION_TYPE_KEY, data.getExceptionType());
        }, () -> isDataPresent.set(false));

        if (!isDataPresent.get()) throw new MasterDataException(RESOURCE_COVERAGE+" "+MASTER_DATA_IS_NULL);
        logger.info(RESOURCE_COVERAGE+" "+MASTER_DATA_LOAD_SUCCESSFULLY);
        return coverageMasterDataContainer;
    }

}
