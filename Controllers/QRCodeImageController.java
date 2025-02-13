package com.dai2324.prototipodai.Controlo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.dai2324.prototipodai.Classes.QRCodeImage;
import org.springframework.web.bind.annotation.PostMapping;

// Controlo de Gestão de Pontos - Verifica a Validação de QR Codes
@RestController
public class QRCodeImageController {

    @Autowired
    private QRCodeImageService qrCodeImageService;

    @GetMapping("/qrcodeimage/{id}")
    public QRCodeImage getQRCodeImage(@PathVariable Long id) {
        return qrCodeImageService.getQRCodeImage(id);
    }

    @PutMapping("/qrcodeimagePut/{id}")
    public QRCodeImage updateQRCodeImage(@PathVariable Long id, @RequestBody QRCodeImage qrCodeImage) {
        return qrCodeImageService.updateQRCodeImage(id, qrCodeImage.getText(), qrCodeImage.getQrCodeImage());
    }

    @DeleteMapping("/qrcodeimageDelete/{id}")
    public void deleteQRCodeImage(@PathVariable Long id) {
        qrCodeImageService.deleteQRCodeImage(id);
    }

    @GetMapping("/qrcodeimages")
    public List<QRCodeImage> getAllQRCodeImages() {
        return qrCodeImageService.getAllQRCodeImages();
    }

    @PostMapping("/updatepontos")
    public void updatePontos(@RequestBody String text, @RequestParam int pontos) throws Exception {
        qrCodeImageService.updateUtilizadorPontosFromQRCode(text, pontos);
    }

    @GetMapping("/qrcodeimage2/{id}")
    public ResponseEntity<byte[]> getQRCodeImage2(@PathVariable Long id) {

        QRCodeImage qrCodeImage = qrCodeImageService.getQRCodeImage(id);
        byte[] imageData = qrCodeImage.getQrCodeImage(); 

        qrCodeImageService.deleteQRCodeImage(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
    }

    @PostMapping("/generateqrcode")
    public ResponseEntity<?> generateQRCode() {
    
        String userId = qrCodeImageService.getLoggedInUserId();

        if (userId == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        QRCodeImage existingQRCode = qrCodeImageService.getQRCodeImageByUserId(userId);
        if (existingQRCode != null) {
            return new ResponseEntity<>(existingQRCode, HttpStatus.OK);
        }

        QRCodeImage newQRCode = qrCodeImageService.generateQRCode(userId);

        return new ResponseEntity<>(newQRCode, HttpStatus.CREATED);
    }

}