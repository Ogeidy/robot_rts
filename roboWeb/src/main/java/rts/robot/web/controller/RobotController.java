package rts.robot.web.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import rts.robot.dto.SignalsDTO;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class RobotController {
    @Autowired
    private SerialPortManager serialPortManager;
    @Autowired
    private SignalsDTO signalsDTO;

    Logger LOGGER = Logger.getLogger(RobotController.class);

    @RequestMapping(value = {"/","/home"}, method = RequestMethod.GET)
    public String showHomePage() {
        return "home";
    }

    @RequestMapping(value="/forward", method = RequestMethod.POST)
    public String forward() {
        LOGGER.info("Forward button was clicked");
        try {
            byte c = 1;
            serialPortManager.write(c);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/home";
    }

    @RequestMapping(value="/back", method = RequestMethod.POST)
    public String back() {
        LOGGER.info("Back button was clicked");
        try {
            byte c = 2;
            serialPortManager.write(c);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/home";
    }

    @RequestMapping(value="/left", method = RequestMethod.POST)
    public String left() {
        LOGGER.info("Left button was clicked");
        try {
            byte c = 4;
            serialPortManager.write(c);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/home";
    }

    @RequestMapping(value="/right", method = RequestMethod.POST)
    public String right() {
        LOGGER.info("Right button was clicked");
        try {
            byte c = 3;
            serialPortManager.write(c);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/home";
    }

    @RequestMapping(value="/stop", method = RequestMethod.POST)
    public String stop() {
        LOGGER.info("Stop button was clicked");
        try {
            byte c = 5;
            serialPortManager.write(c);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/home";
    }

    @RequestMapping(value="/signals", method = RequestMethod.GET, produces="application/json")
    //public String signals(Model model) {
    public @ResponseBody SignalsDTO signals() {
        /*LOGGER.info("bck:" + signalsDTO.isBck());
        LOGGER.info("fwd:" + signalsDTO.isFwd());
        LOGGER.info("bckRight:" + signalsDTO.isBckRight());
        LOGGER.info("bckLeft:" + signalsDTO.isBckLeft());
        LOGGER.info("fwdRight:" + signalsDTO.isFwdRight());
        LOGGER.info("fwdLeft:" + signalsDTO.isFwdLeft());
        */
        //model.addAttribute("currentSignals", signalsDTO);
        //model.addAttribute("currentSignals", signalsDTO);
        //session.setAttribute("currentSignals", signalsDTO);
        return signalsDTO;
    }
}
