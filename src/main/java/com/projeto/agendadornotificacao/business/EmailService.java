package com.projeto.agendadornotificacao.business;

import com.projeto.agendadornotificacao.business.dto.TarefasDTO;
import com.projeto.agendadornotificacao.infrastructure.exceptions.EmailException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender _javaMailSender;
    private final TemplateEngine _templateEngine;

    @Value("${envio.email.remetente}")
    public String remetente;

    @Value("${envio.email.nomeRemetente}")
    private String nomeRemetente;


    public void enviarEmail(TarefasDTO tarefasDTO) {

        try {

            MimeMessage mensagem = _javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mensagem, true,
                    StandardCharsets.UTF_8.name());

            mimeMessageHelper.setFrom(new InternetAddress(remetente, nomeRemetente));
            mimeMessageHelper.setTo(InternetAddress.parse(tarefasDTO.getEmailUsuario()));
            mimeMessageHelper.setSubject("Notificação de Tarefa");

            Context context = new Context();

            context.setVariable("nomeTarefa", tarefasDTO.getNomeDescricao());
            context.setVariable("dataEvento", tarefasDTO.getDataEvento());
            context.setVariable("descricao", tarefasDTO.getDescricao());

            String template = _templateEngine.process("notificacao", context);
            mimeMessageHelper.setText(template, true);
            _javaMailSender.send(mensagem);

        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new EmailException("Erro ao enviar o email.", e.getCause()) ;
        }
    }
}
