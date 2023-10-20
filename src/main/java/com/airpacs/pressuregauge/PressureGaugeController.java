package com.airpacs.pressuregauge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PressureGaugeController {

	@Autowired
	private DBConnection dbConnection;
	

	@GetMapping("/data")
	public String getAllData(Model model) {
		
		model.addAttribute("dataList", dbConnection.getAllData());
		return "dataList";
	}
	
	@GetMapping("/getdatafromserialport")
	public String getDatFromSerialPort(Model model) {
		if(ComPortConnection.comPort==null) {
			ComPortConnection.setupEventsForComPortIfAvailable();
			System.out.println("INITIALISINGGGGGGGGGGG");
			
		}
		System.out.println("INSIDEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
		model.addAttribute("dataList", dbConnection.getAllData());
		return "dataList";
	}

}
