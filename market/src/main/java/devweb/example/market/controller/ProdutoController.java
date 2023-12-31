package devweb.example.market.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import devweb.example.market.domain.Produto;
import devweb.example.market.requests.ProdutoPutRequestBody;
import devweb.example.market.service.ProdutoService;
import devweb.example.market.util.DateUtil;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("produtos")
@Log4j2
@RequiredArgsConstructor
public class ProdutoController {
    private final DateUtil dateUtil;
    private final ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<Produto>> list(){
        log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return new ResponseEntity<>(produtoService.listAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Produto> findByIdOrthrowBadRequestException(@PathVariable long id){
        return ResponseEntity.ok(produtoService.findByIdOrthrowBadRequestException(id));
    }

    @PostMapping
    public ResponseEntity<Produto> save(@RequestBody ProdutoPutRequestBody produtoPostRequestBody){
        return new ResponseEntity<>(produtoService.save(produtoPostRequestBody),HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        produtoService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody ProdutoPutRequestBody produtoPutRequestBody){
        produtoService.replace(produtoPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
};
