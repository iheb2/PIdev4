package tn.esprit.pidev.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.pidev.model.Request;

@Repository
public interface RequestRepository extends CrudRepository <Request,Long> {

	@Query(value="SELECT count(*) FROM request where type = 'request_credit' and client_id_client = 1", nativeQuery =true)
	public long countcreditrequest( String type,  Integer client_id);
	@Query(value="SELECT count(*) FROM request where type = 'request_credit_card' and client_id_client = 1", nativeQuery =true)
	public long countcreditrequestcard( String type,  Integer client_id);
	@Query(value="SELECT count(*) FROM request where type = 'request_checkbook' and client_id_client = 1", nativeQuery =true)
	public long countcheckbookrequest( String type,  Integer client_id);

}
