package canvara.fhir.phr.api.masterdata.controller;

import canvara.fhir.phr.api.masterdata.exception.MasterDataException;
import canvara.fhir.phr.api.masterdata.service.CoverageMasterDataService;
import canvara.fhir.phr.api.masterdata.service.InsurancePlanMasterDataService;
import canvara.fhir.phr.api.masterdata.service.MasterService;
import canvara.fhir.pojos.constant.MasterDataConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/masterData")
public class MasterDataController {

   @Autowired
   private MasterService masterService;
   @Autowired private CoverageMasterDataService coverageMasterDataService;
   @Autowired private InsurancePlanMasterDataService insurancePlanMasterDataService;

    @GetMapping(value = "/getCoverageMasterData")
    public ResponseEntity<Map<String, List<String>>> getCoverageMasterData() throws MasterDataException {
        return ResponseEntity.ok(coverageMasterDataService.getCoverageMasterData());
    }

    @GetMapping(value = "/getInsurancePlanMasterData")
    public ResponseEntity<Map<String, List<String>>> getInsurancePlanMasterData() throws MasterDataException {
        return ResponseEntity.ok(insurancePlanMasterDataService.getInsurancePLanMasterData());
    }
}
