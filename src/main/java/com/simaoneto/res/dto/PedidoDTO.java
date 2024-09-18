package com.simaoneto.res.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {
    @NotNull(message = "Campo obrigatório")
    private Integer cliente;

    @NotNull(message = "Campo obrigatório")
    private BigDecimal total;

    private List<ItemPedidoDTO> itemPedidoDTOS;
}
