package com.dai2324.prototipodai.Classes;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

// Dados Pontos
@Entity
public class QRCodeImage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String text;

	@Lob
	@Column(name = "qrcodeimage")
	private byte[] qrCodeImage;

	@ManyToOne
	@JoinColumn(name = "utilizador_id")
	private Utilizador utilizador;

	// getters e setters...

	public Utilizador getUtilizador() {
		return utilizador;
	}

	public void setUtilizador(Utilizador utilizador2) {
		this.utilizador = utilizador2;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public byte[] getQrCodeImage() {
		return qrCodeImage;
	}

	public void setQrCodeImage(byte[] qrCodeImage) {
		this.qrCodeImage = qrCodeImage;
	}

	public byte[] generateQRCodeImage(String text, int width, int height) throws WriterException, IOException {
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

		ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
		MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
		byte[] pngData = pngOutputStream.toByteArray();
		return pngData;
	}

	@Override
	public String toString() {
		return "QRCodeImage [id=" + id + ", text=" + text + "]";
	}
}