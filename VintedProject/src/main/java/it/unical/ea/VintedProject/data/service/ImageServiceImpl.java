package it.unical.ea.VintedProject.data.service;

import it.unical.ea.VintedProject.config.i18n.MessageLang;
import it.unical.ea.VintedProject.config.utility.FileUtil;
import it.unical.ea.VintedProject.core.detail.LoggedUserDetail;
import it.unical.ea.VintedProject.core.detail.LoggedUserMethod;
import it.unical.ea.VintedProject.data.entities.BasicInsertion;
import it.unical.ea.VintedProject.data.entities.User;
import it.unical.ea.VintedProject.data.service.interfaces.BasicInsertionService;
import it.unical.ea.VintedProject.data.service.interfaces.ImageService;
import it.unical.ea.VintedProject.data.service.interfaces.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.ws.rs.BadRequestException;
import java.io.File;
import java.util.Optional;

//Dao Notation:
//DAO (JPA): find, delete
//DAO (Service): get, update, delete

@Service
@RequiredArgsConstructor
@Slf4j
public class ImageServiceImpl implements ImageService {


    //TODO: MESSAGGI DI ERRORE

    private final UserService userService;
    private final BasicInsertionService insertionService;
    private final MessageLang messageLang;
    private final LoggedUserMethod loggedUserMethod;

    private final String relativePathToUploads = "src/main/resources/image/";

    @Override
    public Resource getImage(String imagePath) {

        // Costruisci il percorso completo del file utilizzando il filepath ricevuto
        String fullPath = relativePathToUploads + imagePath;

        // Crea una risorsa File System utilizzando il percorso completo del file
        Resource resource = new FileSystemResource(fullPath);

        if(resource.exists()){
            return resource;
        }
        throw new EntityNotFoundException(messageLang.getMessage("image.not.found"));
    }

    public Boolean insertUserImage(MultipartFile img){

        User u = loggedUserMethod.getEntireLoggedUser();
        try {
            String realPathToUploads = System.getProperty("user.dir") + File.separator + relativePathToUploads;

            if (!new File(realPathToUploads).exists()) { //If the directory "image" is not existent
                new File(realPathToUploads).mkdir();     //Create a directory
            }

            if(u.getImageName() != null) {                                  //If the User has already a photo
                String oldFilePath = realPathToUploads + u.getImageName();  //"\\"+
                File fileToDelete = new File(oldFilePath);                  //Go to the path the old photo
                if (fileToDelete.exists()) {                                //Check if the photo exists
                    if (fileToDelete.delete()) {                            //delete old photo
                        log.info("The file has been deleted successfully");
                    }
                }
            }

            String orgName = FileUtil.assignProgressiveName(img);
            String filePath = realPathToUploads + orgName; //"\\"+

            File dest = new File(filePath);
            img.transferTo(dest);

            u.setImageName(orgName);

            userService.save(u);

            return true;
        }catch (Exception e){ return false; }
    }

    public Boolean insertInsertionImage(Long insertionId, MultipartFile img){
        BasicInsertion insertion = insertionService.getById(insertionId);
        User u = loggedUserMethod.getEntireLoggedUser();

        if(!u.getId().equals(insertion.getUser().getId())){
            throw new BadRequestException(messageLang.getMessage("access.denied"));
        }
        try {
            String realPathToUploads = System.getProperty("user.dir") + File.separator + relativePathToUploads;

            if (!new File(realPathToUploads).exists()) { //If the directory "image" is not existent
                new File(realPathToUploads).mkdir();     //Create a directory
            }

            String orgName = FileUtil.assignProgressiveName(img);
            String filePath = realPathToUploads + orgName; //"\\"+

            File dest = new File(filePath);
            img.transferTo(dest);

            insertion.setImageName(orgName);

            insertionService.save(insertion);

            return true;
        }catch (Exception e){ return false; }
    }

    @Override
    public void deleteImageUser(Long id) {
        User user = userService.getUserById(id);
        String newPath =  relativePathToUploads + user.getImageName();

        File file = new File(newPath);

        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                log.info("File deleted successfully");
                user.setImageName(null);
                userService.save(user);
            } else {
                log.info("Impossible to eliminate the file");
            }
        } else {
            log.info("The file is non existent or isn't in a valid file type");
        }


    }
    @Override
    public void userDeleteImageInsertion(Long insertionId) {
        BasicInsertion basicInsertion = insertionService.getById(insertionId);
        User u = loggedUserMethod.getEntireLoggedUser();
        if(basicInsertion.getUser().getId().equals(u.getId())){
            String newPath = relativePathToUploads + basicInsertion.getImageName();

            File file = new File(newPath);
            if (file.exists() && file.isFile()) {
                if (file.delete()) {
                    log.info("File deleted successfully");
                    basicInsertion.setImageName(null);
                    insertionService.save(basicInsertion);
                } else {
                    log.info("Impossible to eliminate the file");
                }
            } else {
                log.info("The file is non existent or isn't in a valid file type");
            }
        } else {
            throw new BadRequestException(messageLang.getMessage("access.denied"));
        }
    }

    @Override
    public void adminDeleteImageInsertion(Long id) {

        BasicInsertion basicInsertion = insertionService.getById(id);
        String newPath = relativePathToUploads + basicInsertion.getImageName();

        File file = new File(newPath);
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                log.info("File deleted successfully");
                basicInsertion.setImageName(null);
                insertionService.save(basicInsertion);
            } else {
                log.info("Impossible to eliminate the file");
            }
        } else {
            log.info("The file is non existent or isn't in a valid file type");
        }
    }

    /*
    try {
         String fileName = file.getOriginalFilename();
         String filePath = "/path/to/save/" + fileName;
         file.transferTo(new File(filePath));
         return "Immagine caricata con successo.";
       } catch (IOException e) {
         return "Errore durante il caricamento dell'immagine: " + e.getMessage();
       }
     */

}
