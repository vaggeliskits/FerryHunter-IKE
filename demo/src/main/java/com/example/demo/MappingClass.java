package com.example.demo;
import org.geonames.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.geonames.PostalCode;
import org.geonames.PostalCodeSearchCriteria;



@RestController
public class MappingClass<code> {
// Add postalCodeSearchCriteria
    PostalCodeSearchCriteria postalCodeSearchCriteria = new PostalCodeSearchCriteria();

// Request to geonames postalCodeSearch?PostalCode
    @RequestMapping(value = "/postalCodeSearch" , produces = MediaType.APPLICATION_JSON_VALUE)
    List<String> getName(@RequestParam(value = "PostalCode", required = false) String Postalcode) throws InvalidParameterException {
// Create List for Result response
            List<PostalCode> queryResult = new ArrayList<>();
// Help list
            List<String> res = new ArrayList<>();
// Add username
            WebService.setUserName("ferryscanner");
// Set postalcode criteria
            postalCodeSearchCriteria.setPostalCode(Postalcode);
// Add try catch
            try {
                // query....
                queryResult =  WebService.postalCodeSearch(postalCodeSearchCriteria);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (GeoNamesException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

// create String with results
            for(int counter = 0;counter < queryResult.size(); counter++){
                System.out.println("\n" + queryResult.get(counter).getPlaceName() + "  " + queryResult.get(counter).getCountryCode()+ "\n");
                res.add("Name: " + queryResult.get(counter).getPlaceName() + ",  CountryCode: " + queryResult.get(counter).getCountryCode());
            }
            //returning the country code and name  
            return res;

    }

}


