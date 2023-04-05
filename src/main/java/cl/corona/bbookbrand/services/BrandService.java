package cl.corona.bbookbrand.services;

import cl.corona.bbookbrand.model.BbookEnviaBrand;
import cl.corona.bbookbrand.model.JsonCab;
import cl.corona.bbookbrand.model.JsonDet;
import cl.corona.bbookbrand.repository.BbookEnviaBrandRepository;
import cl.corona.bbookbrand.utilities.Utility;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BrandService {

    @Value("${api_url}")
    private String apiurl;

    @Value("${token_uri}")
    private String token;

    @Autowired
    BbookEnviaBrandRepository updsdirepository;

    private static final Logger LOG = LoggerFactory.getLogger(BrandService.class);

    public void EnvioBrand(List<BbookEnviaBrand> bbookbrd, String tipo) {

        Gson g = new Gson();
        JsonCab jsoncab = null;
        List<JsonDet> json = new ArrayList<>();
        HttpEntity<String> entity = null;

        try{

            for (BbookEnviaBrand row : bbookbrd) {

                json.add(new JsonDet(String.valueOf(row.getAtrCode()), row.getatrCodeDesc(), row.getInactive()));
            }

            //jsoncab = new JsonCab(g.toJson(json));
            jsoncab = new JsonCab(json);

            // create full entity.
            HttpHeaders headers = new HttpHeaders();
            headers.set("user-agent", "Application");
            //headers.set("cache-control", "no-cache");

            headers.setContentType(MediaType.APPLICATION_JSON);
            //headers.set("Authorization", "Bearer " + token);
            headers.set("X-Bbook-Token", token);
            //headers.set("Authorization", token);

            // create entity, headers + body
            entity = new HttpEntity<String>(g.toJson(jsoncab), headers);
            //entity = new HttpEntity<String>(g.toJson("\"data\": [{\"id\":\"1050\",\"name\":\"ECOGAR\",\"inactive\":\"false\"},{\"id\":\"660\",\"name\":\"KAYSER\",\"inactive\":\"false\"}]"), headers);

            //System.out.println(entity);

        }catch (Exception e) {

            LOG.error("Error al cargar json", e);

        }

        String responses = null;

        try {

            responses = Utility.BulkUpdateApiPoster(entity, apiurl, tipo);

        }catch (Exception e) {

            LOG.error("Error al enviar datos", e);

            responses = "No se pudo informar Marca";
        }

        for (BbookEnviaBrand row : bbookbrd) {
            updsdirepository.updSdiSdibasatr(row.getId(), responses.substring(1,500));
        }
    }
}
