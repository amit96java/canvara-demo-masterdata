package canvara.fhir.phr.api.masterdata.repository;

import canvara.fhir.phr.api.masterdata.entity.CoverageMasterDetail;
import canvara.fhir.phr.api.masterdata.entity.InsurancePlanMasterDetail;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsuranceMasterDataRepository extends MongoRepository<InsurancePlanMasterDetail, String> {
}
