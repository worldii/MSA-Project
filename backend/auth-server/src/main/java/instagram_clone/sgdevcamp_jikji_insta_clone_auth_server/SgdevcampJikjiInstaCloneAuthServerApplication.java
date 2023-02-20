package instagram_clone.sgdevcamp_jikji_insta_clone_auth_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SgdevcampJikjiInstaCloneAuthServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SgdevcampJikjiInstaCloneAuthServerApplication.class, args);
	}

}
