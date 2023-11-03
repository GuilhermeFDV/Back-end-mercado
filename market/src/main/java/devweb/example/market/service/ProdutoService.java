package devweb.example.market.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import devweb.example.market.domain.Produto;
import devweb.example.market.repository.ProdutoRepository;
import devweb.example.market.requests.ProdutoPutRequestBody;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProdutoService<ProdutoPostRequestBody> {

    private final ProdutoRepository produtoRepository;
    public List<Produto> listAll(){
        return produtoRepository.findAll();
    }

    public Produto findByIdOrthrowBadRequestException(Long id){
        return produtoRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto not found"));
    }
    
    public Produto save(ProdutoPostRequestBody produtoPostRequestBody){
        return produtoRepository.save(Produto.builder().name(produtoPostRequestBody.getName()).build());
    }

    public void delete(long id) {
        produtoRepository.delete(findByIdOrthrowBadRequestException(id));
    }

    public void replace(@RequestBody ProdutoPutRequestBody produtoPutRequestBody) {
        findByIdOrthrowBadRequestException(produtoPutRequestBody.getId());
        Produto produto = Produto.builder()
        .id(produtoPutRequestBody.getId())
        .name(produtoPutRequestBody.getId())
        .descricao(produtoPutRequestBody.getId())
        .preco_kg(produtoPutRequestBody.getId())
        .estoque(produtoPutRequestBody.getId())
        .build();
        produtoRepository.save(produto);
   
    }
}