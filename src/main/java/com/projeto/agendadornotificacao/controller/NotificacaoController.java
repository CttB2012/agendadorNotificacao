package com.projeto.agendadornotificacao.controller;


import com.projeto.agendadornotificacao.business.EmailService;
import com.projeto.agendadornotificacao.business.dto.TarefasDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NotificacaoController {

    private final EmailService _emailService;

    @PostMapping("/email")
    public ResponseEntity<Void> enviarEmail(@RequestBody TarefasDTO tarefasDTO){

        _emailService.enviarEmail(tarefasDTO);
        return ResponseEntity.ok().build();
    }


}
