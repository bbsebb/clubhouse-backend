package fr.hoenheimsports.service.game.mapper;

import fr.hoenheimsports.dto.game.importFromCsv.ImportCsvGameDTO;
import fr.hoenheimsports.gamedomain.exception.FileDataException;
import fr.hoenheimsports.gamedomain.exception.FileException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

public interface CSVFileMapper {

    List<ImportCsvGameDTO>  toImportCSVGameDTO (InputStream fileStream) throws FileException, FileDataException;
}
