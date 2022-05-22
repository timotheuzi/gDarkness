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
import com.fasterxml.jackson.annotation.JsonFormat
import lombok.Value
import net.bytebuddy.utility.RandomString
import org.json.JSONException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
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

    private String map_0 = "An empty bar"
    private String map_1 = "A dark street corner"
    private String map_2 = "A dark alley"

    private String npc_0 = "Surly Bartender"
    private String npc_1 = "Vagrant"

    private String item_1 = "sterling silver"

    void initializeMapValues() {
        Integer count = CountMaps()
        MapDB mapDB = new MapDB()
        if (count == 0) {
            mapDB.name = ("map_" + (CountMaps() + 1).toString())
            mapDB.description = (map_0)
        } else if ((count + 1) == 0) {
            mapDB.name = ("map_" + (CountMaps() + 2).toString())
            mapDB.description = (map_1)
        } else {
            mapDB.name = ("map_" + (CountMaps() + 3).toString())
            mapDB.description = (map_2)
        }
        mapRepos.save(mapDB)
    }

    void initializeItemValues() {
        Double attack = Math.random() * ((10 - 1) + 1)
        Double defense = Math.random() * ((5 - 1) + 1)
        ItemsDB itemsDB = new ItemsDB()
        itemsDB.name = ("luger_" + CountItems().toString())
        itemsDB.description = (item_1)
        itemsDB.attack = (attack.intValue())
        itemsDB.defense = (defense.intValue())
        itemsDB.location = (0)
        itemsDB.value = Math.random() * ((50 - 1) + 1)
        itemsRepos.save(itemsDB)
    }

    void initializeNpcValues() {
        Double attack = Math.random() * ((50 - 1) + 1)
        Double defense = Math.random() * ((10 - 1) + 1)
        Double hp = Math.random() * ((1000 - 1) + 1)
        NpcDB npcDB = new NpcDB()
        if (CountNpcs() == 0) {
            npcDB.name = ("Frank")
            npcDB.description = ("Stinky ")
            npcDB.location = (0)
            npcDB.attack = (75)
            npcDB.defense = (75)
            npcDB.hp = (3000)
        } else {
            npcDB.name = (null)
            npcDB.description = (npc_1)
            npcDB.location = (2)
            npcDB.attack = (attack.intValue())
            npcDB.defense = (defense.intValue())
            npcDB.hp = (hp.intValue())
        }
        npcRepos.save(npcDB)
    }
    /*String getMeAGoodName(){
        String charset = (('A'..'Z') + ('0'..'9')).join()
        Integer length = 9
        String randomString = RandomString(length, charset.toCharArray())
        return randomString
    }*/

    Boolean createNewUser(String name) {
        if (userRepos.findByName(name) != null) {
            try {
                UserDB newEntry = new UserDB()
                newEntry.name = (name)
                newEntry.lvl = (1)
                newEntry.money = (1)
                newEntry.exp = (1)
                newEntry.attack = (1)
                newEntry.defense = (1)
                newEntry.description = ("A new user named " + name)
                newEntry.location = (1)
                newEntry.hp = (1000)
                userRepos.save(newEntry)
                return true
            } catch (Exception e) {
                System.out.println("error")
            }
        } else {
            return false
        }

    }

    HashMap<String, Integer> getStats(String name, Boolean isNpc) {
        HashMap<String, Integer> stats = new HashMap<String, Integer>()
        if (!isNpc) {
            UserDB userObj = userRepos.findByName(name)
            stats.put("ID", userObj.id)
            stats.put("attack", userObj.attack)
            stats.put("defense", userObj.defense)
            stats.put("exp", userObj.exp)
            stats.put("location", userObj.location)
            stats.put("lvl", userObj.lvl)
            stats.put("money", userObj.money)
        } else {
            NpcDB npcObj = npcRepos.findByName(name)
            stats.put("ID", npcObj.id)
            stats.put("attack", npcObj.attack)
            stats.put("defense", npcObj.defense)
            stats.put("hp", npcObj.hp)
            stats.put("location", npcObj.location)
        }
        return stats
    }

    Integer CountMaps() {
        /*Integer count = 0
        for(MapDB mapDb : mapRepos.findAll())
        {
            count++
        }
        return count*/
        return mapRepos.findAll().iterator().size()
    }

    Integer CountUsers() {
        /*Integer count = 0
        for(UserDB userDB : userRepos.findAll())
        {
            count++
        }*/
        //return count
        return userRepos.findAll().iterator().size()
    }

    Integer CountItems() {
        /*Integer count = 0
        for(ItemsDB itemdb : itemsRepos.findAll())
        {
            count++
        }
        return count*/
        return itemsRepos.findAll().iterator().size()
    }

    Map GetUserItems(Integer userId) {
        ItemsDB itemdb = itemsRepos.getItemsByOwner(userId)
        Map<JsonFormat.Value, KeyValueHolder> resp = itemdb.eachWithIndex
                { ItemsDB entry, int i -> entry.owner entry.name }
        return resp
    }

    Integer CountNpcs() {
        Integer count = 0
        for (NpcDB itemdb : npcRepos.findAll()) {
            count++
        }
        return count
    }

    Map NpcsByLocation(Integer location) {
        NpcDB npcDB = npcRepos.findByLocation(location)
        Map resp = npcDB.each { NpcDB entry, int i -> entry.id entry }
        /*NpcDB npc = npcRepos.findByLocation(location)
           Integer count = 0
           for (NpcDB npcDB : npcRepos.findAll()) {
               if (npcDB.npcLocation == location) {
                   npcs.write(npcDB.npcLocation + ",")
                   count++
               }
           }*/
        return resp
    }

    String getUsersByLocation(Integer location) {
        StringWriter users = new StringWriter()
        Integer count = 0
        for (UserDB userDB : userRepos.findAll()) {
            if (userDB.location == location) {
                users.write(userDB.name + ",")
                count++
            }
        }
        return users.toString()
    }

    HashMap<Integer, String> ShowUsersInLocation(Integer index) {
        HashMap<Integer, String> users = new HashMap<Integer, String>()
        for (UserDB userDB : userRepos.findAll()) {
            if (userDB.location == index) {
                users.put(userDB.id, userDB.name)
            }
        }
        return users
    }

    HashMap<Integer, String> ShowNpcsInLocation(Integer index) {
        HashMap<Integer, String> npcs = new HashMap<Integer, String>()
        for (NpcDB npcDB : npcRepos.findAll()) {
            if (npcDB.location == index) {
                npcs.put(npcDB.id, npcDB.name)
            }
        }
        return npcs
    }

    Integer move(String name, Integer location) {
        if (CountMaps() < 11) {
            initializeMapValues()
        }
        Double locationRando = Math.random() * ((CountMaps()))
        // random NPC generation and movement
        //Double npcToMoveRando = Math.random() * ((CountNpcs()))
        //int temp = npcToMoveRando.intValue()
        //NpcDB npcs = npcRepos.findById(temp)
        UserDB users = userRepos.findByNameAndLocation(name, location)
        users.location(locationRando)
        userRepos.save(users)
        // Model model = null
        // templateController.template_1(name, model)
        return locationRando.intValue()
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
            mapObj.put(count, cacheDB.msg())
            count++
        }

        return mapObj
    }

    Map findUserStatsByName(String name) throws JSONException {
        HashMap userObj = new HashMap()
        try {
            UserDB userDB = userRepos.findByName(name)
            userObj.put("name", name)
            userObj.put("attack", userDB.attack)
            userObj.put("defense", userDB.defense)
            userObj.put("description", userDB.description)
            userObj.put("exp", userDB.exp)
            userObj.put("hp", userDB.hp)
            userObj.put("location", userDB.location)
            userObj.put("lvl", userDB.lvl)
            userObj.put("money", userDB.money)
        }
        catch (Exception e) {
            e.printStackTrace()
            return (HashMap) userObj.put("error", e.toString())
        }
        return userObj
    }

    String generateRandomString(Integer len) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijk"
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(len);
        for (Integer i = 0; i < len; i++)
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        return sb.toString();
    }

    void updateCache(String data, Integer location, String name) {
        CacheDB newCacheEntry = new CacheDB()
        newCacheEntry.mapId(location)
        newCacheEntry.usersInRoom(name)
        newCacheEntry.msg(data)
        cacheRepos.save(newCacheEntry)
        // return Methods.getNpcByIndex(index)
    }
}
