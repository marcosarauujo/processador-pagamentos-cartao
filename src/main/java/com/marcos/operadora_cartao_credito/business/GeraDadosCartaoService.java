package com.marcos.operadora_cartao_credito.business;

import com.marcos.operadora_cartao_credito.infrastructure.entity.CartaoEntity;
import com.marcos.operadora_cartao_credito.infrastructure.entity.ClienteEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class GeraDadosCartaoService {

    private static final String PREFIXO_NUMERO_CARTAO_VISA = "4000"; //4 primeiros digitos do cartao
    private static final int TAMANHO_NUMERO_CARTAO = 16; //quantidade de caracteres do cartao


    public CartaoEntity gerarCartaoParaCliente(ClienteEntity cliente) {
        String numeroCartao = gerarNumeroCartao();
        LocalDate dataExpiracao = gerarDataExpiracao();
        String cvv = gerarCVV();
        double limiteCartao = determinarLimiteCredito(cliente);
        double limiteCartaoDisponivel = limiteCartao; // No início, o disponível é igual ao limite total!

        return new CartaoEntity(
                numeroCartao,
                dataExpiracao,
                cvv,
                limiteCartao,       // limite total
                limiteCartaoDisponivel,       // limite disponível (igual ao total no início)
                cliente,
                LocalDate.now(),    // data de hoje
                10                  // dia de vencimento da fatura (ex: dia 10)
        );
}


private String gerarNumeroCartao() {
    Random random = new Random(); //Cria um gerador de números aleatórios.
    StringBuilder numeroCartao = new StringBuilder(PREFIXO_NUMERO_CARTAO_VISA);
    for (int i = 0; i < TAMANHO_NUMERO_CARTAO - PREFIXO_NUMERO_CARTAO_VISA.length(); i++) {
        numeroCartao.append(random.nextInt(10)
        );
    }
    return numeroCartao.toString();
}

private LocalDate gerarDataExpiracao() {
    Random random = new Random();
    int mes = random.nextInt(12) + 1; // Sorteia mês de 1 a 12
    int ano = random.nextInt(5) + 2026; // Sorteia um ano no futuro (ex: 2026 a 2030)
    return LocalDate.of(ano, mes, 1);
}

private String gerarCVV() {
    Random random = new Random();
    return String.format("%03d", random.nextInt(1000) //sorteia um número de 0 até 999.
    );
}

private double determinarLimiteCredito(ClienteEntity cliente) {
    int idade = cliente.getIdade();
    double salario = cliente.getRendaMensal();

    if (idade <= 25) return calcularLimiteJovem(salario);
    if (idade <= 40) return calcularLimiteAdulto(salario);
    return calcularLimiteSenior(salario);
}

private double calcularLimiteJovem(double salario) {
    if (salario < 3000) return 1000.00;
    if (salario < 6000) return 3000.00;
    return 10000.00;
}

private double calcularLimiteAdulto(double salario) {
    if (salario < 4000) return 2000.00;
    if (salario < 8000) return 5000.00;
    return 10000.00;
}

private double calcularLimiteSenior(double salario) {
    if (salario < 5000) return 3000.00;
    if (salario < 10000) return 8000.00;
    return 15000.00;
}

}
