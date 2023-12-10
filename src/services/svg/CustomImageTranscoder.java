package services.svg;

import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.TranscodingHints;
import org.apache.batik.transcoder.image.ImageTranscoder;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class CustomImageTranscoder extends ImageTranscoder {
    private BufferedImage image = null;

    public BufferedImage createImage(int w, int h) {
        image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        return image;
    }

    public void writeImage(BufferedImage img, TranscoderOutput out) {}

    public BufferedImage getImage() {
        return image;
    }

    public Icon getIconFromSVG(String path, int x, int y) {
        try {
            CustomImageTranscoder transcoder = new CustomImageTranscoder();
            transcoder.createImage(x, y);
            TranscodingHints hints = new TranscodingHints();
            hints.put(ImageTranscoder.KEY_WIDTH, width);
            hints.put(ImageTranscoder.KEY_HEIGHT, height);
            transcoder.setTranscodingHints(hints);
            transcoder.transcode(new TranscoderInput(path), null);
            BufferedImage image = transcoder.getImage();

            return new ImageIcon(image);
        } catch (Exception e) {
            return null;
        }
    }
}
