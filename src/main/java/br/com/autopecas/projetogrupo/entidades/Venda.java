package br.com.autopecas.projetogrupo.entidades;

import java.time.LocalDate;

public class Venda {
   private Long id;
   private LocalDate data;
   private Float totalVenda;
   private Cliente cliente;
   private Funcionario funcionario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Float getTotalVenda() {
        return totalVenda;
    }

    public void setTotalVenda(Float totalVenda) {
        this.totalVenda = totalVenda;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
}
