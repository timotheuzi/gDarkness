package com.darkness.controller

import com.darkness.db.*
import com.darkness.model.GenericRequest
import com.darkness.model.GenericResponse
import com.darkness.model.UserObject
import com.darkness.utils.DarknessUtils
import org.json.JSONException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

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

    @RequestMapping(method = RequestMethod.GET, path = "/initializeMap", produces = MediaType.APPLICATION_JSON_VALUE)
    GenericResponse initializeMap() {
        darknessUtils.initializeMapValues()
        GenericResponse response = new GenericResponse()
        response.setCode("400")
        response.setMessage("maps have been initialized")
        return response
    }

    @RequestMapping(method = RequestMethod.GET, path = "/initializeNpc", produces = MediaType.APPLICATION_JSON_VALUE)
    GenericResponse initializeNpc() {
        darknessUtils.initializeNpcValues()
        GenericResponse response = new GenericResponse()
        response.setCode("333")
        response.setMessage("npcs have been initialized")
        return response
    }

    @RequestMapping(method = RequestMethod.GET, path = "/initializeItem", produces = MediaType.APPLICATION_JSON_VALUE)
    GenericResponse initializeItem() {
        darknessUtils.initializeItemValues()
        GenericResponse response = new GenericResponse()
        response.setCode("555")
        response.setMessage("items have been initialized")
        return response
    }

    @RequestMapping(method = RequestMethod.POST, path = "/getFullInformation", produces = MediaType.APPLICATION_JSON_VALUE)
    UserObject getFullInformation(@RequestParam(name = "name", required = true) String name,
                           @RequestParam(name = "user", required = false, defaultValue = "user") String user) {
        Boolean isNpc = false
        if (user.contains("npc")) {
            isNpc = true
        }
        return darknessUtils.getStats(name, isNpc)
    }

    @RequestMapping(method = RequestMethod.POST, path = "/createNewUser", produces = MediaType.APPLICATION_JSON_VALUE)
    GenericResponse createNewUser(@RequestBody GenericRequest obj) {
        GenericResponse response = new GenericResponse()
        if (darknessUtils.createNewUser(obj.getName())) {
            response.setMessage("New User " + obj.getName() + " created....")
        } else {
            response.setMessage("User already exists, logging in using " + obj.getName() + " and redirect to home")
        }
        return response
    }

    @RequestMapping(method = RequestMethod.POST, path = "/setUser", produces = MediaType.APPLICATION_JSON_VALUE)
    UserObject setUser(@RequestBody UserObject obj) {
        //UserDB userObj = uRepo.findByName(obj.getName())
        //userObj.name
        Integer id = uRepo.findByNameIgnoreCase(obj.getName()).id
        if (id == null) {
            darknessUtils.createNewUser(obj.getName())
            return "New User Created name " + obj.getName() + " created...." as UserObject
        }
        UserDB newEntry = new UserDB()
        newEntry.id = id
        newEntry.name = (obj.getName())
        if (obj.getLvl() != null) {
            newEntry.lvl = (obj.getLvl())
        } else {
            newEntry.lvl = (uRepo.findByNameIgnoreCase(obj.getName()).lvl)
        }
        if (obj.getMoney() != null) {
            newEntry.money = (obj.getMoney())
        } else {
            newEntry.money = (uRepo.findByNameIgnoreCase(obj.getName()).money)
        }
        if (obj.getExp() != null) {
            newEntry.exp = (obj.getExp())
        } else {
            newEntry.exp = (uRepo.findByNameIgnoreCase(obj.getName()).exp)
        }
        if (obj.getAttack() != null) {
            newEntry.attack = (obj.getAttack())
        } else {
            newEntry.attack = (uRepo.findByNameIgnoreCase(obj.getName()).attack)
        }
        if (obj.getDefense() != null) {
            newEntry.defense = (obj.getDefense())
        } else {
            newEntry.defense = (uRepo.findByNameIgnoreCase(obj.getName()).defense)
        }
        if (obj.getDescription() != null) {
            newEntry.description = (obj.getDescription())
        } else {
            newEntry.description = (uRepo.findByNameIgnoreCase(obj.getName()).description)
        }
        if (obj.getLocation() != null) {
            newEntry.location = (obj.getLocation())
        } else {
            newEntry.location = (uRepo.findByNameIgnoreCase(obj.getName()).location)
        }
        if (obj.getHp() != null) {
            newEntry.hp = (obj.getHp())
        } else {
            newEntry.hp = (uRepo.findByNameIgnoreCase(obj.getName()).hp)
        }
        uRepo.save(newEntry)
        return newEntry as UserObject
    }

    @RequestMapping(method = RequestMethod.POST, path = "/various", produces = MediaType.APPLICATION_JSON_VALUE)
    // consumes
    GenericResponse various(@RequestBody GenericRequest obj) throws JSONException {
        //set up
        Integer location = uRepo.findByNameIgnoreCase(obj.getName()).location
        Integer userId = uRepo.findByNameIgnoreCase(obj.getName()).id
        String value = obj.getValue()
        GenericResponse output = new GenericResponse()
        //update rolling cache
        if (!value.isEmpty()) {
            darknessUtils.updateCache(value, location, obj.getName())
        }
        //movement
        if (value.contains("move") || value.contains("go")) {
            Integer newLocation = darknessUtils.move(obj.getName(), location)
            output.setUsers(darknessUtils.ShowUsersInLocation(newLocation) as List<String>)
            output.setNpcs(darknessUtils.ShowNpcsInLocation(newLocation) as List<String>)
            output.setMapDescription(mRepo.findById(newLocation).description)
            CacheDB cache = new CacheDB()
            cache.usersInRoom = (userId.toString())
            cache.msg = (value)
            cache.mapId = (newLocation)
            cRepo.save(cache)
            return output
        }
        //look or inventory
        else if ((value.contains("inv") || value.contains("look")) || (value.contains(darknessUtils.ShowNpcsInLocation(location).toString())
                || value.contains(darknessUtils.ShowUsersInLocation(location).toString()))) {
            List<String> users = darknessUtils.ShowUsersInLocation(location)
            output.setUsers(users)
            List<String> npcs = darknessUtils.ShowNpcsInLocation(location)
            output.setNpcs(npcs)
            output.setUserObj(darknessUtils.getStats(obj.getName(), false))
            return output
        } else {
            output.setMessage("No implementation for that string yet")
            return output
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/updateRoom", produces = MediaType.APPLICATION_JSON_VALUE)
    GenericResponse updateRoom(@RequestParam(name = "name", required = false) String name) {
        UserDB userIndo = uRepo.findByNameIgnoreCase(name)
        return darknessUtils.mapStatus(userIndo.getLocation())
    }

    @RequestMapping(method = RequestMethod.GET, path = "/findUserByIndex", produces = MediaType.APPLICATION_JSON_VALUE)
    String findUserByIndex(@RequestParam(name = "index", required = true) Integer index) {
        // int[] updateRoom = darknessUtils.mapStatus(location)
        return darknessUtils.getUsersByLocation(index)
    }

    @RequestMapping(method = RequestMethod.GET, path = "/findNpcByIndex", produces = MediaType.APPLICATION_JSON_VALUE)
    String findNpcByIndex(@RequestParam(name = "index", required = true) Integer index) {
        // int[] updateRoom = darknessUtils.mapStatus(location)
        return darknessUtils.NpcsByLocation(index)
    }

}