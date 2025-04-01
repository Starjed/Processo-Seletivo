package com.processo.seletivo.controllers;

import com.processo.seletivo.models.FotoPessoa;
import com.processo.seletivo.services.FotoPessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/fotos")
public class FotoPessoaController {

    @Autowired
    private FotoPessoaService fotoService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFotos(
            @RequestParam("files") List<MultipartFile> files,
            @RequestParam("pessoaId") Integer pessoaId) {
        try {
            List<FotoPessoa> fotos = fotoService.uploadMultiplasFotos(files, pessoaId);
            return ResponseEntity.ok("Fotos salvas: " + fotos.size());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro: " + e.getMessage());
        }
    }


    @GetMapping("/pessoa/{pessoaId}/links")
    public ResponseEntity<?> gerarLinksPorPessoa(@PathVariable Integer pessoaId) {
        try {
            List<String> links = fotoService.gerarLinksPorPessoa(pessoaId);
            return ResponseEntity.ok(links);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro: " + e.getMessage());
        }
    }

    @DeleteMapping("/excluir/{fotoId}")
    public ResponseEntity<?> deletarFoto(@PathVariable Integer fotoId) {
        try {
            fotoService.deletarFoto(fotoId);
            return ResponseEntity.ok("Foto removida com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro: " + e.getMessage());
        }
    }

    @PutMapping("/editar/{fotoId}")
    public ResponseEntity<?> editarFoto(
            @PathVariable Integer fotoId,
            @RequestParam("file") MultipartFile novoArquivo) {
        try {
            fotoService.atualizarFoto(fotoId, novoArquivo);
            return ResponseEntity.ok("Foto atualizada com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao atualizar a foto: " + e.getMessage());
        }
    }

}

