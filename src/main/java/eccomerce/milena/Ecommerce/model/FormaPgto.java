package eccomerce.milena.Ecommerce.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "FormaPGTO")
public class FormaPgto extends Pagamentos{

    @Column
    private String formaPgto;

    @Column
    private Integer qntdParcelas;

    public String getFormaPgto() {
        return formaPgto;
    }

    public void setFormaPgto(String formaPgto) {
        this.formaPgto = formaPgto;
    }

    public Integer getQntdParcelas() {
        return qntdParcelas;
    }

    public void setQntdParcelas(Integer qntdParcelas) {
        this.qntdParcelas = qntdParcelas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        FormaPgto formaPgto = (FormaPgto) o;
        return Objects.equals(formaPgto, formaPgto.formaPgto) && Objects.equals(qntdParcelas, formaPgto.qntdParcelas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), formaPgto, qntdParcelas);
    }
}