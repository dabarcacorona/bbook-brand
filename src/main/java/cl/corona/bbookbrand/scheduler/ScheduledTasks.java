package cl.corona.bbookbrand.scheduler;

import cl.corona.bbookbrand.model.BbookEnviaBrand;
import cl.corona.bbookbrand.repository.BbookEnviaBrandRepository;
import cl.corona.bbookbrand.services.BrandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class ScheduledTasks {

    private static final Logger LOG = LoggerFactory.getLogger(ScheduledTasks.class);
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Autowired
    private BrandService brandService;

    private BbookEnviaBrandRepository bbookenviobrandrepository;


    @Autowired
    public ScheduledTasks(BbookEnviaBrandRepository bbookenviobrandrepository) {
        this.bbookenviobrandrepository = bbookenviobrandrepository;
    }

    @Scheduled(cron = "${cron.expression}")
    public void scheduledBbook() throws InterruptedException {
        LOG.info("{} : Generacion periodica para el envio de Marcas  - {}",
                dateTimeFormatter.format(LocalDateTime.now()));

        // Date exceptions
        LocalDate today = LocalDate.now();
        int count = 0;
        List<BbookEnviaBrand> Bbookbrand = new ArrayList<>();

        try {
            String statusBrand = "T";
            Bbookbrand = (List<BbookEnviaBrand>) bbookenviobrandrepository.findAllByTranType("A");

        } catch (InvalidDataAccessResourceUsageException e) {
            LOG.error("{}: Ocurrio un error al momento de rescatar las Marcas: ", e );
            return;
        }

        LOG.info("Cantidad de Marcas para generar: {}", Bbookbrand.size());

        if (Bbookbrand.size() > 0) {
            brandService.EnvioBrand(Bbookbrand, "A");
        }

        try {
            String statusBrand = "T";
            Bbookbrand = (List<BbookEnviaBrand>) bbookenviobrandrepository.findAllByTranType("C");

        } catch (InvalidDataAccessResourceUsageException e) {
            LOG.error("{}: Ocurrio un error al momento de rescatar las Marcas: ", e );
            return;
        }

        LOG.info("Cantidad de Marcas para generar: {}", Bbookbrand.size());

        if (Bbookbrand.size() > 0) {
            brandService.EnvioBrand(Bbookbrand, "C");
        }

        try {
            String statusBrand = "T";
            Bbookbrand = (List<BbookEnviaBrand>) bbookenviobrandrepository.findAllByTranType("D");

        } catch (InvalidDataAccessResourceUsageException e) {
            LOG.error("{}: Ocurrio un error al momento de rescatar las Marcas: ", e );
            return;
        }

        LOG.info("Cantidad de Marcas para generar: {}", Bbookbrand.size());

        if (Bbookbrand.size() > 0) {
            brandService.EnvioBrand(Bbookbrand, "D");
        }

    }  // fin scheduledBulkUpdateWithFixedDelay()

}
