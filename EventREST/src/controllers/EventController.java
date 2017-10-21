package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import data.EventDAO;
import entities.CatchLog;

@RestController
public class EventController {
	
	@Autowired
	EventDAO dao;
	
	@RequestMapping(path="ping", method=RequestMethod.GET)
	public String ping(){
	  return "pong";
	}
	
	@RequestMapping(path = "events", method = RequestMethod.GET)
	public List<CatchLog> index() {
		return dao.index();
	}
	
	@RequestMapping(path = "events/{id}", method = RequestMethod.GET)
	public CatchLog show(@PathVariable int id) {
		return dao.show(id);
	}
	@RequestMapping(path = "events", method = RequestMethod.POST)
	public CatchLog create(@RequestBody String jsonAddress) {
		ObjectMapper mapper = new ObjectMapper();
		CatchLog log = null;
		try {
			  log = mapper.readValue(jsonAddress, CatchLog.class);
			} catch (Exception e) {
			  e.printStackTrace();
			}
			return dao.create(log);
	}
	@RequestMapping(path = "events/{id}", method = RequestMethod.PUT)
	public CatchLog update(@RequestBody String jsonAddress, @PathVariable int id) {
		ObjectMapper mapper = new ObjectMapper();
		CatchLog log = null;
		try {
			  log = mapper.readValue(jsonAddress, CatchLog.class);
			} catch (Exception e) {
			  e.printStackTrace();
			}
			return dao.update(id, log);
	}
	@RequestMapping(path = "events/{id}", method = RequestMethod.DELETE)
	public boolean destroy(@PathVariable int id) {
		return dao.destroy(id);
	}

}
