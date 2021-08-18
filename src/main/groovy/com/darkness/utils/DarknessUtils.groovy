package com.darkness.utils

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
	//@Autowired
	//TemplateController templateController
	//  Integer mapCount = 0
	// private Integer npcCount = 0
	// private Integer itemCount = 0
	// private Integer items, npcs, users, attack, defense
	// private String name, description
	private String map_0 = "An empty bar"
	private String map_1 = "A dark street corner"
	private String map_2 = "A dark alley"

	private String npc_0 = "Surly Bartender"
	private String npc_1 = "Vagrant"

	private String item_1 = "sterling silver"

	 void initializeMapValues() {
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
	}

	 void initializeItemValues() {
		Double attack = Math.random() * ((10 - 1) + 1)
		Double defense = Math.random() * ((5 - 1) + 1)
		ItemsDB itemsDB = new ItemsDB()
		itemsDB.itemName("luger_" + CountItems().toString())
		itemsDB.itemDescription(item_1)
		itemsDB.itemAttack(attack.intValue())
		itemsDB.itemDefense(defense.intValue())
        itemsDB.itemLocation(0)
		itemsRepos.save(itemsDB)
	}

	 void initializeNpcValues() {
		Double attack = Math.random() * ((50 - 1) + 1)
		Double defense = Math.random() * ((10 - 1) + 1)
		Double hp = Math.random() * ((1000 - 1) + 1)
		NpcDB npcDB = new NpcDB()
		if (CountNpcs() == 0) {
			npcDB.npcName("Frank")
			npcDB.npcDescription("Stinky ")
			npcDB.npcLocation(0)
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
	 }

	 Boolean createNewUser(String name) {
		 if(userRepos.findByUserName(name) != null)
		 {
			 try {
				 UserDB newEntry = new UserDB()
				 newEntry.userName(name)
				 newEntry.userLvl(1)
				 newEntry.userMoney(1)
				 newEntry.userExp(1)
				 newEntry.userAttack(1)
				 newEntry.userDefense(1)
				 newEntry.userDescription("A new user named " + name)
				 newEntry.userLocation(1)
				 newEntry.userHp(1000)
				 userRepos.save(newEntry)
				 return true
			 } catch (Exception e) {
				 System.out.println("errrror")
			 }
		 }
		 else {
			 return false
		 }

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
		}
		return stats
	}

	 Integer CountMaps() {
		Integer count = 0
		for (MapDB mapDB : mapRepos.findAll()) {
			count++
		}
		return count
	}

	 /*Integer CountUsers() {
		Integer count = 0
		for (UserDB userdb : userRepos.findAll()) {
			count++
		}
		return count // + "response" + response
	}*/

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
			if (npcDB.npcLocation == location) {
				npcs.write(npcDB.npcLocation + ",")
				count++
			}
		}
		return npcs.toString()
	}

	 String CountUsersByLocation(Integer location) {
		StringWriter users = new StringWriter()
		Integer count = 0
		for (UserDB userDB : userRepos.findAll()) {
			if (userDB.userLocation == location) {
				users.write(userDB.userName + ",")
				count++
			}
		}
		return users.toString()
	}

	 HashMap<Integer, String> ShowUsersInLocation(Integer index) {
		HashMap<Integer, String> users = new HashMap<Integer, String>()
		for (UserDB userDB : userRepos.findAll()) {
			if (userDB.userLocation == index) {
				users.put(userDB.id, userDB.userName)
			}
		}
		return users // + "response" + response
	}

	 HashMap<Integer, String> ShowNpcsInLocation(Integer index) {
		HashMap<Integer, String> npcs = new HashMap<Integer, String>()
		for (NpcDB npcDB : npcRepos.findAll()) {
			if (npcDB.npcLocation == index) {
				npcs.put(npcDB.id, npcDB.npcName)
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
		npcRepos.findById(temp)       //.get().setLocation(location.intValue())
		userRepos.findByUserName(name).userLocation(location.intValue())
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
			mapObj.put(count, cacheDB.cacheCurrentRoomStatus())
			count++
		}

		return mapObj
	}

	//// get individual user or npc
	 String getUserByName(String name) {
		return userRepos.findByUserName(name)
	}

	 String getNpcByName(String name) {
		return npcRepos.findByNpcName(name)
	}

	 String getUserByIndex(Integer index) {
		return userRepos.findById(index)
	}

	 String getNpcByIndex(Integer index) {
		return npcRepos.findById(index)
	}

	 String getStatus(String where) {
		return where
	}

	 Map findUserStatsByName(String name) throws JSONException {
		HashMap userObj = new HashMap()
		try {
			userObj.put("name", name)
			userObj.put("attack", userRepos.findByUserName(name).userAttack)
			userObj.put("defense", userRepos.findByUserName(name).userDefense)
			userObj.put("description", userRepos.findByUserName(name).userDescription)
			userObj.put("exp", userRepos.findByUserName(name).userExp)
			userObj.put("hp", userRepos.findByUserName(name).userHp)
			userObj.put("location", userRepos.findByUserName(name).userLocation)
			userObj.put("lvl", userRepos.findByUserName(name).userLvl)
			userObj.put("money", userRepos.findByUserName(name).userMoney)
			userObj.put("name", userRepos.findByUserName(name).userName)
		}
		catch (Exception e) {
			e.printStackTrace()
			return (HashMap) userObj.put("error", e.toString())
		}
		return userObj
	 }

	 String getMeAgoodName() {
		Random rand = new Random()
		String vocals = "aeiou" + "ioaeu" + "ouaei"
		String cons = "bcdfghjklznpqrst" + "bcdfgjklmnprstvw" + "bcdfgjklmnprst"
		//String allchars = vocals + cons
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
		newCacheEntry.cacheCurrentRoomStatus(msg)
		newCacheEntry.cacheMapName("map_" + location)
		cacheRepos.save(newCacheEntry)
		// return Methods.getNpcByIndex(index)
	}
}
