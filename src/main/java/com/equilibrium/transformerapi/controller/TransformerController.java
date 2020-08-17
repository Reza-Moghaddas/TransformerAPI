package com.equilibrium.transformerapi.controller;


import com.equilibrium.transformerapi.model.Transformer;
import com.equilibrium.transformerapi.model.TransformerBattleResult;
import com.equilibrium.transformerapi.service.TransformerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(value = "API Description")
public class TransformerController {
    @Autowired
    private TransformerService transformerService;

    @ApiOperation(value = "It will return list of Transformers")
    @GetMapping("/transformers")
    public ResponseEntity<List<Transformer>> getAllTransformer() {
        return ResponseEntity.ok().body(transformerService.getAllTransformer());
    }

    @ApiOperation(value = "It will return a Transformer by Id")
    @GetMapping("/transformers/{id}")
    public ResponseEntity<Transformer> getTransformerById(@PathVariable long id){
        return ResponseEntity.ok().body(transformerService.getTransformerById(id));
    }

    @ApiOperation(value = "It will add new Transformer")
    @PostMapping("/transformers")
    public ResponseEntity<Transformer> createTransformer(@RequestBody Transformer transformer){
        return ResponseEntity.ok().body(this.transformerService.createTransformer(transformer));
    }

    @ApiOperation(value = "It will update Transformer")
    @PutMapping("/transformers/{id}")
    public ResponseEntity<Transformer> updateTransformer(@PathVariable long id, @RequestBody Transformer transformer){
        transformer.setId(id);
        return ResponseEntity.ok().body(this.transformerService.updateTransformer(transformer));
    }

    @ApiOperation(value = "It will delete Transformer")
    @DeleteMapping("/transformers/{id}")
    public HttpStatus deleteTransformer(@PathVariable long id){
        this.transformerService.deleteTransformer(id);
        return HttpStatus.ACCEPTED;
    }

    @ApiOperation(value = "It will run the battle game Transformer")
    @GetMapping("/transformers/battle/{idList}")
    public ResponseEntity<TransformerBattleResult> Battle(@PathVariable ArrayList<Long> idList){
        return ResponseEntity.ok().body(this.transformerService.battle(idList));
    }
}
