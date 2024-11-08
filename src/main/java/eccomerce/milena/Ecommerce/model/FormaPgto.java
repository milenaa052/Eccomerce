package eccomerce.milena.Ecommerce.model;

import jakarta.persistence.*;

import java.util.Objects;

public class FormaPgto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_formPgto;

    @OneToOne
    @JoinColumn(name = "pagamento_id", referencedColumnName = "id_pagamentos")
    private Pagamentos pagamento_id;

    @Column
    private String forma_pgto;

    @Column
    private Integer qntd_parcelas;

    public Integer getId_formPgto() {
        return id_formPgto;
    }

    public void setId_formPgto(Integer id_formPgto) {
        this.id_formPgto = id_formPgto;
    }

    public Pagamentos getPagamento_id() {
        return pagamento_id;
    }

    public void setPagamento_id(Pagamentos pagamento_id) {
        this.pagamento_id = pagamento_id;
    }

    public String getForma_pgto() {
        return forma_pgto;
    }

    public void setForma_pgto(String forma_pgto) {
        this.forma_pgto = forma_pgto;
    }

    public Integer getQntd_parcelas() {
        return qntd_parcelas;
    }

    public void setQntd_parcelas(Integer qntd_parcelas) {
        this.qntd_parcelas = qntd_parcelas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FormaPgto formaPgto = (FormaPgto) o;
        return Objects.equals(id_formPgto, formaPgto.id_formPgto) && Objects.equals(pagamento_id, formaPgto.pagamento_id) && Objects.equals(forma_pgto, formaPgto.forma_pgto) && Objects.equals(qntd_parcelas, formaPgto.qntd_parcelas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_formPgto, pagamento_id, forma_pgto, qntd_parcelas);
    }
}
