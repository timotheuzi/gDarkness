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

@Controller
class ModelAPIs {

    @Autowired
    UserRepo uRepo

    @Autowired
    MapRepo mRepo

    @Autowired
    NpcRepo nRepo

    @Autowired
    DarknessUtils utils

    // index page
    @GetMapping("/")
    String index()
    {
        //todo random moves
        utils.initializeMapValues()
        utils.initializeItemValues()
        utils.initializeNpcValues()
        return "index"
    }
    // home map
    @GetMapping("/home")
    String home(@RequestParam(name="name", required=false, defaultValue="test_user") String name, Model model)
    {
        //todo random moves
        //darknessUtils.randomNpcMove()
        //currentMap = null
        //darknessUtils.initializeMapValues()
        //String userName = uRepo.findByName(name).getName()

        Integer currentMap = mRepo.findById(1).get()
        model.addAttribute("name", name)
        model.addAttribute("mapName", mRepo.findById(currentMap.intValue()).get().mapName)
        model.addAttribute("description", mRepo.findById(currentMap.intValue()).get().mapDescription)
        model.addAttribute("npcs", utils.ShowNpcsInLocation(currentMap))
        model.addAttribute("users", utils.ShowUsersInLocation(currentMap))
        model.addAttribute("location", currentMap)
        return "home"
    }
    // alley templates
    @GetMapping("/alley_1")
    String alley_1(@RequestParam(name="name", required=false, defaultValue="test_user") String name, Model model) {
        //todo random moves
        //darknessUtils.randomNpcMove()
        //darknessUtils.initializeMapValues()
        Integer currentMap = utils.move(name)
        uRepo.findByUserName(name).userLocation(currentMap.intValue())
        //darknessUtils.initializeNpcValues()
        model.addAttribute("name", name)
        model.addAttribute("mapName", mRepo.findById(currentMap.intValue()).get().mapName)
        model.addAttribute("description", mRepo.findById(currentMap.intValue()).get().mapDescription)
        ///model.addAttribute("nps", mRepo.findById(currentMap.intValue()).)
        //model.addAttribute("users", mRepo.findById(currentMap.intValue()).getUsers())
        //todo show npcs
        model.addAttribute("npcs", utils.ShowNpcsInLocation(currentMap.intValue()))
        //todo show npcs
        model.addAttribute("users", utils.ShowUsersInLocation(currentMap.intValue()))
        model.addAttribute("location", currentMap)
        return "alley_1"
    }

    //todo create greeting page
    /*@GetMapping("/greeting")
    String greeting(@RequestParam(name="name", required=false, defaultValue="test_user") String name, Model model)
    {
        model.addAttribute("name", name)

        UserDB tempEntry = new UserDB()
        if (!uRepo.findByUserName(name))
        {
            tempEntry.userName(name)
            tempEntry.userLvl(1)
            tempEntry.userMoney(1)
            tempEntry.userExp(1)
            tempEntry.userAttack(1)
            tempEntry.userDefense(1)
            tempEntry.userDescription("A testing object named " + name)
            tempEntry.userLocation(1)
            uRepo.save(newEntry)
        }
        return "greeting"
    }*/
}
