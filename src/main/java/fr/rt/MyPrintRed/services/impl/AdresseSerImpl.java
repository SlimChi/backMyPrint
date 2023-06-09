package fr.rt.MyPrintRed.services.impl;

import fr.rt.MyPrintRed.dto.AdresseDto;
import fr.rt.MyPrintRed.entities.Adresse;
import fr.rt.MyPrintRed.entities.Utilisateur;
import fr.rt.MyPrintRed.mapper.AdresseMapper;
import fr.rt.MyPrintRed.repositories.AdresseRepository;
import fr.rt.MyPrintRed.services.AdresseService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author slimane
 * @Project auth
 */
@Service
@RequiredArgsConstructor
public class AdresseSerImpl implements AdresseService {

    private final AdresseRepository adresseRepository;

    private final AdresseMapper mapper;


    @Override
   public Integer save(AdresseDto dto) {
       // Adresse address = AdresseDto.toEntity(dto);
    return adresseRepository.save(mapper.toAdresse(dto)).getId();
    }

    @Override
    @Transactional
    public void addAdresseToUser(AdresseDto dto){

       // Adresse adresse = AdresseDto.toEntity(dto);

        adresseRepository.save(mapper.toAdresse(dto)).getIdUtilisateur();
    }
    @Transactional
    @Override
    public List<AdresseDto> findAll() {
        return mapper.toListDto(adresseRepository.findAll());

    }
    @Transactional
    @Override
    public AdresseDto findById(Integer id) {

        return mapper.toDto(adresseRepository.findById(id).orElseThrow(()->new EntityNotFoundException("No address found with the ID : " + id)));

    }

    @Override
    @Transactional
    public void updateAdress(Integer id, String rue, String complement, String codePostal, String ville){

        Adresse updateAdresse = adresseRepository.findById(id).orElseThrow();

        updateAdresse.setId(id);
        updateAdresse.setRue(rue);
        updateAdresse.setComplement(complement);
        updateAdresse.setCodePostal(codePostal);
        updateAdresse.setVille(ville);


    }



    @Override
    public void delete(Integer id) {
        adresseRepository.deleteById(id);
    }



}
