package dushkof.seaWars.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dushkof.seaWars.objects.ValCurs;
import dushkof.seaWars.services.GameService;
import dushkof.seaWars.services.UserService;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.Resource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.net.MalformedURLException;
import java.net.URL;

@RestController
@RequestMapping("hello")
public class HelloController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    @Resource(name = "userService")
    private UserService userService;

    @Resource(name = "gameService")
    private GameService gameService;

    private ObjectMapper objectMapper = new ObjectMapper();

    private final CloseableHttpClient httpClient = HttpClients.createDefault();


    @GetMapping
    public String list() {
        LOGGER.error("test error");
        LOGGER.info("test info");
        LOGGER.debug("test debug");
        LOGGER.warn("test warn");
        LOGGER.trace("test trace");
        return userService.sayHi();
    }

    @GetMapping("/init")
    public String init() {
        return gameService.init();
    }


    @GetMapping("/t")
    public String t() {
        String publicURI = "http://www.cbr.ru/scripts/XML_daily.asp";
        try {
            URL url = new URL(publicURI);
            JAXBContext jaxbContext = JAXBContext.newInstance(ValCurs.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            ValCurs resp = (ValCurs) jaxbUnmarshaller.unmarshal(url);
            System.out.println(resp.toString());
            return "см консоль";
        } catch (MalformedURLException malformedURLException) {
            malformedURLException.printStackTrace();
        } catch (JAXBException jaxbException) {
            jaxbException.printStackTrace();
        }
        return "zalupa";
    }

}
