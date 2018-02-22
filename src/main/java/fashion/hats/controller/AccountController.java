package fashion.hats.controller;

import fashion.hats.entity.Accounts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import fashion.hats.service.AccountService;

@Controller
@CrossOrigin
@RestController
@RequestMapping (path = "/sample")
public class AccountController {

    public static final Logger logger = LoggerFactory.getLogger(AccountController.class);
    private Environment env;

    @Autowired
    private AccountService accountService;

    @RequestMapping (value = "/")
    public String home() {
        return "home";
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public @ResponseBody
    Iterable<Accounts> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @RequestMapping(value = "/account/getAccount", method = RequestMethod.POST)
    public @ResponseBody
    Iterable<Accounts> getAccountsByFirstName(@RequestParam(value = "firstname") String firstname) {
        return accountService.getAccountsByFirstName(firstname);
    }

    @RequestMapping(value = "/account/createAccount", method = RequestMethod.POST)
    ResponseEntity createNewAccount(@RequestBody Accounts account) {

        try {
            accountService.createAccount(account);
            return ResponseEntity.ok(HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("New user not saved " + e);
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(value = "/account/removeAccount", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity deleteAccount(@RequestParam(value = "id") String id) {

        try {
            accountService.removeAccount(id);
            return ResponseEntity.ok(HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("User not removed" + e);
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(value = "/account/updateAccount", method = RequestMethod.POST)
    ResponseEntity updateAccount(@RequestBody Accounts account) {

        try {
            accountService.updateAccount(account);
            return ResponseEntity.ok(HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("User not updated" + e);
            return ResponseEntity.badRequest().build();
        }
    }

}
