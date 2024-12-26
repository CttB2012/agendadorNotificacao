package com.projeto.agendadornotificacao.business.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.projeto.agendadornotificacao.business.enums.StatusNotificacaoEnum;
import jakarta.mail.internet.MimeMessage;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TarefasDTO {

    private String id;
    private String nomeDescricao;
    private String descricao;
    private String emailUsuario;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataCriacao;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataAlteracao;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataEvento;

    private StatusNotificacaoEnum statusNotificacaoEnum;

}
