package dushkof.seaWars.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dushkof.seaWars.objects.CurrencyResponse;
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
import java.io.IOException;
import java.net.URI;
import java.util.Map;

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

    @GetMapping("/rest")
    public String rest() {
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.getMessageConverters().add(new Jaxb2RootElementHttpMessageConverter());
        final String publicURI = "http://www.cbr.ru/scripts/XML_daily.asp";
        URI uri = getURI(publicURI);
        System.setProperty("sun.net.http.allowRestrictedHeaders", "true");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/xml");
        headers.set("Host", "www.cbr.ru");
//        headers.add("charset", "UTF-8");
        //            String jsonObj = objectMapper.writeValueAsString(request);
        try {
            ResponseEntity<CurrencyResponse> personEntity = restTemplate.getForEntity(publicURI, CurrencyResponse.class);
            personEntity.getBody();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return "hui";
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
                // return it as a String
                String result = EntityUtils.toString(entity);
                return result;
            }

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "zalupa";
    }

    private URI getURI(String host) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(host);
        return builder.build().encode().toUri();
    }


}
