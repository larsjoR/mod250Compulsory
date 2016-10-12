package no.hib.mod250.anthrax.util;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.*;
import java.util.regex.Pattern;

import static javax.faces.event.PhaseId.RENDER_RESPONSE;

/**
 * Utility helper class for handling images
 */
public class ImageHelper {

    public ImageHelper() { }


    /**
     * Helper method to extract filename from full path
     * @param path the full path
     * @return the filname
     */
    public String getFileNameFromFullPath(String path) {
        String[] filenameString;
        filenameString = path.split(Pattern.compile("\\\\").toString());
        return filenameString[filenameString.length-1];
    }

    /**
     * Method to copy file to a file destination
     * @param destination the file destination
     * @param filename the filename
     * @param in the input stream
     */
    public void copyFile(String destination, String filename, InputStream in) {
        try {
            File location = new File(destination);

            if (!location.exists()) {
                location.mkdirs();
            }

            if (new File(destination + filename).exists()) {
                //  FacesMessage message = new FacesMessage("File already exists!");
                //  FacesContext.getCurrentInstance().addMessage("newProductForm:uploadFile", message);
            }

            OutputStream out = new FileOutputStream(new File(destination + filename));

            int read;
            byte[] bytes = new byte[1024];

            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }

            in.close();
            out.flush();
            out.close();

        } catch (IOException e) {
            FacesMessage message = new FacesMessage("Could not upload file", destination + filename + ". Please try again");
            FacesContext.getCurrentInstance().addMessage("newProductForm:uploadFile", message);
        }


    }

    /**
     * Method to extract image from location.
     * Not used in this implementation after issues with local URI, and
     * since we did not persist the images as blobs in db.
     * @param path the full path of the file
     * @return a Stream with the image
     * @throws IOException
     */
    public StreamedContent getImage(String path) throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        ImageHelper ih = new ImageHelper();
        String file = ih.getFileNameFromFullPath(path);

        if (context.getCurrentPhaseId() == RENDER_RESPONSE) {
            return new DefaultStreamedContent();
        } else {
            String filename = context.getExternalContext().getRequestParameterMap().get(file);
            return new DefaultStreamedContent(new FileInputStream(new File(path, filename)));
        }
    }
}
