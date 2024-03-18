package br.com.autopecas.projetogrupo.entidades;

public class VendaPeca {
    private Long id;
    private Venda venda;
    private Peca peca;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public Peca getPeca() { return peca; }

    public void setPeca(Peca peca) {
        this.peca = peca;
    }


}
