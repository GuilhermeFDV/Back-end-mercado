package devweb.example.market.requests;

import lombok.Data;

@Data
class ProdutoPostRequestBody {

    private String name;
    private String descricao;
    private int preco_kg;
    private int estoque;
}
