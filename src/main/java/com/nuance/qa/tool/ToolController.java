package com.nuance.qa.tool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.mail.MessagingException;

import java.util.ArrayList;

@RestController
public class ToolController {
	
	@Autowired
	private QatoolEmailServiceImpl emailService;
	
	@Autowired
	private VminfoRepository vminfoRepository;

    @GetMapping("/vmip")
    public String getVmip(@RequestParam(value = "userid", defaultValue = "huajianloo") String userid) throws MessagingException {
    	emailService.sendSimpleEmail();
        return vminfoRepository.findOne(userid).getVmip();
    }
    
    @GetMapping("/hotfixlist")
    public List<String> getHotfixlist() {
    	List<String> hotFixes = new ArrayList<String>();
    	hotFixes.add("EQ56-HF-291002-DWS");
    	hotFixes.add("EQ56-HF-291002-SysMgr");
    	return hotFixes;
    }
    
    @PostMapping("/setvmip")
    /**
     * with @RequestBody, Spring will bind the incoming HTTP request body to that parameter (unmarshal). 
     * @param vminfo
     * @return
     */
    public String setVmip(@RequestBody Vminfo vminfo) {
    	System.out.println(vminfo.getUserid() + " : " + vminfo.getVmip());
    	try {
    		Vminfo a = vminfoRepository.save(vminfo);
    		System.out.println("name = " + a.getUserid() + ", " + a.getVmip());
    	} catch(Exception ex) {
    		System.out.println(ex.getMessage());
    	}
    	return "set vm ip ok";
    }
}
