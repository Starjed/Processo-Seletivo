package com.processo.seletivo.services;

import com.processo.seletivo.models.FotoPessoa;
import com.processo.seletivo.models.Pessoa;
import com.processo.seletivo.repository.FotoPessoaRepository;
import com.processo.seletivo.repository.PessoaRepository;
import io.minio.*;
import io.minio.http.Method;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class FotoPessoaService {

    @Autowired
    private MinioClient minioClient;

    @Autowired
    private FotoPessoaRepository fotoPessoaRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Value("${minio.bucket}")
    private String bucket;

    public FotoPessoa uploadFoto(MultipartFile file, Integer pessoaId) throws Exception {
        String nomeArquivo = gerarNomeArquivo(file);

        criarBucketSeNaoExistir();

        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(bucket)
                        .object(nomeArquivo)
                        .stream(file.getInputStream(), file.getSize(), -1)
                        .contentType(file.getContentType())
                        .build()
        );

        Pessoa pessoa = pessoaRepository.findById(pessoaId)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));

        FotoPessoa foto = new FotoPessoa();
        foto.setPessoa(pessoa);
        foto.setFpBucket(bucket);
        foto.setFpHash(nomeArquivo);
        foto.setFpData(LocalDate.now());

        return fotoPessoaRepository.save(foto);
    }

    public List<FotoPessoa> uploadMultiplasFotos(List<MultipartFile> arquivos, Integer pessoaId) throws Exception {
        Pessoa pessoa = pessoaRepository.findById(pessoaId)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));

        criarBucketSeNaoExistir();

        List<FotoPessoa> fotosSalvas = new ArrayList<>();

        for (MultipartFile file : arquivos) {
            String nomeArquivo = gerarNomeArquivo(file);

            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucket)
                            .object(nomeArquivo)
                            .stream(file.getInputStream(), file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build()
            );

            FotoPessoa foto = new FotoPessoa();
            foto.setPessoa(pessoa);
            foto.setFpBucket(bucket);
            foto.setFpHash(nomeArquivo);
            foto.setFpData(LocalDate.now());

            fotosSalvas.add(fotoPessoaRepository.save(foto));
        }

        return fotosSalvas;
    }

    public String gerarUrlTemporaria(String nomeArquivo) throws Exception {
        return minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                        .method(Method.GET)
                        .bucket(bucket)
                        .object(nomeArquivo)
                        .expiry(300)
                        .build()
        );
    }

    public List<String> gerarLinksPorPessoa(Integer pessoaId) throws Exception {
        List<FotoPessoa> fotos = fotoPessoaRepository.findByPessoaPesIdOrderByFpDataDesc(pessoaId);
        List<String> links = new ArrayList<>();

        for (FotoPessoa foto : fotos) {
            String url = minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.GET)
                            .bucket(bucket)
                            .object(foto.getFpHash())
                            .expiry(300)
                            .build()
            );
            links.add(url);
        }

        return links;
    }

    private void criarBucketSeNaoExistir() throws Exception {
        boolean existe = minioClient.bucketExists(
                BucketExistsArgs.builder().bucket(bucket).build()
        );
        if (!existe) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucket).build());
        }
    }

    private String gerarNomeArquivo(MultipartFile file) {
        String extensao = "";
        String originalName = file.getOriginalFilename();
        if (originalName != null && originalName.contains(".")) {
            extensao = originalName.substring(originalName.lastIndexOf("."));
        }
        return UUID.randomUUID().toString().replace("-", "").substring(0, 32) + extensao;
    }
}
