package com.marcos.operadora_cartao_credito.controller;

import com.marcos.operadora_cartao_credito.business.ClienteService;
import com.marcos.operadora_cartao_credito.business.GeraDadosCartaoService;
import com.marcos.operadora_cartao_credito.business.dto.in.ClienteRequestDTO;
import com.marcos.operadora_cartao_credito.business.dto.out.ClienteResponseDTO;
import com.marcos.operadora_cartao_credito.business.mapper.ClienteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteService clienteService;
    private final ClienteMapper clienteMapper;

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> solicitarCartao(@RequestBody ClienteRequestDTO clienteRequestDTO) {
        return ResponseEntity.ok(clienteMapper.paraClienteResponseDTO(
                        clienteService.solicitarCartao(
                                clienteMapper.paraClienteEntity(clienteRequestDTO)
                        )
                )
        );
    }

    @GetMapping
    public ResponseEntity<ClienteResponseDTO> buscaClientePorCpf(@RequestParam String cpf) {
        return ResponseEntity.ok(clienteMapper.paraClienteResponseDTO(
                        clienteService.buscaClientePorCpf(cpf)
                )
        );
    }


}
