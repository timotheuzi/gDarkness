package com.darkness.controller

import com.darkness.db.CacheDB
import com.darkness.db.MapDB
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
	MapRepo mRepo
	@Autowired
	NpcRepo nRepo
	@Autowired
	DarknessUtils darknessUtils
	@Autowired
	UserRepo uRepo
	@Autowired
	CacheRepo cRepo

	@SuppressWarnings("static-access")
	@RequestMapping(method = RequestMethod.GET, path = "/initializeMap", produces = MediaType.APPLICATION_JSON_VALUE)
	String initializeMap() {
		// Response response =
		darknessUtils.initializeMapValues()
		return "initialized maps"
	}

	@RequestMapping(method = RequestMethod.GET, path = "/initializeNpc", produces = MediaType.APPLICATION_JSON_VALUE)
	String initializeNpc() {
		darknessUtils.initializeNpcValues()
		return "initialized npc"
	}

	@RequestMapping(method = RequestMethod.GET, path = "/initializeItem", produces = MediaType.APPLICATION_JSON_VALUE)
	String initializeItem() {
		darknessUtils.initializeItemValues()
		return "initialized items"
	}

	@RequestMapping(method = RequestMethod.POST, path = "/getFullInformation", produces = MediaType.APPLICATION_JSON_VALUE)
	Map getFullInformation(@RequestParam(name = "name", required = true) String name,
						   @RequestParam(name = "user", required = false, defaultValue = "user") String user) {
		Boolean isNpc = false
		if (user.contains("npc")) {
			isNpc = true
		}
		Map<String, Integer> info = darknessUtils.getStats(name, isNpc)
		return info
	}

	@RequestMapping(method = RequestMethod.POST, path = "/createNewUser", produces = MediaType.APPLICATION_JSON_VALUE)
	String createNewUser(@RequestParam(name = "name", required = true) String name) {

		if (darknessUtils.createNewUser(name)) {
			return "New User Created name " + name + " created...."
		} else {
			return "User already exists, logging in using " + name + " and redirect to home"
		}
	}

	@RequestMapping(method = RequestMethod.POST, path = "/setUser", produces = MediaType.APPLICATION_JSON_VALUE)
	String setUser(@RequestParam(name = "name", required = true) String name,
			@RequestParam(name = "lvl", required = false) Integer lvl,
			@RequestParam(name = "money", required = false) Integer money,
			@RequestParam(name = "exp", required = false) Integer exp,
			@RequestParam(name = "attack", required = false) Integer attack,
			@RequestParam(name = "defense", required = false) Integer defense,
			@RequestParam(name = "description", required = false) String description,
			@RequestParam(name = "location", required = false) Integer location,
			@RequestParam(name = "hp", required = false) Integer hp) {
		Integer id = uRepo.findByName(name).id
		if(id == null){
			darknessUtils.createNewUser(name)
			return "New User Created name " + name + " created...."
		}
		UserDB newEntry = new UserDB()
		newEntry.id = id
		newEntry.name(name)
		if (lvl != null) {
			newEntry.lvl(lvl)
		} else {
			newEntry.lvl(uRepo.findByName(name).lvl)
		}
		if (money != null) {
			newEntry.money(money)
		} else {
			newEntry.money(uRepo.findByName(name).money)
		}
		if (exp != null) {
			newEntry.exp(exp)
		} else {
			newEntry.exp(uRepo.findByName(name).exp)
		}
		if (attack != null) {
			newEntry.attack(attack)
		} else {
			newEntry.attack(uRepo.findByName(name).attack)
		}
		if (defense != null) {
			newEntry.defense(defense)
		} else {
			newEntry.defense(uRepo.findByName(name).defense)
		}
		if (description != null) {
			newEntry.description(description)
		} else {
			newEntry.description(uRepo.findByName(name).description)
		}
		if (location != null) {
			newEntry.location(location)
		} else {
			newEntry.location(uRepo.findByName(name).location)
		}
		if (hp != null) {
			newEntry.hp(hp)
		} else {
			newEntry.hp(uRepo.findByName(name).hp)
		}
		uRepo.save(newEntry)
		return newEntry.toString()
	}


	@RequestMapping(method = RequestMethod.POST, path = "/various", produces = MediaType.APPLICATION_JSON_VALUE) // consumes
	Map various(String value, String name) throws JSONException {
		//set up
		Integer location = uRepo.findByName(name).id
		Integer userId = uRepo.findByName(name).id
		value = value.replace(",", "").toLowerCase()
		Map<String, String> output = new HashMap<String, String>()
		//update rolling cache
		if (!value.isEmpty()) {
			darknessUtils.updateCache(value, location, name)
		}
		//movement
		if (value.contains("move") || value.contains("go")) {
			Integer newLocation = darknessUtils.move(name, location)
			output.put("users", darknessUtils.ShowUsersInLocation(newLocation).toString())
			output.put("npcs", darknessUtils.ShowNpcsInLocation(newLocation).toString())
			output.put("description", mRepo.findById(newLocation).toString())
			CacheDB cache = new CacheDB()
			cache.usersInRoom.add(userId)
			cache.msg(value)
			cache.mapId(newLocation)
			cRepo.save(cache)
			return output
		}
		//look or inventory
		if ((value.contains("inv") || value.contains("look"))  && value.contains(darknessUtils.ShowNpcsInLocation(location).toString())) {
			Iterator it = darknessUtils.ShowNpcsInLocation(location).iterator()
			while (it.hasNext()) {
				Map.Entry pair = (Map.Entry) it.next()
				if (value(pair.getValue().toString())) {
					return darknessUtils.getStats(pair.getValue().toString(), false)
				}
				it.remove() // avoids a ConcurrentModificationException
			}
		}
		if ((value("inv") || value.contains("look") && value.contains(darknessUtils.ShowUsersInLocation(location)))
				&& value(darknessUtils.ShowUsersInLocation(location).toString())) {
			Iterator it = darknessUtils.ShowUsersInLocation(location).entrySet().iterator()
			while (it.hasNext()) {
				Map.Entry pair = (Map.Entry) it.next()
				if (value(pair.getValue().toString())) {
					return darknessUtils.getStats(pair.getValue().toString(), true)
				}
				it.remove() // avoids a ConcurrentModificationException
			}
		}
		if (value("inv")) {
			return darknessUtils.getStats(name, true)
		}
		{
			output.put("msg", "No implementation for that string yet")
			return output
		}
		// Map error = (error, "error happened")
		return output
	}

	// @GetMapping("/updateRoom")
	/*@RequestMapping(method = RequestMethod.POST, path = "/updateRoom", produces = MediaType.APPLICATION_JSON_VALUE)
	Map updateRoom(@RequestParam(name = "mapIndex", required = false) Integer mapIndex) {
		if (mapIndex != null) {
			return darknessUtils.mapStatus(mapIndex)
		} else {
			return darknessUtils.mapStatus(0)
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
	}*/

}