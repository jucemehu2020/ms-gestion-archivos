package com.unicauca.maestria.api.gestionarchivosms.common.util;

import org.apache.tika.metadata.Metadata;
import org.apache.tika.mime.MediaType;
import org.apache.tika.mime.MimeTypeException;
import org.apache.tika.mime.MimeTypes;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

public class FilesUtilities {

    public static String guardarArchivo(String archivoBase64) {
        try {
            String[] partesBase64 = archivoBase64.split("-");
            byte[] archivoBytes = Base64.getDecoder().decode(partesBase64[1]);
            Date fechaActual = new Date();
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yy");
            String fechaFormateada = formatoFecha.format(fechaActual);
            ByteArrayInputStream inputStream = new ByteArrayInputStream(archivoBytes);

            String rutaCarpeta = "./files/" + fechaFormateada;
            String rutaArchivo = rutaCarpeta + "/" + generateUniqueFileName() + "-" + partesBase64[0];
            File carpeta = new File(rutaCarpeta);
            OutputStream out = null;
            if (!carpeta.exists()) {
                if (carpeta.mkdirs()) {
                    out = new FileOutputStream(rutaArchivo);
                    out.write(archivoBytes);
                    out.close();
                    return rutaArchivo;
                }
            } else {
                out = new FileOutputStream(rutaArchivo);
                out.write(archivoBytes);
                out.close();
                return rutaArchivo;
            }
            return "Error al guardar el archivo";
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String detectExtension(ByteArrayInputStream inputStream) throws IOException, MimeTypeException {
        MimeTypes mimeTypes = MimeTypes.getDefaultMimeTypes();
        MediaType mimeType = mimeTypes.detect(inputStream, new Metadata());

        if (mimeType != null) {
            return mimeTypes.forName(String.valueOf(mimeType)).getExtension();
        }

        return "error";
    }

    private static String generateUniqueFileName() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String timestamp = dateFormat.format(new Date());
        return timestamp;
    }

    public static boolean deleteFileExample(String rutaArchivo) {
        File archivo = new File(rutaArchivo);
        if (archivo.exists()) {
            if (archivo.delete()) {
                return true;
            }
        }
        return false;
    }
}
