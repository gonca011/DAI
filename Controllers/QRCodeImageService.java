package com.dai2324.prototipodai.Controlo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.dai2324.prototipodai.Classes.LoggedInUtilizador;
import com.dai2324.prototipodai.Classes.QRCodeImage;
import com.dai2324.prototipodai.Classes.Utilizador;
import com.dai2324.prototipodai.repositorio.LoggedInUtilizadorRepositorio;
import com.dai2324.prototipodai.repositorio.QRCodeRepositorio;
import com.dai2324.prototipodai.repositorio.UtilizadorRepositorio;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

@Service
public class QRCodeImageService {

    @Autowired
    private QRCodeRepositorio qrCodeImageRepository;

    @Autowired
    private UtilizadorRepositorio utilizadorRepositorio;

    @Autowired
    private LoggedInUtilizadorRepositorio loggedInUtilizadorRepositorio;

    private String convertUtilizadorToJson(Utilizador utilizador) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(utilizador);
    }

    public void saveQRCodeImage(Utilizador utilizador, byte[] qrCodeImage) throws Exception {
        String text = convertUtilizadorToJson(utilizador);

        QRCodeImage qrCodeImageEntity = new QRCodeImage();
        qrCodeImageEntity.setText(text);
        qrCodeImageEntity.setQrCodeImage(qrCodeImage);

        qrCodeImageEntity = qrCodeImageRepository.save(qrCodeImageEntity);

        Long id = qrCodeImageEntity.getId();
        text = text + ", \"id\": " + id + "}";
        qrCodeImageEntity.setText(text);

        qrCodeImageRepository.save(qrCodeImageEntity);
    }

    public QRCodeImage getQRCodeImage(Long id) {
        return qrCodeImageRepository.findById(id).orElse(null);
    }

    public QRCodeImage updateQRCodeImage(Long id, String text, byte[] qrCodeImage) {
        QRCodeImage qrCodeImageEntity = getQRCodeImage(id);
        if (qrCodeImageEntity != null) {
            qrCodeImageEntity.setText(text);
            qrCodeImageEntity.setQrCodeImage(qrCodeImage);
            qrCodeImageRepository.save(qrCodeImageEntity);
        }
        return qrCodeImageEntity;
    }

    public void deleteQRCodeImage(Long id) {
        qrCodeImageRepository.deleteById(id);
    }

    public List<QRCodeImage> getAllQRCodeImages() {
        return qrCodeImageRepository.findAll();
    }

    public ResponseEntity<LoggedInUtilizador> updateUtilizadorPontosFromQRCode(String text, int pontos)
            throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(text);

        int id = jsonNode.get("id").asInt();
        LoggedInUtilizador loggedInUtilizador = loggedInUtilizadorRepositorio.findById(id)
                .orElseThrow(() -> new Exception("Utilizador not found"));

        System.out.println("Pontos: " + loggedInUtilizador.getPontos());
        loggedInUtilizador.setPontos(loggedInUtilizador.getPontos() + pontos);

        System.out.println("Pontos ADICIONADOS: " + loggedInUtilizador.getPontos());

        System.out.println("Utilizador: " + loggedInUtilizador);
        loggedInUtilizador = loggedInUtilizadorRepositorio.save(loggedInUtilizador);
        System.out.println("Utilizador GUARDADO: " + loggedInUtilizador);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(loggedInUtilizador);
    }

    public QRCodeImage generateQRCode(String userId) {
        String qrCodeData = userId;
        String charset = "UTF-8"; // or "ISO-8859-1"
        Map<EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

        try {
            String filePath = "QRCode_" + UUID.randomUUID().toString() + ".png";

            createQRCode(qrCodeData, filePath, charset, hintMap, 200, 200);
            System.out.println("QR Code imagem criada com sucesso!");

            Optional<Utilizador> utilizadorOptional = utilizadorRepositorio.findByNumdocIdentificacao(userId);

            if (!utilizadorOptional.isPresent()) {
                System.err.println("Nenhum Utilizador encontrado com numdocidentificacao: " + userId);
                return null;
            }

            Utilizador utilizador = utilizadorOptional.get();

            QRCodeImage qrCodeImage = new QRCodeImage();
            qrCodeImage.setText(userId);
            qrCodeImage.setQrCodeImage(Files.readAllBytes(Paths.get(filePath))); 
            qrCodeImage.setUtilizador(utilizador);

            qrCodeImageRepository.save(qrCodeImage);

            return qrCodeImage;

        } catch (Exception e) {
            System.err.println("Não foi possível gerar QR Code, WriterException :: " + e.getMessage());
            return null;
        }
    }

    public static void createQRCode(String qrCodeData, String filePath, String charset, Map hintMap, int qrCodeheight,
            int qrCodewidth)
            throws WriterException, IOException {
        BitMatrix matrix = new QRCodeWriter().encode(new String(qrCodeData.getBytes(charset), charset),
                BarcodeFormat.QR_CODE, qrCodewidth, qrCodeheight, hintMap);
        MatrixToImageWriter.writeToPath(matrix, filePath.substring(filePath.lastIndexOf('.') + 1), Paths.get(filePath));
    }

    public String getLoggedInUserId() {
        LoggedInUtilizador user = loggedInUtilizadorRepositorio.findFirstByTipoPerfis_Perfil("CLIENTE");

        if (user == null) {
            return null;
        }

        return user.getNumdocidentificacao();
    }

    public QRCodeImage getQRCodeImageByUserId(String userId) {
        return qrCodeImageRepository.findByUtilizador_NumdocIdentificacao(userId);
    }
}