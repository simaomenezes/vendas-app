package com.simaoneto.service;

import com.simaoneto.domain.entity.Pedido;
import com.simaoneto.res.dto.PedidoDTO;

public interface PedidoService {
    Pedido salvar(PedidoDTO pedidoDTO);
}
