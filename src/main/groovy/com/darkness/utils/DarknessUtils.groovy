package com.darkness.utils

import com.darkness.controller.TemplateController
import java.io.StringWriter
import java.util.HashMap
import java.util.HashSet
import java.util.Iterator
import java.util.Map
import java.util.Random
import java.util.Set
import com.darkness.db.UserRepo
import com.darkness.db.ItemsDB
import com.darkness.db.ItemsRepo
import com.darkness.db.MapDB
import com.darkness.db.MapRepo
import com.darkness.db.NpcDB
import com.darkness.db.NpcRepo
import com.darkness.db.UserDB
import com.darkness.db.CacheRepo
import com.darkness.db.CacheDB
import org.json.JSONException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.ui.Model

 class DarknessUtils {

	@Autowired
	UserRepo userRepos

	@Autowired
	MapRepo mapRepos

	@Autowired
	ItemsRepo itemsRepos

	@Autowired
	NpcRepo npcRepos

	@Autowired
	CacheRepo cacheRepos

	@Autowired
	TemplateController templateController

	//  Integer mapCount = 0
	// private Integer npcCount = 0
	// private Integer itemCount = 0
	// private Integer items, npcs, users, attack, defense
	// private String name, description

	private String map_0 = "An empty bar"
	private String map_1 = "A dark street corner"
	private String map_2 = "A dark alley"

	private String npc_0 = "Burly Bartender"
	private String npc_1 = "Vagrant"

	private String item_1 = "sterling silver"

	 void initializeMapValues() {
		Integer mapCount = CountMaps()
		MapDB mapDB = new MapDB()
		mapDB("map_" + (CountMaps() + 1))
	if (CountMaps() == 0) {
			mapDB.mapDescription(map_0)
		} else if ((CountMaps() & 1) == 0) {
			mapDB.mapDescription(map_1)
		} else {
			mapDB.mapDescription(map_2)
		}
		mapDB.mapItems(0)
		mapDB.mapNpcs(0)
		mapDB.mapUsers(0)
		mapRepos.save(mapDB)
		// mapCount++
	}

	 void initializeItemValues() {
		Integer itemCount = 0
		Double attack = Math.random() * ((10 - 1) + 1)
		Double defense = Math.random() * ((5 - 1) + 1)
		ItemsDB itemsDB = new ItemsDB()
		itemsDB.itemName("gun	_" + itemCount.toString())
		itemsDB.itemDescription(item_1)
		itemsDB.itemAttack(attack.intValue())
		itemsDB.itemDefense(defense.intValue())
		// itemsDB.location(0)
		itemsRepos.save(itemsDB)
		itemCount++
	}

	 void initializeNpcValues() {
		Double attack = Math.random() * ((50 - 1) + 1)
		Double defense = Math.random() * ((10 - 1) + 1)
		Double hp = Math.random() * ((1000 - 1) + 1)
		NpcDB npcDB = new NpcDB()
		if (CountNpcs() == 0) {
			npcDB.npcName("Frank")
			npcDB.npcDescription(npc_0)
			npcDB.npcLocation(1)
			npcDB.npcAttack(75)
			npcDB.npcDefense(75)
			npcDB.npcHp(3000)
		} else {
			npcDB.npcName(getMeAgoodName())
			npcDB.npcDescription(npc_1)
			npcDB.npcLocation(2)
			npcDB.npcAttack(attack.intValue())
			npcDB.npcDefense(defense.intValue())
			npcDB.npcHp(hp.intValue())
		}

		npcRepos.save(npcDB)
		// npcCount++
	}

	 Boolean createNewUser(String name) {
		try {
			UserDB result = userRepos.findByUserName(name)
			result.userName()
			result.userLvl()
			result.userMoney()
			result.userExp()
			result.userAttack()
			result.userDefense()
			result.userDescription()
			result.userLocation()
			result.userHp()
			String test = result.toString()
			System.out.println("record does exist:" + test)
			return false
		} catch (Exception e) {
			System.out.println("record doesnt exist, creating it")
		}
		UserDB newEntry = new UserDB()
		// newEntry.setId(id)
		newEntry.userName(name)
		newEntry.userLvl(1)
		newEntry.userMoney(1)
		newEntry.userExp(1)
		newEntry.userAttack(1)
		newEntry.userDefense(1)
		newEntry.userDescription("A weak vagrant with no weapon")
		newEntry.userLocation(1)
		newEntry.userHp(1000)
		userRepos.save(newEntry)
		return true
	}

	 HashMap<String, Integer> getStats(String name, Boolean user) {
		HashMap<String, Integer> stats = new HashMap<String, Integer>()
		if (user) {
			stats.put("ID", userRepos.findByUserName(name).id)
			stats.put("attack", userRepos.findByUserName(name).userAttack)
			stats.put("defense", userRepos.findByUserName(name).userDefense)
			stats.put("exp", userRepos.findByUserName(name).userExp)
			stats.put("location", userRepos.findByUserName(name).userLocation)
			stats.put("lvl", userRepos.findByUserName(name).userLvl)
			stats.put("money", userRepos.findByUserName(name).userMoney)
		} else {
			stats.put("ID", npcRepos.findByNpcName(name).id)
			stats.put("attack", npcRepos.findByNpcName(name).npcAttack)
			stats.put("defense", npcRepos.findByNpcName(name).npcDefense)
			stats.put("hp", npcRepos.findByNpcName(name).npcHp)
			stats.put("location", npcRepos.findByNpcName(name).npcLocation)
			// stats.put("name", npcRepos.findByName(name).)
		}
		return stats
	}

	// counters
	 Integer CountMaps() {
		// String result = ""
		Integer count = 0
		for (MapDB mapDB : mapRepos.findAll()) {
			count++
		}
		return count
	}

	 Integer CountUsers() {
		Integer count = 0
		for (UserDB userdb : userRepos.findAll()) {
			count++
		}
		return count // + "response" + response
	}

	 Integer CountItems() {
		Integer count = 0
		for (ItemsDB itemdb : itemsRepos.findAll()) {
			count++
		}
		return count
	}

	 Integer CountNpcs()
	{
		Integer count = 0
		for(NpcDB itemdb : npcRepos.findAll())
		{
				count++
		}
		return count
	}

	 String CountNpcsByLocation(Integer location) {
		StringWriter npcs = new StringWriter()
		Integer count = 0
		for (NpcDB npcDB : npcRepos.findAll()) {
			if (npcDB.getLocation() == location) {
				npcs.write(npcDB.getName() + ",")
				count++
			}
		}
		return npcs.toString()
	}

	 String CountUsersByLocation(Integer location) {
		StringWriter users = new StringWriter()
		Integer count = 0
		for (UserDB userDB : userRepos.findAll()) {
			if (userDB.getLocation() == location) {
				users.write(userDB.getName() + ",")
				count++
			}
		}
		return users.toString()
	}

	 HashMap<Integer, String> ShowUsersInLocation(Integer index) {
		HashMap<Integer, String> users = new HashMap<Integer, String>()
		for (UserDB userDB : userRepos.findAll()) {
			if (userDB.getLocation() == index) {
				users.put(userDB.getId(), userDB.getName())
			}
		}
		return users // + "response" + response
	}

	 HashMap<Integer, String> ShowNpcsInLocation(Integer index) {
		HashMap<Integer, String> npcs = new HashMap<Integer, String>()
		for (NpcDB npcDB : npcRepos.findAll()) {
			if (npcDB.getLocation() == index) {
				npcs.put(npcDB.getId(), npcDB.getName())
			}
		}
		return npcs // + "response" + response

	}

	 Integer move(String name) {
		if (CountMaps() < 11) {
			initializeMapValues()
		}
		Double location = Math.random() * ((CountMaps()))
		// random NPC generation and movement
		Double npcToMove = Math.random() * ((CountNpcs()))
		int temp = npcToMove.intValue()
		npcRepos.findById(temp).get().setLocation(location.intValue())
		userRepos.findByName(name).setLocation(location.intValue())
		// Model model = null
		// templateController.template_1(name, model)
		return location.intValue()
	}

	 Map mapStatus(Integer mapIndex) {
		HashMap mapObj = new HashMap()
		int count = 0
		Iterator itUser = (ShowUsersInLocation(mapIndex)).entrySet().iterator()
		while (itUser.hasNext()) {
			Map.Entry pair = (Map.Entry) itUser.next()
			mapObj.put(count, pair.getValue())
			itUser.remove() // avoids a ConcurrentModificationException
			count++
		}
		Iterator itNpc = (ShowNpcsInLocation(mapIndex)).entrySet().iterator()
		while (itNpc.hasNext()) {
			Map.Entry pair = (Map.Entry) itNpc.next()
			mapObj.put(count, pair.getValue())
			itNpc.remove() // avoids a ConcurrentModificationException
			count++
		}
		for (CacheDB cacheDB : cacheRepos.findAll()) {
			mapObj.put(count, cacheDB.getCurrentStatis())
			count++
		}

		return mapObj
	}

	//// get individual user or npc
	 String getUserByName(String name) {
		return userRepos.findByName(name).getName()
	}

	 String getNpcByName(String name) {
		return npcRepos.findByName(name).getName()
	}

	 String getUserByIndex(Integer index) {
		return userRepos.findById(index).get().getName()
	}

	 String getNpcByIndex(Integer index) {
		return npcRepos.findById(index).get().getName()
	}

	 String getStatus(String where) {
		return where
	}

	 Map findUserStatsByName(String name) throws JSONException {
		HashMap userObj = new HashMap()

		try {
			userObj.put("name", userRepos.findByName(name).getName())
			userObj.put("attack", userRepos.findByName(name).getAttack())
			userObj.put("defense", userRepos.findByName(name).getDefense())
			userObj.put("description", userRepos.findByName(name).getDescription())
			userObj.put("exp", userRepos.findByName(name).getExp())
			userObj.put("hp", userRepos.findByName(name).getHp())
			userObj.put("location", userRepos.findByName(name).getLocation())
			userObj.put("lvl", userRepos.findByName(name).getLvl())
			userObj.put("money", userRepos.findByName(name).getMoney())
			userObj.put("name", userRepos.findByName(name).getName())
		} catch (Exception e) {
			e.printStackTrace()
			return (HashMap) userObj.put("error", e.toString())
		}
		return userObj
	}

	static String getMeAgoodName() {
		Random rand = new Random()
		String vocals = "aeiou" + "ioaeu" + "ouaei"
		String cons = "bcdfghjklznpqrst" + "bcdfgjklmnprstvw" + "bcdfgjklmnprst"
		// String allchars = vocals + cons
		int length = rand.nextInt(8)
		if (length < 3)
			length = 3
		// int consnum = 1
		int consnum = 1
		String name = ""
		String touse
		char c

		for (int i = 0; i < length; i++) {
			if (consnum == 2) {
				touse = vocals
				if (length != 2) {
					consnum = rand.nextInt(2)
				} else {
					consnum = 1
				}
			} else
				touse = cons
			// pick a random character from the set we are goin to use.
			c = touse.charAt(rand.nextInt(touse.length()))
			name = name + c
			if (cons.indexOf(c) != -1)
				consnum++
			if (vocals.indexOf(c) != -1)
				consnum = consnum - 1
		}
		name = name.charAt(0) + name.substring(1, name.length())
		System.out.println(name)
		return name

	}

	void updateCache(Integer location, String msg) {
		CacheDB newCacheEntry = new CacheDB()
		newCacheEntry.setCurrentStatus(msg)
		newCacheEntry.setMapName("map_" + location)
		cacheRepos.save(newCacheEntry)
		// return Methods.getNpcByIndex(index)
	}
}
