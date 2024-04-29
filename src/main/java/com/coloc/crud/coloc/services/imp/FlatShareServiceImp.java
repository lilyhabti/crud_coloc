package com.coloc.crud.coloc.services.imp;

import com.coloc.crud.coloc.models.FlatShare;
import com.coloc.crud.coloc.models.FlatShareApplication;
import com.coloc.crud.coloc.models.User;
import com.coloc.crud.coloc.repositories.FlatShareAppRepository;
import com.coloc.crud.coloc.repositories.FlatShareRepository;
import com.coloc.crud.coloc.repositories.UserRepository;
import com.coloc.crud.coloc.services.FlatShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FlatShareServiceImp implements FlatShareService {

    private final FlatShareRepository flatShareRepository;
    private final FlatShareAppRepository flatShareApplicationRepository;
    private final UserRepository userRepository;

    @Autowired
    public FlatShareServiceImp(FlatShareRepository flatShareRepository, FlatShareAppRepository flatShareApplicationRepository, UserRepository userRepository) {
        this.flatShareRepository = flatShareRepository;
        this.flatShareApplicationRepository = flatShareApplicationRepository;
        this.userRepository = userRepository;
    }


    public FlatShare createFlatShare(FlatShare flatShare, String ownerUsername) {
        // Fetch the owner user from the database based on the provided username
        Optional<User> ownerOptional = Optional.ofNullable(userRepository.findByUsername(ownerUsername));

        if (ownerOptional.isPresent()) {
            User owner = ownerOptional.get();

            // Set the owner of the flatshare
            flatShare.setOwner(owner);

            // Add the owner to the list of roommates of the flatshare
            flatShare.getRoomates().add(owner);

            // Set the flatshare as the colocation for the owner
            owner.setFlatShareColocs(flatShare);

            // Save the flatshare and owner
            flatShareRepository.save(flatShare);
            userRepository.save(owner);

            return flatShare;
        } else {
            throw new IllegalArgumentException("Owner with username " + ownerUsername + " not found.");
        }
    }

    // Modifier une colocation
    public FlatShare updateFlatShare(FlatShare flatShare) {
        return flatShareRepository.save(flatShare);
    }

    // Supprimer une colocation
    public void deleteFlatShare(Long flatShareId) {
        flatShareRepository.deleteById(flatShareId);
    }

    // Obtenir une colocation par ID
    public Optional<FlatShare> getFlatShareById(Long flatShareId) {
        return flatShareRepository.findById(flatShareId);
    }

    public List<FlatShare> getAllAvailableFlatShares() {
        return flatShareRepository.findAllAvailableFlatShares();
    }
    @Transactional
    public void approveFlatShareApplication(Long flatShareId, Long applicationId) {
        // Récupérer la colocation correspondant à l'ID
        FlatShare flatShare = flatShareRepository.findById(flatShareId)
                .orElseThrow(() -> new RuntimeException("Colocation introuvable"));

        // Récupérer la demande de colocation correspondant à l'ID
        FlatShareApplication application = flatShareApplicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Demande de colocation introuvable"));

        // Vérifier si l'utilisateur connecté est le propriétaire de la colocation
        if (!flatShare.getOwner().getIdUser().equals(application.getFlatShare().getOwner().getIdUser())) {
            throw new RuntimeException("Vous n'êtes pas autorisé à approuver cette demande.");
        }

        // Approuver la demande en ajoutant le demandeur à la liste des colocataires
        flatShare.getRoomates().add(application.getApplicant());

        // Enregistrer les modifications dans la base de données
        flatShareRepository.save(flatShare);

        // Supprimer la demande de la liste des demandes de colocation
        flatShareApplicationRepository.delete(application);
    }
}
