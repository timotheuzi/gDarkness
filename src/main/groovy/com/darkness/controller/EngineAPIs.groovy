package com.darkness.controller

import org.json.JSONException
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import com.darkness.db.CacheRepo
import com.darkness.db.MapRepo
import com.darkness.db.UserDB
import com.darkness.db.UserRepo
import com.darkness.db.NpcRepo
import com.darkness.utils.DarknessUtils
import org.springframework.beans.factory.annotation.Autowired

@RestController
 class EngineAPIs {

	/*@Value("${hello}")
	String temp = this.toString()*/

	@Autowired
	UserRepo uRepo
	@Autowired
	MapRepo mRepo
	@Autowired
	NpcRepo nRepo
	@Autowired
	DarknessUtils darknessUtils
	@Autowired
	UserRepo repository

	@RequestMapping(method = RequestMethod.GET, path = "/createNewUser", produces = MediaType.TEXT_HTML_VALUE)
	createNewUser(@RequestParam(name = "name", required = true) String name) {

		if (darknessUtils.createNewUser(name)) {
			return "New User Created name " + name + " created...."
		} else {
			return "User already exists, logging in using " + name + " and redirect to home"
		}
	}

	@RequestMapping(method = RequestMethod.GET, path = "/setUser", produces = MediaType.APPLICATION_JSON_VALUE)
	setUser(@RequestParam(name = "name", required = true) String name,
			@RequestParam(name = "lvl", required = false) Integer lvl,
			@RequestParam(name = "money", required = false) Integer money,
			@RequestParam(name = "exp", required = false) Integer exp,
			@RequestParam(name = "attack", required = false) Integer attack,
			@RequestParam(name = "defense", required = false) Integer defense,
			@RequestParam(name = "description", required = false) String description,
			@RequestParam(name = "location", required = false) Integer location,
			@RequestParam(name = "hp", required = false) Integer hp) {
		repository.findByUserName(name)
		int id = repository.findByUserName(name).id
		UserDB newEntry = new UserDB()
		newEntry.id = id
		newEntry.userName(name)
		if (lvl != null) {
			newEntry.userLvl(lvl)
		} else {
			newEntry.userLvl(repository.findByUserName(name))
		}
		if (money != null) {
			newEntry.userMoney(money)
		} else {
			newEntry.userMoney(repository.findByUserName(name).userMoney)
		}
		if (exp != null) {
			newEntry.userExp(exp)
		} else {
			newEntry.userExp(repository.findByUserName(name).userExp)
		}
		if (attack != null) {
			newEntry.userAttack(attack)
		} else {
			newEntry.userAttack(repository.findByUserName(name).userAttack)
		}
		if (defense != null) {
			newEntry.userDefense(defense)
		} else {
			newEntry.userDefense(repository.findByUserName(name).userDefense)
		}
		if (description != null) {
			newEntry.userDescription(description)
		} else {
			newEntry.userDescription(repository.findByUserName(name).userDescription)
		}
		if (location != null) {
			newEntry.userLocation(location)
		} else {
			newEntry.userLocation(repository.findByUserName(name).userLocation)
		}
		if (hp != null) {
			newEntry.userHp(hp)
		} else {
			newEntry.userHp(repository.findByUserName(name).userHp)
		}
		repository.save(newEntry)
		return newEntry.toString()
	}

	@RequestMapping(method = RequestMethod.GET, path = "/getFullInformation", produces = MediaType.APPLICATION_JSON_VALUE)
	Map getFullInformation(@RequestParam(name = "name", required = true) String name,
						   @RequestParam(name = "user", required = false, defaultValue = "user") String user) {
		Map<String, Integer> info = new HashMap<String, Integer>()
		Boolean userNpc = true
		if (user.contains("npc")) {
			userNpc = false
		}
		info = darknessUtils.getStats(name, userNpc)
		return info
	}

	 /*@GetMapping("/CountUsers")  Integer findAll(){
	 String result = "" Integer count = 0 for(userDB userDB :
	 repository.findAll()) { count++ result += userDB.getId() + "</br>" }
	 //Response response = new Response("Done", 1) return count // + "response"
	  + response }

	/*@GetMapping("/CountMaps")
	Integer CountMaps() {
		String result = ""
		// mapRepo = new mapRepo
		// List<mapRepo> = mapRepo
		Integer count = 0
		for (MapDB mapDB : mapRepo.findAll()) {
			count++
		}
		return count
	}*/

	@SuppressWarnings("static-access")
	@RequestMapping(method = RequestMethod.GET, path = "/initializeMap", produces = MediaType.TEXT_HTML_VALUE)
	String initializeMap() {
		// Response response =
		darknessUtils.initializeMapValues()
		return "Success"
	}

	@RequestMapping(method = RequestMethod.GET, path = "/initializeNpc", produces = MediaType.TEXT_HTML_VALUE)
	String initializeNpc() {
		darknessUtils.initializeNpcValues()
		return "Success"
	}

	@RequestMapping(method = RequestMethod.GET, path = "/initializeItem", produces = MediaType.TEXT_HTML_VALUE)
	String initializeItem() {
		darknessUtils.initializeItemValues()
		return "Success"
	}

	@RequestMapping(method = RequestMethod.GET, path = "/various", produces = MediaType.APPLICATION_JSON_VALUE) // consumes
	Map various(String value, String name) throws JSONException {
		// Map<String, String> map = requestForm.get(arg0)
		// String loca = requestForms.get("location").toString()
		// String value = requestForms.get("value").toString()
		// String name = requestForms.get("name").toString()
		// Integer location = Integer.parseInt(loca)
		if (!value.isEmpty()) {
			value = value.replaceAll(",", "")
			darknessUtils.updateCache(location, value)
			return value
		}
		Map<String, String> output = new HashMap<String, String>()
		// output.put("msg", value)
		// output.put("location", location.toString())
		// cacheRepos.save(output)
		if (value.toLowerCase().contains("move")) {
			darknessUtils.move(name)
			// output.put("users", darknessUtils.ShowUsersInLocation(location).toString())
			// output.put("npcs", darknessUtils.ShowNpcsInLocation(location).toString())
			// output.put("description", mapRepo.findById(location).get().getDescription())

			// Model model = null
			// tempController.template_1(name, model)
			return output
		} else if (value.toLowerCase().contains("inv")
				&& value.toLowerCase().contains(darknessUtils.ShowNpcsInLocation(location).toString())) {
			Iterator it = darknessUtils.ShowNpcsInLocation(location).entrySet().iterator()
			while (it.hasNext()) {
				Map.Entry pair = (Map.Entry) it.next()
				if (value.contains(pair.getValue().toString())) {
					return darknessUtils.getStats(pair.getValue().toString(), false)
				}
				it.remove() // avoids a ConcurrentModificationException
			}
		} else if (value.toLowerCase().contains("inv")
				&& value.toLowerCase().contains(darknessUtils.ShowUsersInLocation(location).toString())) {
			Iterator it = darknessUtils.ShowUsersInLocation(location).entrySet().iterator()
			while (it.hasNext()) {
				Map.Entry pair = (Map.Entry) it.next()
				if (value.contains(pair.getValue().toString())) {
					return darknessUtils.getStats(pair.getValue().toString(), true)
				}
				it.remove() // avoids a ConcurrentModificationException
			}
		} else if (value.toLowerCase().contains("inv")) {
			return darknessUtils.getStats(name, true)
		} else {
			output.put("msg", "No implementation for that string yet")
			return output
		}
		// Map error = (error, "error happened")
		return output
	}

	// @GetMapping("/updateRoom")
	@RequestMapping(method = RequestMethod.GET, path = "/updateRoom", produces = MediaType.APPLICATION_JSON_VALUE)
	Map updateRoom(@RequestParam(name = "mapIndex", required = false) Integer mapIndex) {
		if (mapIndex != null) {
			return darknessUtils.mapStatus(mapIndex)
		} else {

			return darknessUtils.mapStatus(mapIndex)
		}
	}

	// @GetMapping("/findUserName")
	@RequestMapping(method = RequestMethod.GET, path = "/findUserByIndex", produces = MediaType.APPLICATION_JSON_VALUE)
	String findUserByIndex(@RequestParam(name = "index", required = true) Integer index) {
		// int[] updateRoom = darknessUtils.mapStatus(location)
		return darknessUtils.getUserByIndex(index)
	}

	// @GetMapping("/findNpcName")
	@RequestMapping(method = RequestMethod.GET, path = "/findNpcByIndex", produces = MediaType.APPLICATION_JSON_VALUE)
	String findNpcByIndex(@RequestParam(name = "index", required = true) Integer index) {
		// int[] updateRoom = darknessUtils.mapStatus(location)
		return darknessUtils.getNpcByIndex(index)
	}
	// @GetMapping("/findNpcName")
	/*
	 * @RequestMapping(method = RequestMethod.GET, path = "/saveCookie", produces =
	 * MediaType.APPLICATION_JSON_VALUE)  String
	 * saveCookie(@RequestParam(name="user", required=true) String cookie)
	 *
	 * {
	 *
	 * //java.util.Map<String, String> values = (java.util.Map<String, String>)
	 * form return cookie }
	 */
}