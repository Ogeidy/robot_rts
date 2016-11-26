package rts.robot.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import rts.robot.web.controller.SerialPortManager;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

@Controller
public class RobotController {
    @Autowired
    private SerialPortManager serialPortManager;

    Logger LOGGER = Logger.getLogger(RobotController.class);

    @RequestMapping(value = {"/","/home"}, method = RequestMethod.GET)
    public String showHomePage() {
        return "home";
    }

    @RequestMapping(value="/forward", method = RequestMethod.POST)
    public String forward() {
        LOGGER.info("Forward button was clicked");
        try {
            serialPortManager.write(0x1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/home";
    }

    @RequestMapping(value="/back", method = RequestMethod.POST)
    public String back() {
        LOGGER.info("Back button was clicked");
        return "redirect:/home";
    }

    @RequestMapping(value="/left", method = RequestMethod.POST)
    public String left() {
        LOGGER.info("Left button was clicked");
        return "redirect:/home";
    }

    @RequestMapping(value="/right", method = RequestMethod.POST)
    public String right() {
        LOGGER.info("Right button was clicked");
        return "redirect:/home";
    }

    @RequestMapping(value="/stop", method = RequestMethod.POST)
    public String stop() {
        LOGGER.info("Stop button was clicked");
        return "redirect:/home";
    }
}
