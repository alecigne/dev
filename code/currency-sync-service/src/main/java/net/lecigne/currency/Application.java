package net.lecigne.currency;

import static net.lecigne.currency.config.Configuration.ROOT_CONFIG;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigBeanFactory;
import com.typesafe.config.ConfigFactory;
import net.lecigne.currency.business.EcbBusiness;
import net.lecigne.currency.config.Configuration;
import net.lecigne.currency.repository.EcbRepository;
import org.flywaydb.core.Flyway;

public class Application {

  public static void main(String[] args) throws Exception {
    Config config = ConfigFactory.load();
    Configuration configuration = ConfigBeanFactory.create(config.getConfig(ROOT_CONFIG), Configuration.class);
    Configuration.Data dataConfig = configuration.getData();
    Configuration.Business businessConfig = configuration.getBusiness();

    Flyway.configure()
        .dataSource(dataConfig.getDbUrl(), dataConfig.getDbUser(), dataConfig.getDbPassword())
        .load()
        .migrate();

    var repository = EcbRepository.create(dataConfig);
    var ecbBusiness = new EcbBusiness(businessConfig, repository);
    ecbBusiness.synchronize();
  }

}
