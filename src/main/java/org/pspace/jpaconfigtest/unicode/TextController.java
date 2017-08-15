package org.pspace.jpaconfigtest.unicode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@RequestMapping("/textEntities")
public class TextController {

    @Autowired private TextRepository textRepository;

    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<TextEntity>> findAll() {
        return ResponseEntity.ok(textRepository.findAll());
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<TextEntity> find(@PathVariable("id") long id) {
        TextEntity textEntity = textRepository.findOne(id);

        return textEntity != null ?
                ResponseEntity.ok(textEntity) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public View update(@RequestBody TextEntity textEntity) {
        TextEntity saved = textRepository.save(textEntity);
        return new RedirectView("" + saved.getId());
    }
}
