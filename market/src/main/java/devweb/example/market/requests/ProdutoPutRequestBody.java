package devweb.example.market.requests;

import lombok.Data;

@Data
public class ProdutoPutRequestBody {
    private Long id;
    private String name;
    private String descricao;
    private int preco_kg;
    private int estoque;
}
