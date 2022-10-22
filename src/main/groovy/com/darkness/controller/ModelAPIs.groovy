package com.darkness.controller

import com.darkness.db.MapRepo
import com.darkness.db.NpcRepo
import com.darkness.db.UserRepo
import com.darkness.utils.DarknessUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

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
    String index() {
        //todo random moves
        utils.initializeMapValues()
        utils.initializeItemValues()
        utils.initializeNpcValues()
        return "index"
    }
    // home map
    @GetMapping("/home")
    String home(@RequestParam(name = "name", required = false, defaultValue = "test_user") String name, Model model) {
        //todo random moves
        //darknessUtils.randomNpcMove()
        //currentMap = null
        //darknessUtils.initializeMapValues()
        //String userName = uRepo.findByName(name).getName()
        //String currentMap = "home"
        model.addAttribute("name", name)
        model.addAttribute("mapName", "Home Surly bar")
        model.addAttribute("description", "dingy bar")
        //model.addAttribute("npcs", utils.ShowNpcsInLocation(currentMap))
        //model.addAttribute("users", utils.ShowUsersInLocation(currentMap))
        model.addAttribute("location", "home")
        return "home"
    }
    // alley templates
    @GetMapping("/alley_1")
    String alley_1(@RequestParam(name = "name", required = false, defaultValue = "test_user") String name, Model model) {
        //todo random moves

        Integer currentMap = utils.move(name)
        uRepo.findByName(name).location(currentMap.intValue())
        model.addAttribute("name", name)
        model.addAttribute("mapName", "alley")
        model.addAttribute("description", "dimlit alleyway")
        model.addAttribute("location", "alley")
        return "alley_1"
    }
}
