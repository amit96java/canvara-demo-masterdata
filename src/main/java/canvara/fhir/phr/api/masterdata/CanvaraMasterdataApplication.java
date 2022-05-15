package canvara.fhir.phr.api.masterdata;

import canvara.fhir.phr.api.masterdata.exception.MasterDataException;
import canvara.fhir.phr.api.masterdata.service.CoverageMasterDataService;
import canvara.fhir.phr.api.masterdata.service.InsurancePlanMasterDataService;
import canvara.fhir.phr.api.masterdata.service.MasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class CanvaraMasterdataApplication {
    @Autowired private MasterService masterService;
    @Autowired private CoverageMasterDataService coverageMasterDataService;
    @Autowired private InsurancePlanMasterDataService insurancePlanMasterDataService;

    public static void main(String[] args) {
        SpringApplication.run(CanvaraMasterdataApplication.class, args);
    }

    @PostConstruct
    public void insertMasterData() throws MasterDataException {
        masterService.insertMasterData();
        coverageMasterDataService.insertCoverageMasterData();
        insurancePlanMasterDataService.insertInsurancePlanMasterData();

    }

}
