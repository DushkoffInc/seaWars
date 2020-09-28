package dushkof.seaWars.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dushkof.seaWars.objects.ValCurs;
import dushkof.seaWars.services.GameService;
import dushkof.seaWars.services.UserService;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.Resource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
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
    public String t(){
        String publicURI = "http://www.cbr.ru/scripts/XML_daily.asp";
        HttpGet request = new HttpGet(publicURI);
        try (CloseableHttpResponse response = httpClient.execute(request)) {

            // Get HttpResponse Status
            System.out.println(response.getStatusLine().toString());

            HttpEntity entity = response.getEntity();
            Header headers = entity.getContentType();
            System.out.println(headers);

            if (entity != null) {
                String result = EntityUtils.toString(entity);
// тут начинается магия XD
                URL url = new URL(publicURI);
                JAXBContext jaxbContext = JAXBContext.newInstance(ValCurs.class);

                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                ValCurs resp = (ValCurs) jaxbUnmarshaller.unmarshal(url);
                System.out.println(resp.toString());
                // тут она заканчивается)

                return result;
            }

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return "zalupa";
    }

    private URI getURI(String host) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(host);
        return builder.build().encode().toUri();
    }


}
