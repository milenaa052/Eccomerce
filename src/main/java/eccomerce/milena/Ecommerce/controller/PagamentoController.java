package eccomerce.milena.Ecommerce.controller;

import eccomerce.milena.Ecommerce.dto.FormaPgtoRequestDTO;
import eccomerce.milena.Ecommerce.model.*;
import eccomerce.milena.Ecommerce.repository.PagamentoRepository;
import eccomerce.milena.Ecommerce.repository.FormaPgtoRepository;
import eccomerce.milena.Ecommerce.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private FormaPgtoRepository formaPgtoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @GetMapping
    public ResponseEntity<List<Pagamentos>> findAll() {
        List<Pagamentos> pagamentos = this.pagamentoRepository.findAll();
        return ResponseEntity.ok(pagamentos);
    }

    @GetMapping("/{id}")
    public Pagamentos findById(@PathVariable Integer id) {
        return this.pagamentoRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Pagamento não encontrado"));
    }

    @PostMapping
    public ResponseEntity<FormaPGTO> save(@RequestBody FormaPgtoRequestDTO dto) {
        if (dto.pedidoId() == null || dto.meioPagamento() == null || dto.formaPgto() == null || dto.qntdParcelas() == null) {
            return ResponseEntity.status(428).body(null);
        }

        Pedidos pedido = pedidoRepository.findById(dto.pedidoId())
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado"));

        FormaPGTO pagamento = new FormaPGTO();
        pagamento.setPedidoId(pedido);
        pagamento.setMeioPagamento(dto.meioPagamento());
        pagamento.setFormaPgto(dto.formaPgto());
        pagamento.setQntdParcelas(dto.qntdParcelas());

        pagamento.processarPagamento();

        FormaPGTO novoPagamento = formaPgtoRepository.save(pagamento);
        return ResponseEntity.ok(novoPagamento);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Pagamentos pagamento = this.pagamentoRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Pagamento não encontrado"));

        this.pagamentoRepository.delete(pagamento);
        return ResponseEntity.noContent().build();
    }
}