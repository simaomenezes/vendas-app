package com.simaoneto.service.impl;

import com.simaoneto.domain.entity.Pedido;
import com.simaoneto.domain.repository.Pedidos;
import com.simaoneto.res.dto.PedidoDTO;
import com.simaoneto.service.PedidoService;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl implements PedidoService {

    private Pedidos pedidos;

    public PedidoServiceImpl(Pedidos pedidos) {
        this.pedidos = pedidos;
    }

    @Override
    public Pedido salvar(PedidoDTO pedidoDTO) {
        return null;
    }
}
