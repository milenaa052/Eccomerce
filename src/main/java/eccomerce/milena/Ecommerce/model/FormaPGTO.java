package eccomerce.milena.Ecommerce.model;

import jakarta.persistence.*;

@Entity
@Table(name = "FormaPGTO")
public class FormaPGTO extends Pagamentos {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FormaPagamento formaPgto;

    @Column
    private Integer qntdParcelas;

    @Override
    public void processarPagamento() {
        if (this.formaPgto == FormaPagamento.PARCELADO) {
            System.out.println("Processando pagamento parcelado...");
        } else {
            System.out.println("Processando pagamento à vista...");
        }
    }

    public FormaPagamento getFormaPgto() {
        return formaPgto;
    }

    public void setFormaPgto(FormaPagamento formaPgto) {
        this.formaPgto = formaPgto;
    }

    public Integer getQntdParcelas() {
        return qntdParcelas;
    }

    public void setQntdParcelas(Integer qntdParcelas) {
        this.qntdParcelas = qntdParcelas;
    }
}
