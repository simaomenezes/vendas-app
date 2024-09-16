package com.simaoneto.service.impl;

import com.simaoneto.domain.entity.Cliente;
import com.simaoneto.domain.entity.ItemPedido;
import com.simaoneto.domain.entity.Pedido;
import com.simaoneto.domain.entity.Produto;
import com.simaoneto.domain.repository.Clientes;
import com.simaoneto.domain.repository.ItemPedidos;
import com.simaoneto.domain.repository.Pedidos;
import com.simaoneto.domain.repository.Produtos;
import com.simaoneto.exception.RegraNegocioException;
import com.simaoneto.res.dto.ItemPedidoDTO;
import com.simaoneto.res.dto.PedidoDTO;
import com.simaoneto.service.PedidoService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoServiceImpl implements PedidoService {

    private Pedidos repositoryPedidos;
    private Clientes repositoryClientes;
    private Produtos repositoryProdutos;
    private ItemPedidos repositoryItemsPedidos;

    public PedidoServiceImpl(Pedidos repositoryPedidos) {
        this.repositoryPedidos = repositoryPedidos;
    }

    @Override
    public Pedido salvar(PedidoDTO pedidoDTO) {
        Integer idCliente = pedidoDTO.getCliente();
        Cliente cliente = repositoryClientes.findById(idCliente).orElseThrow(() -> new RegraNegocioException("Código de cliente inválido"));

        Pedido pedido = new Pedido();
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);
        pedido.setTotal(pedidoDTO.getTotal());

        List<ItemPedido> itemPedidoList = converterItems(pedido, pedidoDTO.getItemPedidoDTOS());
        repositoryPedidos.save(pedido);
        repositoryItemsPedidos.saveAll(itemPedidoList);
        pedido.setItens(itemPedidoList);

        return pedido;
    }

    private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> itemPedidoDTOS) {
        if(itemPedidoDTOS.isEmpty()){
            throw new RegraNegocioException("Não é possível realizar um pedido sem items.");
        }
        return itemPedidoDTOS
                .stream()
                .map( dto -> {
                    Integer idProduto = dto.getProduto();
                    Produto produto = repositoryProdutos
                            .findById(idProduto)
                            .orElseThrow(
                                    () -> new RegraNegocioException(
                                            "Código de produto inválido: "+ idProduto
                                    ));

                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);
                    return itemPedido;
                }).collect(Collectors.toList());
    }
}
