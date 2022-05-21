package canvara.fhir.phr.api.masterdata.repository;

import canvara.fhir.phr.api.masterdata.entity.MasterDetail;
import canvara.fhir.phr.api.masterdata.entity.OrganizationAfiliationMasterDetail;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationAfiliationDataRepository extends MongoRepository<OrganizationAfiliationMasterDetail, String> {
}
