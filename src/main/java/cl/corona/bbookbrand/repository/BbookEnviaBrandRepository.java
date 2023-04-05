package cl.corona.bbookbrand.repository;

import cl.corona.bbookbrand.model.BbookEnviaBrand;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BbookEnviaBrandRepository extends CrudRepository<BbookEnviaBrand, String> {

    public List<BbookEnviaBrand> findAllByTranType(String tranType);

    @Modifying(clearAutomatically = true)
    @Query(value = "update app_sam.sdi_sdibasatr s set s.download_date_1 = sysdate, s.observacion = :responses where rowid = :id", nativeQuery = true)
    public void updSdiSdibasatr(@Param("id") String id, @Param("responses") String responses);
}
