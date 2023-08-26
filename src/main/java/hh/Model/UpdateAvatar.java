package hh.Model;

import org.springframework.web.multipart.MultipartFile;

public class UpdateAvatar {
    private  int id;
    private MultipartFile avatar;

    public UpdateAvatar() {
    }

    public UpdateAvatar(int id, MultipartFile avatar) {
        this.id = id;
        this.avatar = avatar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MultipartFile getAvatar() {
        return avatar;
    }

    public void setAvatar(MultipartFile avatar) {
        this.avatar = avatar;
    }
}
