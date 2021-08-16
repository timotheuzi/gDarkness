package com.darkness.controller

import com.darkness.db.MapRepo
import com.darkness.db.NpcRepo
import com.darkness.db.UserDB
import com.darkness.db.UserRepo
import com.darkness.utils.DarknessUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ModelAPIs {

    @Autowired
    UserRepo uRepo
    @Autowired
    UserDB userDB

    @Autowired
    MapRepo mRepo

    @Autowired
    NpcRepo nRepo

    @Autowired
    DarknessUtils utils

    @GetMapping("/greeting")
    String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model)
    {

        model.addAttribute("name", name)
        UserDB newEntry = new UserDB()
		newEntry.id
        if (!uRepo.findByUserName(name))
        {
        	newEntry.userName(name)
        	newEntry.userLvl(1)
        	newEntry.userMoney(1)
        	newEntry.userExp(1)
        	newEntry.userAttack(1)
        	newEntry.userDefense(1)
        	newEntry.userDescription("A weak noob with no weapon")
        	newEntry.userLocation(1)
        	uRepo.save(newEntry)
        }
        return "greeting"
    }
    // home map
    @GetMapping("/home")
    String home(@RequestParam(name="name", required=false) String name, Model model)
    {
        //todo random moves
        //darknessUtils.randomNpcMove()
        Integer currentMap = null
        //darknessUtils.initializeMapValues()
        //String userName = uRepo.findByName(name).getName()
        currentMap = mRepo.findById(1).get()
        //uRepo.findByName(name).setLocation()
        model.addAttribute("name", name)
        model.addAttribute("mapName", mRepo.findById(currentMap.intValue()).get().mapName)
        model.addAttribute("description", mRepo.findById(currentMap.intValue()).get().mapDescription)
        model.addAttribute("npcs", darknessUtils.ShowNpcsInLocation(currentMap))
        model.addAttribute("users", darknessUtils.ShowUsersInLocation(currentMap))
        model.addAttribute("location", currentMap)
        return "home"
    }
    // alley templates
    @GetMapping("/alley_1")
    String alley_1(@RequestParam(name="name", required=true) String name, Model model) {
        //todo random moves
        //darknessUtils.randomNpcMove()
        //darknessUtils.initializeMapValues()
        Integer currentMap = darknessUtils.move(name)
        uRepo.findByUserName(name).userLocation(currentMap.intValue())
        //darknessUtils.initializeNpcValues()
        model.addAttribute("name", name)
        model.addAttribute("mapName", mRepo.findById(currentMap.intValue()).get().mapName)
        model.addAttribute("description", mRepo.findById(currentMap.intValue()).get().mapDescription)
        ///model.addAttribute("nps", mRepo.findById(currentMap.intValue()).)
        //model.addAttribute("users", mRepo.findById(currentMap.intValue()).getUsers())
        //todo show npcs
        model.addAttribute("npcs", darknessUtils.ShowNpcsInLocation(currentMap.intValue()))
        //todo show npcs
        model.addAttribute("users", darknessUtils.ShowUsersInLocation(currentMap.intValue()))
        model.addAttribute("location", currentMap)
        return "alley_1"
    }
}
