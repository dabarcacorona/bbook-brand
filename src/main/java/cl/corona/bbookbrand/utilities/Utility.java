package cl.corona.bbookbrand.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.format.DateTimeFormatter;

public class Utility {

    private static final Logger LOG = LoggerFactory.getLogger(Utility.class);
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");


    static RestTemplate restTemplate = new RestTemplate();

    public static String BulkUpdateApiPoster(HttpEntity<String> entity, String endpoint, String tipo) {
        // declare response entity
        ResponseEntity<String> responseEntity = null;
        String responseStatus = null;
        String responseBody = null;


        try {
            // post request to authentication API
            if (tipo.equals("A")) {
                responseEntity = restTemplate.exchange(endpoint, HttpMethod.POST, entity, String.class);
            }else {
                responseEntity = restTemplate.exchange(endpoint, HttpMethod.GET, entity, String.class);
            }
            LOG.info("Solicitud a la API realizada, cuerpo de la solicitud: {}. Código de respuesta obtenido: {}, Cuerpo de respuesta {}",
                    entity.getBody().toString(), responseEntity.getStatusCode().toString(), responseEntity.getBody().toString());
        }catch (HttpClientErrorException | HttpServerErrorException httpClientOrServerExc) { // captura errores 5xx o 4xx
            if (httpClientOrServerExc.getStatusCode().is5xxServerError()){
                LOG.error("Error {} en el servidor: {}", httpClientOrServerExc.getStatusCode(),
                        httpClientOrServerExc.getResponseBodyAsString(), httpClientOrServerExc);
            }
            if (httpClientOrServerExc.getStatusCode().is4xxClientError()){

                if (httpClientOrServerExc.getStatusCode().toString().contains("404")) {
                    LOG.error("{} {} Error {}, se esta tratando una tienda inexistente: {}", httpClientOrServerExc.getStatusCode(),
                            httpClientOrServerExc.getResponseBodyAsString(), httpClientOrServerExc);
                } else if (httpClientOrServerExc.getStatusCode().toString().contains("401")) {
                    LOG.error("{} {} Error de autenticacion {}: {}", httpClientOrServerExc.getStatusCode(),
                            httpClientOrServerExc.getResponseBodyAsString(), httpClientOrServerExc);
                }

            }
            responseBody = responseEntity.getBody().toString();
            return responseBody;
        } // end catch

        responseBody = responseEntity.getBody().toString();
        return responseBody;
    } // end bulk Update API poster
}
