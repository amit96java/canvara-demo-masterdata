package canvara.fhir.phr.api.masterdata.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;
import canvara.fhir.pojos.dto.master.data.OrganizationAfiliationMasterDataDetail;
/**
 * @author amit pal
 */
@Document(collection = "canvara_organization_afiliation_master_data")
@Data
@NoArgsConstructor
@ToString
public class OrganizationAfiliationMasterDetail extends OrganizationAfiliationMasterDataDetail {

}
