package com.simaoneto.service;

import com.simaoneto.domain.entity.Pedido;
import com.simaoneto.domain.enums.StatusPedido;
import com.simaoneto.res.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {
    Pedido salvar(PedidoDTO pedidoDTO);
    Optional<Pedido> obterPedidoCompleto(Integer id);
    void atualizaStatus(Integer id, StatusPedido statusPedido);
}
