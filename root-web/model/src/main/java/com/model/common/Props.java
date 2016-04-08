package com.model.common;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ImportResource;

import com.model.api.common.ApiRoute;
import com.model.webapp.common.Route;


/**
 * Loaded from the property files
 *
 * @author: Y Kamesh Rao
 * @created: 4/14/12 3:07 PM
 * @company: &copy; 2012, Kaleidosoft Labs
 */
@ImportResource("classpath:config/spring/applicationContext-properties.xml")
public class Props {
    public @Value("#{fProps['yourwebproject.host']}") String fHost;
    public @Value("#{fProps['yourwebproject.api.path']}") String fApiPath;
    public @Value("#{fProps['yourwebproject.web.path']}") String fWebPath;
    public @Value("#{fProps['yourwebproject.user.country']}") String fUserCountry;

    public @Value("#{mailProps['mail.fromAddress']}") String fromAddress;
    public @Value("#{mailProps['mail.sub.verificationEmail']}") String subVerificationEmail;
    public @Value("#{mailProps['mail.sub.confirmationEmail']}") String subConfirmationEmail;
    public @Value("#{mailProps['mail.verifyUrl']}") String verifyUrl;

    public List<String> webAuthRoutes;
    public List<String> apiAuthRoutes;


    @PostConstruct
    public void init() {
        webAuthRoutes = Arrays.asList(
                fWebPath + Route.dashboard
        );

        apiAuthRoutes = Arrays.asList(
                fApiPath + ApiRoute.userController + ApiRoute.uRegister,
                fApiPath + ApiRoute.userController + ApiRoute.uLogin
        );
    }
}
