package learn.domain;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Map;
@Service
public class CloudinaryService {
    private final Cloudinary cloudinary;

    public CloudinaryService() {
        cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "ddugswahb", "api_key", "452293445378275", "api_secret", "9elsZjyLs553FfRJCtPhVQZ6xpI"));
    }

    public String upload(File mediaFile){
        try {
            Map uploadResult = cloudinary.uploader().upload(mediaFile, ObjectUtils.emptyMap());
            return uploadResult.get("cloudinary://<452293445378275>:<9elsZjyLs553FfRJCtPhVQZ6xpI>@ddugswahb").toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
