
package com.example.demo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class BankController {

	HashMap<String,BankData> map=new HashMap<String,BankData>();
	
	@RequestMapping(value="/")
	public String index() throws FileNotFoundException{
		Scanner scanner = new Scanner(new FileReader("C:\\Users\\Jay K Patel\\Desktop\\PayGlocal\\PayGlocal-Assignment\\src\\main\\resources\\bin.csv"));
		while (scanner.hasNext()) {
			
			String inputs[] = scanner.nextLine().split(";",-1);
				
			String binRange = inputs[0];
			String cardBrand=inputs[1];
			String cardIssuer=inputs[2];
			String cardType=inputs[3];
			String cardSubtype=inputs[4];
			String country=inputs[5];
			String countryCode=inputs[6];
			String iso=inputs[7];
			String currencyCode=inputs[8];
			String url=inputs[9];
			String contact=inputs[10];
			
			BankData data = new BankData();
			data.setBinRange(binRange);
			data.setCardBrand(cardBrand);
			data.setCardIssuer(cardIssuer);
			data.setCardType(cardType);
			data.setCardSubtype(cardSubtype);
			data.setCountry(country);
			data.setCountryCode(countryCode);
			data.setIso(iso);
			data.setCurrencyCode(currencyCode);
			data.setUrl(url);
			data.setContact(contact);
											
			map.put(binRange,data);
		}
		return "index";
	}
	
	@ResponseBody
	@RequestMapping("/{binNo}")
	public ResponseEntity<BankData> getData(@PathVariable String binNo){
		
		
		if(!map.containsKey(binNo)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		BankData ans=map.get(binNo);
		return new ResponseEntity<BankData>(ans,HttpStatus.OK);
		
	}
}